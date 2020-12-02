package org.xy.mysqldemo.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.xy.mysqldemo.persistence.common.OrderStatusEnum;
import org.xy.mysqldemo.persistence.common.PayTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OrderDO
 *
 * @author wangxinyu
 * @date 2020/12/02
 */
@Data
@Entity
@Table(name = "t_order_item")
public class OrderItemDO extends IdDO {

    /**
     * order id
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20)", unique = true)
    private Long orderId;

    /**
     * order serial number
     */
    @Column(nullable = false, name = "order_sn", length = 64, unique = true)
    private String orderSn;

    /**
     * product id
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20)", unique = true)
    private Long productId;

    /**
     * product price
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double productPrice;

    /**
     * product quantity
     */
    @Column(nullable = false, columnDefinition = "INT(10)")
    private Integer productQuantity;

    /**
     * product total amount
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double productTotalAmount;

    /**
     * isDelete
     */
    @Column(nullable = false)
    private Boolean isDeleted;

    @CreatedBy
    @Column(length = 30, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(length = 30)
    private String lastModifiedBy;

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
