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
     * name
     */
    @Column(nullable = false, length = 30)
    private String name;

    /**
     * price
     */
    @Column(nullable = false, length = 10)
    private String price;

    /**
     * weight
     */
    @Column(length = 10)
    private String weight;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private OrderDO order;
}
