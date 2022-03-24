package examples.market;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.constant.PrivateConfig;
import com.binance.client.model.market.AggregateTrade;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class GetAggregateTrades_BTCUSDT {
  public static void main(String[] args) {
    RequestOptions options = new RequestOptions();
    SyncRequestClient syncRequestClient =
        SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);
    int count = 0;
    File f =
        new File(
            "/Users/fuyafeng/IdeaProjects/Binance_Futures_Java/tmp/getFutureDataBtcUsdtByRestApi.txt");
    Writer w = null;
    try {
      w = new FileWriter(f, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (true) {
      List<AggregateTrade> AggregateTrades_btcusdt =
          syncRequestClient.getAggregateTrades("BTCUSDT", null, null, null, 1);
      count++;
      AggregateTrade at = AggregateTrades_btcusdt.get(0);
      String writedStr =
          "第"
              + count
              + "条：price:"
              + at.getPrice()
              + ", qty："
              + at.getQty()
              + ",time:"
              + at.getTime()
              + "\n";
      System.out.println(writedStr);
      try {
        w.write(writedStr);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
