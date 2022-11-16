package org.data.dao;

import org.data.model.entity.DistributedUID;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface DistributedUIDRepository extends ReactiveCassandraRepository<DistributedUID, Integer> {
}
