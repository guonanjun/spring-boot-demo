package com.guonanjun.demo.manager;

import com.guonanjun.common.ao.Result;
import com.guonanjun.demo.model.User;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
public interface UserManager {

    Result save(User user);
}
