package org.xy.mysqldemo.persistence.common;

import org.apache.commons.lang3.EnumUtils;

/**
 * OrderStatusEnum
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
public enum OrderStatusEnum {

    /**
     * 代付款
     */
    WAIT_FOR_PAYMENT,

    /**
     * 已下单
     */
    ORDERED,

    /**
     * 等待发货
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
