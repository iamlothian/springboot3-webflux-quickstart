package cn.ca.rtbi.service.core.repositories;

import cn.ca.rtbi.service.core.models.CoreNote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotesCoreRepository {

    Flux<CoreNote> findAll();

    Mono<CoreNote> findById(int id);

    Mono<CoreNote> create(CoreNote coreNote);

    Mono<CoreNote> update(int id, CoreNote coreNote);

    Mono<Void> deleteAll();

    Mono<Void> deleteById(int id);

}

