package org.data.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class DUIDDetailedResponse {

    private Long duid;
    private LocalDateTime createdAt;
    private Long uidTimestamp;
    private Long randomNodeId;
    LocalDateTime epochDateTime;
    private Long maxNodeId;
    private Long initialBits;
    private Long totalBits;
    private Long nodeIdBits;
    private Long randomIdBound;
    private String message;

    public DUIDDetailedResponse(){}
}
