package org.xy.mysqldemo.persistence.model;

import lombok.Data;
import lombok.ToString;
import org.xy.mysqldemo.persistence.common.OrderStatusEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * OrderDO
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Data
@Entity
@Table(name = "t_order")
public class OrderDO extends IdDO {

//    /**
//     * buyer id
//     */
//    @ToString.Exclude
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "buyer_id", nullable = false, updatable = false)
//    private UserDO user;

    /**
     * seller id
     */
    @Column(nullable = false, length = 20)
    private Long sellerId;

    /**
     * order total price
     */
    @Column(nullable = false, length = 20)
    private String totalPrice;

    /**
     * create date
     */
    @Column(nullable = false)
    private Long createdDate;

    /**
     * last modified date
     */
    @Column(nullable = false)
    private Long lastModifiedDate;

    /**
     * order status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    /**
     * merchandise list
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<ProductDO> merchandises;
}
