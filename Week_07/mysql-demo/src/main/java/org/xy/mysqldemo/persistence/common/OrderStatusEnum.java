package org.xy.mysqldemo.persistence.common;

/**
 * OrderStatusEnum
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
public enum OrderStatusEnum {

    /**
     * 待付款
     */
    WAIT_FOR_PAYMENT,

    /**
     * 已下单
     */
    ORDERED,

    /**
     * 待发货
     */
    WAIT_FOR_DELIVERY,

    /**
     * 已发货
     */
    DELIVERED,

    /**
     * 已收货
     */
    RECEIVE
}
