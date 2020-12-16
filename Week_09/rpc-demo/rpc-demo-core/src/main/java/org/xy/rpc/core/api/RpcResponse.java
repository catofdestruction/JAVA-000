package org.xy.rpc.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RpcResponse
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse {

    private Object result;

    private boolean status;

    private Exception exception;
}
