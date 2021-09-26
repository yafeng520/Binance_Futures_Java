package com.binance.client.examples.websocket;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.SubscriptionClient;

public class SubscribeDiffDepth {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeDiffDepthEvent("1000shibusdt", ((event) -> {
            System.out.println(JSONObject.toJSON(event));
        }), null);

    }

}
