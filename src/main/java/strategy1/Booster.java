package strategy1;

import strategy1.businessEntity.HoldSide;
import strategy1.businessEntity.Position;
import strategy1.businessEntity.PositionStatus;
import strategy1.businessEntity.StepGrowth;
import com.binance.client.RequestOptions;
import com.binance.client.SubscriptionClient;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.enums.NewOrderRespType;
import com.binance.client.model.enums.OrderSide;
import com.binance.client.model.enums.OrderType;
import com.binance.client.model.enums.PositionSide;

import java.util.LinkedList;

import strategy1.constants.PrivateConfig;

public class Booster {
    /*
    运行条件：
    1.需要在目标币价稳定的时候运行
    2.当前账户需是空仓状态
     */
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        try {
            analyzer.init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Analyzer初始化错误，程序已退出");
            System.exit(1);
        }

        final String symbol = Analyzer.symbol;

        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);

        SubscriptionClient client = SubscriptionClient.create();
        client.subscribeAggregateTradeEvent( symbol,
                ((event) -> {
                    float currentPrice = event.getPrice().floatValue();
                    float currentQuantity = event.getQty().floatValue();
                    float stepGrowthRate = analyzer.calculateStepGrowthRate(currentPrice);
                    LinkedList<StepGrowth> rollingWindow = analyzer.getRollingWindow();
                    float lastStepGrowthRate = rollingWindow.getLast().getStepGrowthRate();
                    rollingWindow.poll();
                    StepGrowth stepGrowth = new StepGrowth();
                    stepGrowth.setStepGrowthRate(stepGrowthRate);
                    stepGrowth.setQuantity(currentQuantity);
                    rollingWindow.offer(stepGrowth);
                    float curSideStepGrowthTotal = analyzer.calculateSideStepGrowthRateTotal(lastStepGrowthRate);

                    //获取持仓状态，是满仓还是空仓。如果是满仓，持仓方向是什么，是做多还是做空
                    PositionStatus positionStatus = analyzer.getPositionStatus();
                    Position position = positionStatus.getPosition();
                    HoldSide holdSide = positionStatus.getHoldSide();
                    String quantity = String.valueOf(analyzer.getOrderQuantity());
                    /*
                    1.如果大于sideStepGrowthTotal就下单买入
                    2.如果小于sideStepGrowthTotal就下单卖出
                     */
                    if (Math.abs(curSideStepGrowthTotal) > analyzer.getBuySideStepGrowthTotal()){//开始暴涨或暴跌，准备买入
                        if (curSideStepGrowthTotal > 0){//开始暴涨，买入做多
                            if (position == Position.HOLD && holdSide == HoldSide.SHORT){
                                //如果反方向持仓（即开始暴涨时我的持仓是做空）了，赶紧卖
                                syncRequestClient.postOrder(symbol, OrderSide.SELL, PositionSide.SHORT, OrderType.MARKET, null,
                                        quantity, null, null, null, null, null, NewOrderRespType.RESULT);
                                positionStatus.setPosition(Position.RELEASE);
                                analyzer.setPositionStatus(positionStatus);
                            }else if (position == Position.RELEASE){
                                //如果是空仓状态，就买入做多
                                syncRequestClient.postOrder(symbol, OrderSide.BUY, PositionSide.LONG, OrderType.MARKET, null,
                                        quantity, null, null, null, null, null, NewOrderRespType.RESULT);
                                positionStatus.setPosition(Position.HOLD);
                                positionStatus.setHoldSide(HoldSide.LONG);
                                analyzer.setPositionStatus(positionStatus);
                            }
                        }else if(curSideStepGrowthTotal < 0){//开始暴跌，买入做空
                            if (position == Position.HOLD && holdSide == HoldSide.LONG){
                                //如果反方向持仓（即开始暴跌时我的持仓是做多）了，赶紧卖
                                syncRequestClient.postOrder(symbol, OrderSide.SELL, PositionSide.LONG, OrderType.MARKET, null,
                                        quantity, null, null, null, null, null, NewOrderRespType.RESULT);
                                positionStatus.setPosition(Position.RELEASE);
                                analyzer.setPositionStatus(positionStatus);
                            }else if (position == Position.RELEASE) {
                                //如果是空仓状态，就买入做空
                                syncRequestClient.postOrder(symbol, OrderSide.BUY, PositionSide.SHORT, OrderType.MARKET, null,
                                        quantity, null, null, null, null, null, NewOrderRespType.RESULT);
                                positionStatus.setPosition(Position.HOLD);
                                positionStatus.setHoldSide(HoldSide.SHORT);
                                analyzer.setPositionStatus(positionStatus);
                            }
                        }
                    }
                    if (Math.abs(curSideStepGrowthTotal) < analyzer.getSellSideStepGrowthTotal()){//开始恢复平静，准备卖出
                        if (position == Position.HOLD && holdSide == HoldSide.LONG){//卖出做多
                            syncRequestClient.postOrder(symbol, OrderSide.SELL, PositionSide.LONG, OrderType.MARKET, null,
                                    quantity, null, null, null, null, null, NewOrderRespType.RESULT);
                        }else if (position == Position.HOLD && holdSide == HoldSide.SHORT){//卖出做空
                            syncRequestClient.postOrder(symbol, OrderSide.SELL, PositionSide.SHORT, OrderType.MARKET, null,
                                    quantity, null, null, null, null, null, NewOrderRespType.RESULT);
                        }
                    }
                    analyzer.setPrePrice(currentPrice);
                }),
                null);

    }
}
