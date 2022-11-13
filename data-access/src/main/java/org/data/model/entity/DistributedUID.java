package org.data.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class DistributedUID {
    Long duid;
    UUID uid;
}
