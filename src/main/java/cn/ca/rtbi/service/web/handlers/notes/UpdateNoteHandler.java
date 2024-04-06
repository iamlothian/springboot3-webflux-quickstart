package cn.ca.rtbi.service.web.handlers.notes;

import cn.ca.rtbi.service.core.services.NoteCoreService;
import cn.ca.rtbi.service.web.handlers.AbstractValidationHandler;
import cn.ca.rtbi.service.web.models.CreateNoteDTO;
import cn.ca.rtbi.service.web.models.UpdateNoteDTO;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UpdateNoteHandler extends AbstractValidationHandler<UpdateNoteDTO, Validator> {

    private final NoteCoreService service;

    public UpdateNoteHandler(NoteCoreService noteApplicationService, Validator validator) {
        super(UpdateNoteDTO.class, validator);
        this.service = noteApplicationService;
    }

    @Override
    protected Mono<ServerResponse> processBody(UpdateNoteDTO note, ServerRequest request) {
        int noteId = Integer.parseInt(request.pathVariable("id"));
        return Mono.just(note)
                .map(UpdateNoteDTO::toNote)
                .flatMap(n -> service.update(noteId, n))
                .flatMap(noteEntity -> ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(OptimisticLockingFailureException.class, e -> ServerResponse.status(409).build());
    }
}
