package org.xy.rpc.api.service;

import org.xy.rpc.api.model.User;
import org.xy.rpc.core.annotation.SimpleRpcProxy;

/**
 * UserService
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@SimpleRpcProxy(namingServiceUrl = "http://localhost:8167/")
public interface UserService {

    /**
     * findUserById
     *
     * @param id id
     * @return findUserById
     */
    User findUserById(int id);
}
