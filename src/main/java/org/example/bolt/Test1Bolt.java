package org.example.bolt;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class Test1Bolt  implements IBasicBolt {

    /**
     * 执行前的准备工作
     * @param map
     * @param topologyContext
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext) {
        System.out.println("test-1 准备阶段");
    }

    /**
     * 执行逻辑
     * @param tuple
     * @param basicOutputCollector
     */
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        System.out.println("test-1 执行阶段");
    }

    /**
     * 执行后逻辑
     */
    @Override
    public void cleanup() {
        System.out.println("test-1 结束阶段");
    }

    /**
     * 创建一个数据流
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        System.out.println("test-1 创建数据域阶段");
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
