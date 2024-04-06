package cn.ca.rtbi.service.web.models;

import cn.ca.rtbi.service.core.commands.UpdateNoteCommand;

public record UpdateNoteDTO(String note, Long version) {
    public static UpdateNoteCommand toNote(UpdateNoteDTO note) {
        return new UpdateNoteCommand(note.note(), note.version());
    }
}
