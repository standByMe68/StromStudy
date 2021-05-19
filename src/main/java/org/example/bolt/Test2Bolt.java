package org.example.bolt;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class Test2Bolt implements IBasicBolt {

    @Override
    public void prepare(Map map, TopologyContext topologyContext) {
    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String data = (String)tuple.getValueByField("data");
        System.out.println("test-2 执行阶段:"+data);
        basicOutputCollector.emit(new Values("test"));
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        System.out.println("test-2 域");
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
