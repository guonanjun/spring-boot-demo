package com.guonanjun.demo.config;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-18
 */
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
public class RedisConfig {

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.redisCacheConfiguration(), // 默认策略，未配置的 key 会使用这个
                this.initialCacheConfigurations() // 指定 key 策略
        );
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置默认的Serialize
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        //单独设置keySerializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //单独设置valueSerializer
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);

        return redisTemplate;
    }

    private Map<String, RedisCacheConfiguration> initialCacheConfigurations() {
        Map<String, RedisCacheConfiguration> configurationMap = new ConcurrentHashMap<>();
        // configurationMap.put("user", this.redisCacheConfiguration(1000));

        return configurationMap;
    }


    private RedisCacheConfiguration redisCacheConfiguration() {
        return redisCacheConfiguration(null);
    }

    private RedisCacheConfiguration redisCacheConfiguration(Integer customExpireSeconds) {

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
        if (customExpireSeconds == null) {
            if (redisProperties.getTimeToLive() != null) {
                config = config.entryTtl(redisProperties.getTimeToLive());
            }
        } else {
            config = config.entryTtl(Duration.ofSeconds(customExpireSeconds));
        }

        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }



}
