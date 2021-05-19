package org.example.bolt;

import org.apache.storm.trident.operation.FlatMapFunction;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Values;

public class TridentFunction implements FlatMapFunction {
    @Override
    public Iterable<Values> execute(TridentTuple tridentTuple) {

        String sum = tridentTuple.getStringByField("sum");
        System.out.println(sum);
        return null;
    }
}
