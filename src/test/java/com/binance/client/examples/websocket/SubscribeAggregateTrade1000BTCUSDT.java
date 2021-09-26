package com.binance.client.examples.websocket;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.SubscriptionClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SubscribeAggregateTrade1000BTCUSDT {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
        AtomicInteger count = new AtomicInteger();
        File f = new File("/Users/fuyafeng/IdeaProjects/Binance_Futures_Java/tmp/getFutureData1000shibusdt.txt");
        Writer w = null;
        try {
            w = new FileWriter(f,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer finalW = w;
        client.subscribeAggregateTradeEvent("1000shibusdt",
                ((event) -> {
                    String writedStr = new Date().toString() +" : "+ JSONObject.toJSON(event) +"\n";
                    try {
                        finalW.append(writedStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(writedStr);

                }),
                null);

    }

}
