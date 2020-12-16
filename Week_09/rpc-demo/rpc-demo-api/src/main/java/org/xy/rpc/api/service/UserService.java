package org.xy.rpc.api.service;

import org.xy.rpc.api.model.User;

/**
 * UserService
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
public interface UserService {

    /**
     * findUserById
     *
     * @param id id
     * @return findUserById
     */
    User findUserById(int id);
}
