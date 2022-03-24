package examples.websocket;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.SubscriptionClient;

public class SubscribeSymbolTicker {

  public static void main(String[] args) {

    SubscriptionClient client = SubscriptionClient.create();

    client.subscribeSymbolTickerEvent(
        "btcusdt",
        ((event) -> {
          System.out.println(JSONObject.toJSON(event));
        }),
        null);
  }
}