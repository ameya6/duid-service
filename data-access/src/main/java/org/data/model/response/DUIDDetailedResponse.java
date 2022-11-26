package org.data.model.response;

import lombok.*;
import lombok.extern.log4j.Log4j2;
/*import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;*/

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class DUIDDetailedResponse {

    protected UUID id;
    protected Long duid;
    protected LocalDateTime createdAt;
    protected Long uidTimestamp;
    protected Long randomNodeId;
    LocalDateTime epochDateTime;
    protected Long maxNodeId;
    protected Long initialBits;
    protected Long totalBits;
    protected Long nodeIdBits;
    protected Long randomIdBound;
    protected Integer randomNumber;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected Double processTimeInMillis;
    protected String message;

    public DUIDDetailedResponse(){}
}
