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

    private Class<?> serviceClass;
    private String url;
}
