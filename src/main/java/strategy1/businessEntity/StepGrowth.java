package strategy1.businessEntity;

public class StepGrowth {
    private float stepGrowthRate;//当前价格相比前一个价格的增长率
    private float quantity;//当前价格成交的数量

    public float getStepGrowthRate() {
        return stepGrowthRate;
    }

    public void setStepGrowthRate(float stepGrowthRate) {
        this.stepGrowthRate = stepGrowthRate;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
