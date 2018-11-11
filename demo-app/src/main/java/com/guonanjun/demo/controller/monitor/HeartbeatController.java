package com.guonanjun.demo.controller.monitor;

import com.guonanjun.common.ao.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: 心跳检测
 *
 * @author guonanjun
 * @date 2018-11-10
 */
@RequestMapping("/heartbeat")
@RestController
public class HeartbeatController {

    @RequestMapping("/check")
    public Result<String> check() {
        return Result.ofSuccessMsg("check success");
    }

}
