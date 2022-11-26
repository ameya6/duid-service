package org.data.model.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Table(value = "distributed_uid")
public class DistributedUID {

    private UUID id;
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
    private Integer randomNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double processTimeInMillis;

    public DistributedUID(){}
}
