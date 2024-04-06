package cn.ca.rtbi.service.web.handlers.notes;

import cn.ca.rtbi.service.core.services.NoteCoreService;
import cn.ca.rtbi.service.web.handlers.AbstractValidationHandler;
import cn.ca.rtbi.service.web.models.CreateNoteDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CreateNoteHandler extends AbstractValidationHandler<CreateNoteDTO, Validator> {

    private final NoteCoreService service;

    public CreateNoteHandler(NoteCoreService noteApplicationService, Validator validator) {
        super(CreateNoteDTO.class, validator);
        this.service = noteApplicationService;
    }

    @Override
    protected Mono<ServerResponse> processBody(CreateNoteDTO note, ServerRequest request) {
        return Mono.just(note)
                .map(CreateNoteDTO::toNote)
                .flatMap(service::create)
                .flatMap(noteEntity -> ServerResponse.created(
                                request.uriBuilder().pathSegment("{id}").build(noteEntity.id()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(noteEntity));
    }
}
