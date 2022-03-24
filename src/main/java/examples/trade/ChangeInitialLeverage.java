package examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.constant.PrivateConfig;

public class ChangeInitialLeverage {
  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient =
        SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);
    System.out.println(syncRequestClient.changeInitialLeverage("BTCUSDT", 1));
  }
}