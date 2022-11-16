package org.data.model.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Table("distributed_uid")
public class DistributedUID {
    @PrimaryKey
    private UUID id;
    private Long duid;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("uid_timestamp")
    private Long uidTimestamp;

    @Column("random_node_id")
    private Long randomNodeId;

    @Column("epoch_date_time")
    LocalDateTime epochDateTime;

    @Column("max_node_id")
    private Long maxNodeId;

    @Column("initial_bits")
    private Long initialBits;

    @Column("total_bits")
    private Long totalBits;

    @Column("node_id_bits")
    private Long nodeIdBits;

    @Column("random_id_bound")
    private Long randomIdBound;

    public DistributedUID(){}
}
