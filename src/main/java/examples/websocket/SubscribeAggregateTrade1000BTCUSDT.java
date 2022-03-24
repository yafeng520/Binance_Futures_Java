package examples.websocket;

import com.binance.client.SubscriptionClient;

public class SubscribeAggregateTrade1000BTCUSDT {

  public static void main(String[] args) {
    SubscriptionClient client = SubscriptionClient.create();
    String symbol = "adausdt";
    client.subscribeAggregateTradeEvent(
        symbol,
        ((event) -> {
          System.out.println(event);
        }),
        ((error) -> {
          //        第七步 关闭资源
          System.out.println("订阅资源出错：" + error);
          System.exit(1);
        }));

    /*
    SubscriptionClient client = SubscriptionClient.create();
    //        第一步 到src下面找到hibernate.cfg.xml，加载到内存中，成为Configuration对象
    Configuration cfg = new Configuration();
    cfg.configure();

    //        第二步 读取hibernate核心配置文件内容，创建sessionFactory
    // 在过程中，根据映射关系，在配置数据库里面把表创建
    SessionFactory sessionFactory = cfg.buildSessionFactory();

    //        第三步 使用SessionFactory创建session对象，创建数据库连接
    Session session = sessionFactory.openSession();

    //        第四步 开启事务
    AtomicReference<Transaction> tx = new AtomicReference<>(session.beginTransaction());
    //        AggregateTradeEvent a1 = new AggregateTradeEvent();
    //        a1.setId(Long.valueOf("1"));
    //        session.save(a1);
    //        tx.commit();
    //
    //        tx = session.beginTransaction();
    //        AggregateTradeEvent a2 = new AggregateTradeEvent();
    //        a2.setId(Long.valueOf("2"));
    //        session.save(a2);
    //        tx.commit();
    //
    //        session.close();
    //        sessionFactory.close();
    String symbol = "1000shibusdt";
    AtomicInteger transactionCommitCounter = new AtomicInteger();
    client.subscribeAggregateTradeEvent(
        symbol,
        ((event) -> {
          //        第五步 写具体逻辑 crud操作
          session.save(event);
          transactionCommitCounter.getAndIncrement();
          //        第六步 提交事务
          if (transactionCommitCounter.get() == 100) {
            tx.get().commit();
            transactionCommitCounter.set(0);
            tx.set(session.beginTransaction());
          }
        }),
        ((error) -> {
          //        第七步 关闭资源
          System.out.println("订阅资源出错：" + error);
          System.exit(1);
        }));

     */
  }
}
