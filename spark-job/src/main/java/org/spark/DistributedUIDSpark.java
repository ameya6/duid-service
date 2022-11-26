package org.spark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.data.model.response.DUIDResponse;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.spark.model.DistributedUIDResponse;
import org.springframework.web.client.RestTemplate;
import scala.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

@Log4j2
public class DistributedUIDSpark {

    public static void main(String[] args) {
        JavaSparkContext sc = sparkContext(esConfig(initConfig(), "192.168.0.103", "9200"));
        List<Long> nums = new LinkedList<>();
        LongStream.range(0, 1000000l).forEach(num -> nums.add(num));
        log.info(nums.size());
        final long MILLION = 1000000;

        String local = "http://localhost:9009/duid/detail";
        String remote = "http://192.168.0.104/duid/detail";
        String url = remote;


        JavaRDD<Long> numsRdd = sc.parallelize(nums);
        JavaRDD<String> responseRDD = numsRdd.mapPartitions(partition -> {
            RestTemplate restTemplate = new RestTemplate();
            Gson gson = new GsonBuilder().create();
            List<String> responses = new LinkedList<>();

            while (partition.hasNext()) {
                partition.next();
                LocalDateTime startTime = LocalDateTime.now();
                DistributedUIDResponse response = restTemplate.getForObject(url, DistributedUIDResponse.class);
                LocalDateTime endTime = LocalDateTime.now();

                Double processTimeInNanoSeconds = (double)Duration.between(startTime, endTime).toNanos();
                response.setProcessStartTime(startTime);
                response.setProcessEndTime(endTime);
                response.setResponseTime(processTimeInNanoSeconds / MILLION);
                responses.add(gson.toJson(response));
            }
            return responses.iterator();
        });

        JavaEsSpark.saveJsonToEs(responseRDD, "duid_responses");
    }


    public static JavaSparkContext sparkContext(SparkConf conf) {
        return new JavaSparkContext(conf);
    }

    public static SparkConf initConfig() {
         return new SparkConf()
                 .setAppName("DUIDSpark")
                 .setMaster("local[*]")
                 .set("spark.executor.cores", "12")
                 .set("spark.executor.memory", "2G");
    }

    public static SparkConf esConfig(SparkConf conf, String host, String port) {
        conf.set("spark.es.nodes",host)
                .set("spark.es.port",port)
                .set("spark.es.nodes.wan.only","true");
        return conf;
    }
}
