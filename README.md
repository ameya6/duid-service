## Distributed Unique ID Generator

### Description

*   64 bit unique distributed ID
*   numerical and ordered sequentially based on timestamp

### Implementation

*   The 64 bit number is divided into 3 parts.
    *   41 bit containing timestamp
    *   13 bits containing a random number(hash code of MAC address can be used)
    *   10 bits containing a random number(hash code of Thread ID can be used)
*   Different logic containing 4 parts can be implemented.
    *   40 bit containing timestamp
    *   10 bit hash code of MAC address
    *   10 bit hash code of Thread ID
    *   4 bits of random(sequential) number
*   **41 bit**
    *   Maintain an epoch timestamp. For example, 1st Jan 2000
    *   Left shift the timestamp by 23 bits(64 bit - 41 bit)
    *   The result will be a long number.
*   **13 bit**
    *   Generate a random number
    *   Left shift it with 10 bits(23 bit - 13 bit)
    *   Or it with the above result.
*   **10 bit**
    *   Generate a random number
    *   Left shift it with 3 bits(13 bit - 10 bit)
    *   Or it with the above result.

### Tech Stack

*   **Language**
    *   Java 11
*   **API**
    *   Spring Boot
        *   Spring Boot Web
        *   Spring Boot Webflux
    *   Reactor Core
    *   Lombok
    *   Log4j2
*   **Database**
    *   TimescaleDB
        *   Logging events
    *   Elasticsearch
        *   Logging response post spark testing
*   **Deployment**
    *   Docker
    *   Kubernetes
*   **Load Testing tools**
    *   JMeter
    *   Apache Spark

### Architecture Diagram

![](https://33333.cdn.cke-cs.com/kSW7V9NHUXugvhoQeFaf/images/dd3d6437787ae07d7a0d131ef465abfbf0270b20e1cc18b0.jpeg)

### Load Tests Result

#### Spark Test

*   Tested with 1M records.
*   Was able to process on average 6K records per second.
*   With the average process time of 0.01 ms

| **request\_time** | **requests\_per\_second** | **avg\_process\_time\_in\_ms** |
| --- | --- | --- |
| 2022-11-25 22:08:49.000 | 6172 | 0.01448639 |
| 2022-11-25 22:08:48.000 | 6432 | 0.017124378 |
| 2022-11-25 22:08:47.000 | 6611 | 0.014892301 |
| 2022-11-25 22:08:46.000 | 6737 | 0.014016921 |
| 2022-11-25 22:08:45.000 | 6567 | 0.014871783 |
| 2022-11-25 22:08:44.000 | 6698 | 0.014204688 |
| 2022-11-25 22:08:43.000 | 6840 | 0.014086257 |
| 2022-11-25 22:08:42.000 | 5946 | 0.015313152 |
| 2022-11-25 22:08:41.000 | 6706 | 0.02184909 |
| 2022-11-25 22:08:40.000 | 6180 | 0.014835599 |
| 2022-11-25 22:08:39.000 | 5366 | 0.01766325 |
| 2022-11-25 22:08:38.000 | 6765 | 0.014550185 |
| 2022-11-25 22:08:37.000 | 5908 | 0.016972749 |
| 2022-11-25 22:08:36.000 | 6816 | 0.018905076 |
| 2022-11-25 22:08:35.000 | 5386 | 0.015349796 |
| 2022-11-25 22:08:34.000 | 7191 | 0.015873731 |
| 2022-11-25 22:08:33.000 | 6407 | 0.016434837 |
| 2022-11-25 22:08:32.000 | 5987 | 0.015605646 |
| 2022-11-25 22:08:31.000 | 7213 | 0.013847359 |
| 2022-11-25 22:08:30.000 | 7125 | 0.012938246 |

### References

*   [Distributed ID Generator](https://towardsdatascience.com/ace-the-system-design-interview-distributed-id-generator-c65c6b568027)
*   [How Do Tech Giants Design and Implement a Distributed ID Generator](https://medium.com/geekculture/how-do-tech-giants-design-and-implement-a-distributed-id-generator-bd618803035f)
*   [What Are Snowflake IDs](https://betterprogramming.pub/uuid-generation-snowflake-identifiers-unique-2aed8b1771bc)
