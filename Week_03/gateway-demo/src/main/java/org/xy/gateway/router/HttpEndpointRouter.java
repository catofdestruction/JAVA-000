package org.xy.gateway.router;

import java.util.List;

/**
 * http endpoint router
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
public interface HttpEndpointRouter {

    /**
     * route
     *
     * @param endpoints endpoints
     * @return endpoint
     */
    String route(List<String> endpoints);
}
