package com.binance.client.model.trade;

import com.binance.client.constant.BinanceApiConstants;
import com.binance.client.constant.Constants;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.ToStringBuilder;

@MappedSuperclass
public class Order implements Serializable {
  @Id private Long orderId;
  @Id private String symbol;

  /** cumulative quote(累计报价，即最终的成交金额)，即此订单总共买/卖了多少钱。0表示此订单为爆仓订单 */
  @Column private BigDecimal cumQuote;
  /** 成交量 */
  @Column(precision = Constants.QUANTITY_PRECISION, scale = Constants.QUANTITY_SCALE)
  private BigDecimal executedQty;
  /** 平均成交价 */
  @Column(precision = Constants.PRICE_PRECISION, scale = Constants.PRICE_SCALE)
  private BigDecimal avgPrice;
  /** 原始委托数量 */
  @Transient private BigDecimal origQty;

  @Transient private String clientOrderId;
  @Transient private BigDecimal price;
  @Transient private Boolean reduceOnly;
  /**
   * 值有BUY、SELL、LIQUIDATION（爆仓）
   */
  @Column private String side;

  @Column private String positionSide;
  @Transient private String status;
  @Transient private BigDecimal stopPrice;

  @Transient private String timeInForce;
  @Transient private String type;
  @Column private Timestamp time;
  @Transient private String workingType;

  public String getClientOrderId() {
    return clientOrderId;
  }

  public void setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
  }

  public BigDecimal getCumQuote() {
    return cumQuote;
  }

  public void setCumQuote(BigDecimal cumQuote) {
    this.cumQuote = cumQuote;
  }

  public BigDecimal getExecutedQty() {
    return executedQty;
  }

  public void setExecutedQty(BigDecimal executedQty) {
    this.executedQty = executedQty;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public BigDecimal getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(BigDecimal avgPrice) {
    this.avgPrice = avgPrice;
  }

  public BigDecimal getOrigQty() {
    return origQty;
  }

  public void setOrigQty(BigDecimal origQty) {
    this.origQty = origQty;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Boolean getReduceOnly() {
    return reduceOnly;
  }

  public void setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
  }

  public String getSide() {
    return side;
  }

  public void setSide(String side) {
    this.side = side;
  }

  public String getPositionSide() {
    return positionSide;
  }

  public void setPositionSide(String positionSide) {
    this.positionSide = positionSide;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigDecimal getStopPrice() {
    return stopPrice;
  }

  public void setStopPrice(BigDecimal stopPrice) {
    this.stopPrice = stopPrice;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getTimeInForce() {
    return timeInForce;
  }

  public void setTimeInForce(String timeInForce) {
    this.timeInForce = timeInForce;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  public String getWorkingType() {
    return workingType;
  }

  public void setWorkingType(String workingType) {
    this.workingType = workingType;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("clientOrderId", clientOrderId)
        .append("cumQuote", cumQuote)
        .append("executedQty", executedQty)
        .append("orderId", orderId)
        .append("origQty", origQty)
        .append("price", price)
        .append("reduceOnly", reduceOnly)
        .append("side", side)
        .append("positionSide", positionSide)
        .append("status", status)
        .append("stopPrice", stopPrice)
        .append("symbol", symbol)
        .append("timeInForce", timeInForce)
        .append("type", type)
        .append("time", time)
        .append("workingType", workingType)
        .toString();
  }
}
