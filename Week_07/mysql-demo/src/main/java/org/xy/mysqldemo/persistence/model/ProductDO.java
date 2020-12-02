package org.xy.mysqldemo.persistence.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MerchandiseDO
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Data
@Entity
@Table(name = "t_product")
public class ProductDO extends IdDO {

    /**
     * product serial number
     */
    @Column(nullable = false, name = "product_sn", length = 64, unique = true)
    private String productSn;

    /**
     * brand id
     */
    @Column(columnDefinition = "BIGINT(20)")
    private Long brandId;

    /**
     * category id
     */
    @Column(columnDefinition = "BIGINT(20)")
    private Long categoryId;

    /**
     * name
     */
    @Column(nullable = false, length = 64)
    private String name;

    /**
     * description
     */
    @Column(nullable = false)
    private String description;

    /**
     * price
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private String price;

    /**
     * weight
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private String weight;


    private String imageUrl;

    /**
     * isDelete
     */
    @Column(nullable = false)
    private Boolean isDeleted;

    /**
     * create date
     */
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long createdDate;

    /**
     * last modified date
     */
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Long lastModifiedDate;
}
