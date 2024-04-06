package cn.ca.rtbi.service.infrastructure.repositories;

import cn.ca.rtbi.service.infrastructure.entities.notes.NoteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteEntityRepository extends ReactiveCrudRepository<NoteEntity, Integer> {

}
