package strategy1.businessEntity;

public class PositionStatus {
    Position position;
    HoldSide holdSide;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public HoldSide getHoldSide() {
        return holdSide;
    }

    public void setHoldSide(HoldSide holdSide) {
        this.holdSide = holdSide;
    }
}
