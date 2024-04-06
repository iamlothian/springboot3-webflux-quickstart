package cn.ca.rtbi.service.core.models;

import lombok.Builder;
import java.time.Instant;

@Builder()
public record CoreNote(
        int id,
        Long version,
        String note,
        Instant createdAt,
        Instant lastUpdatedAt
) {
}
