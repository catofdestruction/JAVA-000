package org.xy.rpc.core.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Invoker
 *
 * @author wangxinyu
 * @date 2020/12/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoker {

    /**
     * class of proxy Service
     */
    private Class<?> serviceClass;

    /**
     * RPC server naming url to connect.
     */
    private String url;
}
