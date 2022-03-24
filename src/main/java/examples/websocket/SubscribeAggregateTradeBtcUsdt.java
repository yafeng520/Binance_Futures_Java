package examples.websocket;

import com.binance.client.SubscriptionClient;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;

public class SubscribeAggregateTradeBtcUsdt {

  public static void main(String[] args) {

    SubscriptionClient client = SubscriptionClient.create();
    AtomicInteger count = new AtomicInteger();
    File f =
        new File("/Users/fuyafeng/IdeaProjects/Binance_Futures_Java/tmp/getFutureDataBtcUsdt.txt");
    Writer w = null;
    try {
      w = new FileWriter(f, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Writer finalW = w;
    client.subscribeAggregateTradeEvent(
        "btcstusdt",
        ((event) -> {
          count.getAndIncrement();
          String writedStr =
              "第" + count.get() + "条：" + event.getPrice() + ", 时间：" + event.getTime() + "\n";
          //                    try {
          //                        finalW.write(writedStr);
          //                    } catch (IOException e) {
          //                        e.printStackTrace();
          //                    }
          System.out.println(writedStr);
        }),
        null);
  }
}
