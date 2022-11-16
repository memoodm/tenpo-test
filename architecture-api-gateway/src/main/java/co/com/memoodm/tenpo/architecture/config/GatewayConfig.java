package co.com.memoodm.tenpo.architecture.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
public class GatewayConfig implements GlobalFilter{

    final Logger logger = LoggerFactory.getLogger(GatewayConfig.class);

    private final String PREDICATE_MATCHED_PATH = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayPredicateMatchedPathAttr";
    private final String GATEWAY_REQUEST_URL = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRequestUrl";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,GatewayFilterChain chain) {
        String predicate = exchange.getAttribute(PREDICATE_MATCHED_PATH);
        URI gatewayRequestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL);
        String fixUrl = gatewayRequestUrl.toString().replace(
                predicate.replace("**",""),
                "/"
        );
        exchange.getAttributes().put( GATEWAY_REQUEST_URL , URI.create(fixUrl));
        return chain.filter(exchange);
    }

}
