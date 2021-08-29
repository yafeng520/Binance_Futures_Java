package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;

public class SubscribeAggregateTrade {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
        AtomicInteger count = new AtomicInteger();
        File f = new File("/Users/fuyafeng/IdeaProjects/Binance_Futures_Java/tmp/getFutureDataTest.txt");
        Writer w = null;
        try {
            w = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer finalW = w;
        client.subscribeAggregateTradeEvent("btcusdt",
                ((event) -> {
                    count.getAndIncrement();
                    String writedStr = "第"+count.get()+"条："+event;
                    try {
                        finalW.write(writedStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }),
                null);

    }

}
