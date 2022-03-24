package examples.trade;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.constant.PrivateConfig;

public class GetAccountInformation {
  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient =
        SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);
    String jsonResult =
        JSONObject.toJSONString(syncRequestClient.getAccountInformation().getPositions());
    //        syncRequestClient.changePositionSide(false);
    System.out.println(jsonResult);
  }
}
