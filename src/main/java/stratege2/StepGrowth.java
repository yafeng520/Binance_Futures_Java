package stratege2;

public class StepGrowth {
    StepGrowthDirection stepGrowthDirection;//当前价格减去前一个价格是正的还是负的
    private float quantity;//当前价格成交的数量

    public StepGrowthDirection getStepGrowthDirection() {
        return stepGrowthDirection;
    }

    public void setStepGrowthDirection(StepGrowthDirection stepGrowthDirection) {
        this.stepGrowthDirection = stepGrowthDirection;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
