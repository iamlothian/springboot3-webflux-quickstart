package cn.ca.rtbi.service.web.handlers.notes;

import cn.ca.rtbi.service.core.services.NoteCoreService;
import cn.ca.rtbi.service.infrastructure.entities.notes.NoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class NoteHandler {

    private final NoteCoreService service;

    public Mono<ServerResponse> getNotes(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), NoteEntity.class);
    }

    public Mono<ServerResponse> getNoteById(ServerRequest request) {
        return Mono.just(request.pathVariable("id"))
                .map(Integer::parseInt)
                .onErrorMap(NumberFormatException.class, e ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid note id"))
                .flatMap(service::findById)
                .flatMap(noteEntity -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(noteEntity))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorMap(Exception.class, e ->
                        new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Error", e));
    }

    public Mono<ServerResponse> deleteNoteById(ServerRequest request) {
        return Mono.just(request.pathVariable("id"))
                .map(Integer::parseInt)
                .onErrorMap(NumberFormatException.class, e ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid note id"))
                .flatMap(service::deleteById)
                .then(ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteAllNotes(ServerRequest request) {
        return service.deleteAll()
                .then(ServerResponse.noContent().build());
    }

}
