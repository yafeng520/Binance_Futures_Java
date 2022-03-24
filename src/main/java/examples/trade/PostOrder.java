package examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.constant.PrivateConfig;
import com.binance.client.model.enums.NewOrderRespType;
import com.binance.client.model.enums.OrderSide;
import com.binance.client.model.enums.OrderType;
import com.binance.client.model.enums.PositionSide;

public class PostOrder {
  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient =
        SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);
    //        System.out.println(syncRequestClient.postOrder("BTCUSDT", OrderSide.SELL,
    // PositionSide.BOTH, OrderType.LIMIT, TimeInForce.GTC,
    //                "1", "1", null, null, null, null));

    // place dual position side order.
    // Switch between dual or both position side, call:
    // examples.trade.ChangePositionSide
    System.out.println(
        syncRequestClient.postOrder(
            "1000SHIBUSDT",
            OrderSide.BUY,
            PositionSide.LONG,
            OrderType.MARKET,
            null,
            "9000",
            null,
            null,
            null,
            null,
            null,
            NewOrderRespType.RESULT));
  }
}
