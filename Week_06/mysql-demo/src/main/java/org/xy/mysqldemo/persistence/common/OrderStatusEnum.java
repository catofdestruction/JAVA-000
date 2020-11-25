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
     * 已下单
     */
    ORDERED("已下单"),

    /**
     * 已发货
     */
    SHIPPED("已发货"),

    /**
     * 已收货
     */
    RECEIVED("已收货");

    private final String desc;

    OrderStatusEnum(String desc) {
        this.desc = desc;
    }

    public String desc() {
        return desc;
    }

    public static OrderStatusEnum status(String name) {
        return EnumUtils.getEnum(OrderStatusEnum.class, name);
    }
}
