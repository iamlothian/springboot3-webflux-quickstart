package cn.ca.rtbi.service.application.services;

import cn.ca.rtbi.service.core.commands.CreateNoteCommand;
import cn.ca.rtbi.service.core.commands.UpdateNoteCommand;
import cn.ca.rtbi.service.core.models.CoreNote;
import cn.ca.rtbi.service.core.repositories.NotesCoreRepository;
import cn.ca.rtbi.service.core.services.NoteCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NoteApplicationService implements NoteCoreService {

    final NotesCoreRepository noteRepository;

    public Flux<CoreNote> findAll() {
        return noteRepository.findAll();
    }
    public Mono<CoreNote> findById(int id) {
        return noteRepository.findById(id);
    }
    public Mono<CoreNote> create(CreateNoteCommand note) {
        return Mono.just(note)
                .map(CreateNoteCommand::toNote)
                .flatMap(noteRepository::create);
    }
    public Mono<CoreNote> update(int id, UpdateNoteCommand note) {
        return Mono.just(note)
                .map(UpdateNoteCommand::toNote)
                .flatMap(n -> noteRepository.update(id, n));
    }
    public Mono<Void> deleteById(int id) {
        return noteRepository.deleteById(id);
    }
    public Mono<Void> deleteAll() {
        return noteRepository.deleteAll();
    }
}
