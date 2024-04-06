package cn.ca.rtbi.service.core.commands;

import cn.ca.rtbi.service.core.models.CoreNote;

public record CreateNoteCommand(String note) {
    public static CoreNote toNote(CreateNoteCommand note) {
        return CoreNote.builder()
                .note(note.note())
                .build();
    }
}
