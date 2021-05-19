package org.example.bolt;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.testing.Split;
import org.apache.storm.trident.tuple.TridentTuple;

public class MySplit extends Split {

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {
        super.execute(tuple, collector);
        System.out.println("tuple:"+tuple.toString());
        System.out.println("这个我的split类");
    }
}
