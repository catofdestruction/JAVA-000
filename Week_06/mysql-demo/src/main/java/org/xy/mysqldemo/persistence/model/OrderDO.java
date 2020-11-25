package org.xy.mysqldemo.persistence.model;

import lombok.Data;
import lombok.ToString;
import org.apache.catalina.User;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.format.annotation.DateTimeFormat;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
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

    /**
     * buyer id
     */
    @Column(nullable = false, length = 20)
    private Long buyerId;

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
    private List<MerchandiseDO> merchandises;
}
