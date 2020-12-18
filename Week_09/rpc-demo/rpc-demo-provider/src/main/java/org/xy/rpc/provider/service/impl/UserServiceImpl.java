package org.xy.rpc.provider.service.impl;

import org.xy.rpc.api.model.User;
import org.xy.rpc.api.service.UserService;

/**
 * UserServiceImpl
 *
 * @author wangxinyu
 * @date 2020/12/18
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findUserById(int id) {
        return new User(id, "XY-" + System.currentTimeMillis());
    }
}
