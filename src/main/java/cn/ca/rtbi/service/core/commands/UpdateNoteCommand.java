package cn.ca.rtbi.service.core.commands;

import cn.ca.rtbi.service.core.models.CoreNote;

public record UpdateNoteCommand(String note, Long version) {
    public static CoreNote toNote(UpdateNoteCommand note) {
        return CoreNote.builder()
                .note(note.note())
                .version(note.version())
                .build();
    }
}
