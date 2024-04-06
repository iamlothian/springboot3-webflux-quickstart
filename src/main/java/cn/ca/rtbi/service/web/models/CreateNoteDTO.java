package cn.ca.rtbi.service.web.models;

import cn.ca.rtbi.service.core.commands.CreateNoteCommand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateNoteDTO (
        @NotEmpty(message = "Note is required")
        @Size(min = 1, max = 1000, message = "Note must be between 1 and 1000 characters")
        String note
) {
    public static CreateNoteCommand toNote(CreateNoteDTO noteDTO) {
        return new CreateNoteCommand(noteDTO.note());
    }
}
