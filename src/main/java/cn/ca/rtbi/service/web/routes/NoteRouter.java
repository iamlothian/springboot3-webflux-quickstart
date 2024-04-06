package cn.ca.rtbi.service.web.routes;

import cn.ca.rtbi.service.web.handlers.notes.NoteHandler;
import cn.ca.rtbi.service.web.handlers.notes.CreateNoteHandler;
import cn.ca.rtbi.service.web.handlers.notes.UpdateNoteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class NoteRouter {

    @Bean
    public RouterFunction<ServerResponse> noteRoutes(
            CreateNoteHandler createNoteHandler,
            UpdateNoteHandler updateNoteHandler,
            NoteHandler noteHandler) {
        return RouterFunctions.route()
                .GET("/notes", noteHandler::getNotes)
                .GET("/notes/{id}", noteHandler::getNoteById)
                .POST("/notes", createNoteHandler::handleRequest)
                .PUT("/notes/{id}", updateNoteHandler::handleRequest)
                .DELETE("/notes/{id}", noteHandler::deleteNoteById)
                .DELETE("/notes", noteHandler::deleteAllNotes)
                .build();
    }
}
