package cn.ca.rtbi.service.infrastructure.entities.notes;

import cn.ca.rtbi.service.core.models.CoreNote;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Builder()
@Table("notes")
public record NoteEntity(
        @Id int id,
        @Version Long version,
        @Length(max = 1, message = "Note must be between 1 and 1000 characters")
        String note,
        @CreatedDate
        Instant createdAt,
        @LastModifiedDate
        Instant lastUpdatedAt
) {
        public static CoreNote toNote(NoteEntity noteEntity) {
                return CoreNote.builder()
                        .id(noteEntity.id())
                        .version(noteEntity.version())
                        .note(noteEntity.note())
                        .createdAt(noteEntity.createdAt())
                        .lastUpdatedAt(noteEntity.lastUpdatedAt())
                        .build();
        }

        public static NoteEntity fromNote(CoreNote coreNote) {
                return NoteEntity.builder()
                        .id(coreNote.id())
                        .version(coreNote.version())
                        .note(coreNote.note())
                        .createdAt(coreNote.createdAt())
                        .lastUpdatedAt(coreNote.lastUpdatedAt())
                        .build();
        }
}
