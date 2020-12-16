package org.xy.rpc.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private int id;

    private String serialNumber;

    private float amount;
}
