package examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.constant.PrivateConfig;

/**
 * @author : wangwanlu
 * @since : 2020/3/25, Wed
 */
public class ChangePositionSide {
  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient =
        SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);
    System.out.println(syncRequestClient.changePositionSide(true));
  }
}
