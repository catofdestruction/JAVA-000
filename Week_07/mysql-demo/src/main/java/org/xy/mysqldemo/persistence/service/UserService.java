package org.xy.mysqldemo.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.mysqldemo.persistence.model.UserDO;
import org.xy.mysqldemo.persistence.repository.UserRepository;

/**
 * user service
 *
 * @author wangxinyu
 * @date 2020/12/2
 */
@Slf4j
@Service
public class UserService extends BaseService<UserDO, Long> {

    @Autowired
    private UserRepository repository;

    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
