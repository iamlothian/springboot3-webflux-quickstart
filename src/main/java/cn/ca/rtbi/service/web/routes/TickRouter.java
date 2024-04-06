package cn.ca.rtbi.service.web.routes;

import cn.ca.rtbi.service.web.handlers.TickHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class TickRouter {

    @Bean
    public RouterFunction<ServerResponse> tickRoutes(TickHandler tickHandler) {
        return RouterFunctions
                .route(POST( "/tick"), tickHandler::tick)
                .andRoute(GET("/tick").and(accept(MediaType.APPLICATION_JSON)), tickHandler::subscribeTick);
    }

}
