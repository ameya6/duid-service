package org.data.dao;

import org.data.model.entity.DistributedUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DUIDEventRepository extends ReactiveCrudRepository<DistributedUID, Integer> {
}
