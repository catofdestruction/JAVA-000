package org.xy.mysqldemo.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.xy.mysqldemo.persistence.common.OrderStatusEnum;
import org.xy.mysqldemo.persistence.common.PayTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OrderDO
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order")
public class OrderDO extends IdDO {

    /**
     * order serial number
     */
    @Column(nullable = false, name = "order_sn", length = 64, unique = true)
    private String orderSn;

    /**
     * user id
     */
    @Column(nullable = false, columnDefinition = "BIGINT(19)")
    private Long userId;

    /**
     * user name (real name)
     */
    @Column(nullable = false, length = 10)
    private String userName;

    /**
     * order total amount
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double totalAmount;

    /**
     * order freight amount
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double freightAmount;

    /**
     * order pay amount
     */
    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double payAmount;

    /**
     * pay type
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private PayTypeEnum payType;

    /**
     * status
     */
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private OrderStatusEnum status;

    /**
     * user id
     */
    @Column(nullable = false, columnDefinition = "BIGINT(19)")
    private Long addressId;

    /**
     * delivery serial number
     */
    @Column(name = "delivery_sn", length = 64, unique = true)
    private String deliverySn;

    /**
     * remark
     */
    private String remark;

    /**
     * isDelete
     */
    @Column(nullable = false)
    private Boolean isDeleted;

    /**
     * create date
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20)")
    private Long createdDate;

    /**
     * last modified date
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20)")
    private Long lastModifiedDate;
}
