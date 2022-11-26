package org.data.dao;

import org.data.model.entity.SystemHealth;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SystemHealthRepository extends ReactiveCrudRepository<SystemHealth, Long> {
}
