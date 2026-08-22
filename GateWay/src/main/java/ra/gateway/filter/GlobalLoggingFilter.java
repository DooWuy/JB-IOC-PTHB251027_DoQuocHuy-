package ra.gateway.filter;


import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;


import java.time.LocalDateTime;
@Component
public class GlobalLoggingFilter  implements GlobalFilter , Ordered {

    @Override
    public Mono<Void> filter(
            org.springframework.web.server.ServerWebExchange exchange,
            org.springframework.cloud.gateway.filter.GatewayFilterChain chain
    ) {
        String method = exchange.getRequest().getMethod().name();
        String path = exchange.getRequest().getURI().getPath();

        System.out.printf(
                "[%s] [%s] [%s] - Processing Request%n",
                LocalDateTime.now(),
                method,
                path
        );

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }


}
