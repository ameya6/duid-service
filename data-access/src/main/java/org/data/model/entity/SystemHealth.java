package org.data.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

//import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Table(name = "system_health")
public class SystemHealth {
    private String message;
    private LocalDateTime created_at;

    public SystemHealth() {
    }
}
