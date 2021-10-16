package strategy1;

import strategy1.businessEntity.StepGrowth;
import strategy1.businessEntity.Position;
import strategy1.businessEntity.PositionStatus;
import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.market.AggregateTrade;
import com.binance.client.model.trade.AccountBalance;
import strategy1.constants.PrivateConfig;

import java.util.LinkedList;
import java.util.List;

public class Analyzer {

    public static final String symbol = "1000shibusdt";
    final int rollingWindowSize = 300;
    final float buySideStepGrowthTotal = 0.013333333333333f;//在rollingWindow总增长率为此值时买入
    final float sellSideStepGrowthTotal = 0.003333333333333f;//在rollingWindow总增长率为此值时卖出

    float prePrice;//在main函数中是前一个价格，在此类中是init()方法执行完后的最新价格
    LinkedList<StepGrowth> rollingWindow;
    PositionStatus positionStatus;
    float balance;//保证金，即余额
    float orderQuantity;//要下单的数量
    float sideStepGrowthRateTotal = 0;

    RequestOptions options;
    SyncRequestClient syncRequestClient;

    /*
    public static void main(String[] args) {
        Queue<StepGouth> rollingWindow = new LinkedList<>();
        int rollingWindowSize = 20;
        double stepRate = 0.000265005962634;
        double buyRate = 0.013333333333333;
        double sideStepGrowthProportionBuyLine = 0.7;
        double sideStepGrowthProportion;

    }
    */
    public void init() throws Exception {
        options = new RequestOptions();
        syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);

        initPRS();
        initPositionStatus();
        initBalance();
        initOrderQuantity();
    }

    private void initBalance() throws Exception {
        List<AccountBalance> accountBalances = syncRequestClient.getBalance();
        for (AccountBalance accountBalance : accountBalances) {
            if (accountBalance.getAsset().equals("USDT")){
                balance = accountBalance.getBalance().floatValue();
                break;
            }
        }
        if (balance < 1){
            throw new Exception("账户余额不足（小于1）！");
        }
    }

    private void initPositionStatus() {
        positionStatus = new PositionStatus();
        positionStatus.setPosition(Position.RELEASE);
    }

    //init prePrice, rollingWindow and sideStepGrowthRateTotal
    private void initPRS() {
        List<AggregateTrade> aggregateTrades = syncRequestClient.getAggregateTrades(symbol, null, null, null, rollingWindowSize+1);
        StepGrowth stepGrowth;
        rollingWindow = new LinkedList<>();
        for (int i=1; i<aggregateTrades.size(); i++) {
            stepGrowth = new StepGrowth();
            float preValue = aggregateTrades.get(i-1).getPrice().floatValue();
            float curValue = aggregateTrades.get(i).getPrice().floatValue();
            float stepGrowthRate = (curValue-preValue)/preValue;
            stepGrowth.setStepGrowthRate(stepGrowthRate);
            stepGrowth.setQuantity(aggregateTrades.get(i).getQty().floatValue());
            rollingWindow.offer(stepGrowth);
            sideStepGrowthRateTotal += stepGrowthRate;
        }
        prePrice = aggregateTrades.get(aggregateTrades.size()-1).getPrice().floatValue();
    }

    private void initOrderQuantity() {
        float totalQuantity = 0;
        for (StepGrowth stepGrowth : rollingWindow) {
            totalQuantity += stepGrowth.getQuantity();
        }
        float averageQuantity = totalQuantity/rollingWindowSize;

        if (prePrice*averageQuantity > balance){//如果要下单的总额大于余额，就用余额all in
            orderQuantity = balance/prePrice*0.99f;//因为币价还在持续上涨，这里乘以0.99是，少买点，避免下单时购买数量太大导致余额不够
        }else {//如果要下单的总额小于余额，就下总额的单就可以了
            orderQuantity = averageQuantity;
        }
    }

    public float calculateStepGrowthRate(float currentPrice) {
        return (currentPrice-prePrice)/prePrice;
    }

    public float calculateSideStepGrowthRateTotal(float lastStepGrowthRate) {
        return sideStepGrowthRateTotal + rollingWindow.peek().getStepGrowthRate() - lastStepGrowthRate;
    }

    public float getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(float prePrice) {
        this.prePrice = prePrice;
    }

    public LinkedList<StepGrowth> getRollingWindow() {
        return rollingWindow;
    }

    public void setRollingWindow(LinkedList<StepGrowth> rollingWindow) {
        this.rollingWindow = rollingWindow;
    }

    public PositionStatus getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(PositionStatus positionStatus) {
        this.positionStatus = positionStatus;
    }

    public float getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(float orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public float getBuySideStepGrowthTotal() {
        return buySideStepGrowthTotal;
    }

    public float getSellSideStepGrowthTotal() {
        return sellSideStepGrowthTotal;
    }
}
