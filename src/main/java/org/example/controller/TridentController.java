package org.example.controller;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.thrift.TException;
import org.apache.storm.thrift.transport.TTransportException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentState;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.builtin.Count;
import org.apache.storm.trident.operation.builtin.FilterNull;
import org.apache.storm.trident.operation.builtin.MapGet;
import org.apache.storm.trident.operation.builtin.Sum;
import org.apache.storm.trident.testing.CountAsAggregator;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.trident.testing.MemoryMapState;
import org.apache.storm.trident.testing.Split;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.DRPCClient;
import org.apache.storm.utils.Utils;
import org.example.bolt.MySplit;
import org.example.bolt.TridentFunction;

import java.util.HashMap;
import java.util.Map;

public class TridentController {

    public static void main(String[] args) throws TException {

        FixedBatchSpout fixedBatchSpout = new FixedBatchSpout(new Fields("meteData"),3
                , new Values("the cow jumped over the moon")
                , new Values("the man went to the store and bought some candy")
                , new Values("four score and seven years ago")
                , new Values("how many apples can you eat"));


        fixedBatchSpout.setCycle(true);

        TridentTopology topology = new TridentTopology();

        TridentState tridentState = topology.newStream("meteSpout", fixedBatchSpout)
                .each(new Fields("meteData"), new MySplit(), new Fields("words"))
                .groupBy(new Fields("words"))
                .persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
                .parallelismHint(6);



        /*Stream aggregate = topology.newDRPCStream("words")
                .each(new Fields("args"), new Split(), new Fields("word"))
                .groupBy(new Fields("word"))
                .stateQuery(tridentState, new Fields("word"), new MapGet(), new Fields("count"))
                .each(new Fields("count"), new FilterNull())
                .aggregate(new Fields("count"), new Sum(), new Fields("sum"));



        String s = aggregate.toString();
        Stream sum = aggregate.flatMap(new TridentFunction(), new Fields("sum"));
        System.out.println("s = " + s);
*/

        LocalCluster cluster = new LocalCluster();

        Config config = new Config();
        config.setDebug(true);
        cluster.submitTopology("test", config, topology.build());
        Utils.sleep(1800000);
        cluster.killTopology("test");
        cluster.shutdown();

        /*DRPCClient client = new DRPCClient(conf,"127.0.0.1", 3772);
        System.out.println(client.execute("word","cat dog the man"));*/


    }


}
