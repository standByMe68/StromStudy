package org.example.controller;

import clojure.lang.Compiler;
import com.google.common.collect.LinkedHashMultimap;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.OutputFieldsGetter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;
import org.example.bolt.Test1Bolt;
import org.example.bolt.Test2Bolt;
import org.example.bolt.Test3Bolt;
import org.example.config.KafkaConfig;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class StormController {

    public static void main(String[] args) {

        /**
         * 构建Spout
         */
        Properties kafkaProperties = KafkaConfig.getKafkaProperties();
        KafkaSpoutConfig.Builder<String, String> builder = KafkaSpoutConfig.builder(kafkaProperties.getProperty("bootstrap.servers"), kafkaProperties.getProperty("DCTopic"));
        KafkaSpoutConfig<String, String> build = new KafkaSpoutConfig<>(builder);
        KafkaSpout<String, String> kafkaSpout = new KafkaSpout<>(build);


        /**
         * 构建拓扑
         */
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("kafka-spout", kafkaSpout).setMaxSpoutPending(10);
        topologyBuilder.setBolt("test-1", new Test1Bolt(),1).shuffleGrouping("kafka-spout").setNumTasks(1);
        topologyBuilder.setBolt("test-3", new Test3Bolt(), 1).shuffleGrouping("test-1");
        topologyBuilder.setBolt("test-2", new Test2Bolt(), 1).shuffleGrouping("test-1","test-11111");



        //进行数据执行
        LocalCluster cluster = new LocalCluster();
        Config config = new Config();
        config.setDebug(true);
        cluster.submitTopology("test", config, topologyBuilder.createTopology());
        Utils.sleep(1800000);
        cluster.killTopology("test");
        cluster.shutdown();

    }





}
