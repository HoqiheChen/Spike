package com.bitcs.util;

/**
 * 订单的状态
 *
 * @author GeChen
 */

public enum OrderStatus {
    /**
     * 订单未支付
     */
    NOT_PAY(0),
    /**
     * 已支付
     */
    PAID(1),
    /**
     * 已发货
     */
    SHIPPED(2),
    /**
     * 已收货
     */
    RECEIVED(3),
    /**
     * 已退货
     */
    REFUNDED(4),
    /**
     * 已完成
     */
    FINISHED(5);
    private int value;

    OrderStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
