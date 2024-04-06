package cn.ca.rtbi.service.application.repositories;

import cn.ca.rtbi.service.core.models.CoreNote;
import cn.ca.rtbi.service.core.repositories.NotesCoreRepository;
import cn.ca.rtbi.service.infrastructure.entities.notes.NoteEntity;
import cn.ca.rtbi.service.infrastructure.repositories.NoteEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotesApplicationRepository implements NotesCoreRepository {

    final NoteEntityRepository noteRepository;

    public Flux<CoreNote> findAll() {
        return noteRepository.findAll()
                .map(NoteEntity::toNote);
    }

    public Mono<CoreNote> findById(int id) {
        return noteRepository.findById(id)
                .map(NoteEntity::toNote);
    }

    public Mono<CoreNote> create(CoreNote coreNote) {
        return Mono.just(coreNote)
                .map(NoteEntity::fromNote)
                .flatMap(noteRepository::save)
                .map(NoteEntity::toNote);
    }

    public Mono<CoreNote> update(int id, CoreNote coreNote) {
        return noteRepository.findById(id)
                .map(existingNoteEntity -> NoteEntity.builder()
                        .id(existingNoteEntity.id())
                        .version(coreNote.version())
                        .note(coreNote.note())
                        .createdAt(existingNoteEntity.createdAt())
                        .build()
                )
                .flatMap(noteRepository::save)
                .map(NoteEntity::toNote);
    }

    public Mono<Void> deleteById(int id) {
        return noteRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return noteRepository.deleteAll();
    }
}
