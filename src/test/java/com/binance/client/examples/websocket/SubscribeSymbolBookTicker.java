package com.binance.client.examples.websocket;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.SubscriptionClient;

public class SubscribeSymbolBookTicker {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeSymbolBookTickerEvent("btcusdt", ((event) -> {
            System.out.println(JSONObject.toJSON(event));
        }), null);

    }

}
