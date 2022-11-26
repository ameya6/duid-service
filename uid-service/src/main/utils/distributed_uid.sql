DROP TABLE distributed_uid

CREATE TABLE distributed_uid(
    id UUID  NOT null default gen_random_uuid(),
    duid bigint  not null,
    created_at TIMESTAMPTZ  NOT null default now(),
    saved_at TIMESTAMPTZ  NOT null default now(),
    uid_timestamp bigint,
    random_node_id bigint,
    epoch_date_time timestamp,
    max_node_id bigint,
    initial_bits bigint,
    total_bits bigint,
    node_id_bits bigint,
    random_id_bound bigint,
    random_number bigint,
    start_time TIMESTAMPTZ  NOT null,
    end_time TIMESTAMPTZ  NOT null,
    process_time_in_millis double precision,
    unique(duid, created_at)
   );

SELECT create_hypertable('distributed_uid', 'created_at');

-- Query for requests, process time per second
select date_trunc('second', created_at::timestamp) as request_time,
count(*) as requests_per_second,
avg(process_time_in_millis) as avg_process_time_in_ms
from distributed_uid
group by request_time
order by request_time desc