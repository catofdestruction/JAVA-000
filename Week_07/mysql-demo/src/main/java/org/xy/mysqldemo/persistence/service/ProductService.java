package org.xy.mysqldemo.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.mysqldemo.persistence.model.ProductDO;
import org.xy.mysqldemo.persistence.repository.ProductRepository;

/**
 * product service
 *
 * @author wangxinyu
 * @date 2020/12/2
 */
@Slf4j
@Service
public class ProductService extends BaseService<ProductDO, Long> {

    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
