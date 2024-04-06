package cn.ca.rtbi.service.core.services;

import cn.ca.rtbi.service.core.commands.CreateNoteCommand;
import cn.ca.rtbi.service.core.commands.UpdateNoteCommand;
import cn.ca.rtbi.service.core.models.CoreNote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoteCoreService {
    Flux<CoreNote> findAll();
    Mono<CoreNote> findById(int id);
    Mono<CoreNote> create(CreateNoteCommand note);
    Mono<CoreNote> update(int id, UpdateNoteCommand note);
    Mono<Void> deleteAll();
    Mono<Void> deleteById(int id);

}
