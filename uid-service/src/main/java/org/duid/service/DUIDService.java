package org.duid.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.data.model.entity.DistributedUID;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@Log4j2
public class DUIDService {
    public DistributedUID duid() {
        LocalDateTime startDateTime = LocalDateTime.of(2000, 1,1, 00, 0);
        Timestamp epoch = Timestamp.valueOf(startDateTime);
        int maxNodeId = 1024;
        Long duid = System.nanoTime() - epoch.getTime();
        Random random = new Random();

        duid = duid << (64 - 41);


        Long randomNodeId = Math.abs(Long.valueOf(UUID.randomUUID().hashCode())) % maxNodeId;

        duid |= randomNodeId << (64-41-13);


        int num = random.nextInt(1024);

        duid |= num;

        duid = Math.abs(duid);

        return DistributedUID.builder()
                .duid(duid)
                .uid(UUID.randomUUID())
                .build();
    }
}
