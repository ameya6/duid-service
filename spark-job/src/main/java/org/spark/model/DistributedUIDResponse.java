package org.spark.model;

import lombok.*;
import org.data.model.response.DUIDDetailedResponse;
import org.data.model.response.DUIDResponse;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
//@Builder
@Data
public class DistributedUIDResponse extends DUIDDetailedResponse implements Serializable {

    private LocalDateTime processStartTime;
    private LocalDateTime processEndTime;
    private Double responseTime;

    public DistributedUIDResponse(){}



}
