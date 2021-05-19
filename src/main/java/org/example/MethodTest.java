package org.example;

import org.apache.storm.hooks.BaseWorkerHook;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class MethodTest {


    public void testTopology(TopologyBuilder topologyBuilder) {

        /**
         * 设置拓扑源头，可以设置多个
         */
        topologyBuilder.setSpout("",null);

        /**
         *
         */
        topologyBuilder.addWorkerHook(new BaseWorkerHook());


        /**
         *
         */
        topologyBuilder.setStateSpout("id",null);


    }

    public void testOutputFieldDeclarer(OutputFieldsDeclarer declarer) {

        /**
         * 声明定义数据流
         */
        declarer.declareStream("test",new Fields("test"));


        /**
         * 规范当前Bolt传出数据流的格式
         */
        declarer.declare(new Fields("test"));

    }




}
