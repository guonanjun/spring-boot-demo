package com.guonanjun.demo.manager;

import com.guonanjun.demo.model.User;
import com.guonanjun.demo.exception.DaoException;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-10
 */
public interface UserManager {

    User save(User user) throws DaoException;

    User selectByUsername(String username);

}
