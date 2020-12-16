package org.xy.rpc.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RpcRequest
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest {

    private String serviceClass;

    private String method;

    private Object[] params;
}
