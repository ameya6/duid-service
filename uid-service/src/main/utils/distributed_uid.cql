CREATE KEYSPACE distributed_uid WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3} AND DURABLE_WRITES = true ;

use distributed_uid

desc keyspace;

CREATE TABLE distributed_uid(
    id UUID ,
    duid bigint,
    created_at timestamp,
    uid_timestamp bigint,
    random_node_id bigint,
    epoch_date_time timestamp,
    max_node_id bigint,
    initial_bits bigint,
    total_bits bigint,
    node_id_bits bigint,
    random_id_bound bigint,
   primary key ((id, duid), created_at, epoch_date_time )
   );