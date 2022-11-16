package org.duid.service;

import lombok.extern.log4j.Log4j2;
import org.data.dao.DistributedUIDRepository;
import org.data.model.entity.DUIDAttributes;
import org.data.model.entity.DistributedUID;
import org.data.model.response.DUIDDetailedResponse;
import org.data.model.response.DUIDResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Log4j2
public class DUIDService {

    @Autowired
    private DUIDAttributes duidAttributes;

    @Autowired
    private DistributedUIDRepository distributedUIDRepository;

    @Autowired
    private LocalDateTime epochDateTime;

    @Autowired
    private Random random;

    public DUIDResponse getDUID() {
        return duidResponse(duid());
    }

    public DUIDDetailedResponse getDUIDWithDetail() {
        return duidDetailedResponse(duid());
    }

    public DistributedUID duid() {
        Long timestamp = System.nanoTime() - epochTimeStamp();
        Long firstDifference = duidAttributes.getTotalBits() - duidAttributes.getInitialBits();
        Long duid = timestamp << firstDifference;
        Long randomNodeId = randomNodeId();

        duid |= randomNodeId << (firstDifference-duidAttributes.getNodeIdBits());
        int randomNumber = randomNumber();
        duid |= randomNumber;
        duid = Math.abs(duid);

        DistributedUID distributedUID = duid(duid, timestamp, randomNodeId);
        save(distributedUID);
        return distributedUID;
    }

    private Long epochTimeStamp() {
        //LocalDateTime epochDateTime = epochDateTime();
        return Timestamp.valueOf(epochDateTime).getTime();
    }



    private Long randomNodeId() {
        return Math.abs(Long.valueOf(UUID.randomUUID().hashCode())) % duidAttributes.getMaxNodeId();
    }

    private Integer randomNumber() {
        return random.nextInt(duidAttributes.getRandomIdBound().intValue());
    }

    private DUIDResponse duidResponse(DistributedUID distributedUID) {
        return DUIDResponse.builder()
                .duid(distributedUID.getDuid())
                .build();
    }

    private DUIDDetailedResponse duidDetailedResponse(DistributedUID distributedUID) {
        return DUIDDetailedResponse.builder()
                .duid(distributedUID.getDuid())
                .createdAt(distributedUID.getCreatedAt())
                .epochDateTime(distributedUID.getEpochDateTime())
                .initialBits(distributedUID.getInitialBits())
                .maxNodeId(distributedUID.getMaxNodeId())
                .nodeIdBits(distributedUID.getNodeIdBits())
                .randomIdBound(distributedUID.getRandomIdBound())
                .totalBits(distributedUID.getTotalBits())
                .randomNodeId(distributedUID.getRandomNodeId())
                .uidTimestamp(distributedUID.getUidTimestamp())
                .build();
    }

    public void save(DistributedUID distributedUID) {
        distributedUIDRepository.saveAll(List.of(distributedUID))
                //.log()
                .doOnComplete(() -> log.info("DUID saved " + distributedUID))
                .doOnError(error -> log.info(error))
                .subscribe();
    }

    private DistributedUID duid(Long duid, Long timestamp, Long randomNodeId) {
        return DistributedUID.builder()
                .duid(duid)
                .id(UUID.randomUUID())
                .randomNodeId(randomNodeId)
                .createdAt(LocalDateTime.now())
                .uidTimestamp(timestamp)
                .epochDateTime(epochDateTime)
                .maxNodeId(duidAttributes.getMaxNodeId())
                .initialBits(duidAttributes.getInitialBits())
                .totalBits(duidAttributes.getTotalBits())
                .nodeIdBits(duidAttributes.getNodeIdBits())
                .randomIdBound(duidAttributes.getRandomIdBound())
                .build();
    }
}
