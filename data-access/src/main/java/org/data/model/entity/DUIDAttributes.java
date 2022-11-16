package org.data.model.entity;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties("duid-attributes")
public class DUIDAttributes {
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Long maxNodeId;
    private Long initialBits;
    private Long totalBits;
    private Long nodeIdBits;
    private Long randomIdBound;
}
