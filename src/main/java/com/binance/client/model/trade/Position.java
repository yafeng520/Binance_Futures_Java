package com.binance.client.model.trade;

import com.binance.client.constant.BinanceApiConstants;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table
public class Position {

  private Boolean isolated;
  /** 仓位的杠杆 */
  @Column private BigDecimal leverage;
  /** 仓位的初始保证金 */
  @Column private BigDecimal initialMargin;

  private BigDecimal maintMargin;

  private BigDecimal openOrderInitialMargin;

  private BigDecimal positionInitialMargin;
  @Id private String symbol;

  private BigDecimal unrealizedProfit;

  private String entryPrice;

  private String maxNotional;
  /** 仓位的方向，是做多还是做空 */
  @Column private String positionSide;
  /** 仓位的数量，正数是做多，负数是做空 */
  @Column private BigDecimal positionAmt;

  @Column private Long updateTime;

  public Boolean getIsolated() {
    return isolated;
  }

  public void setIsolated(Boolean isolated) {
    this.isolated = isolated;
  }

  public BigDecimal getLeverage() {
    return leverage;
  }

  public void setLeverage(BigDecimal leverage) {
    this.leverage = leverage;
  }

  public BigDecimal getInitialMargin() {
    return initialMargin;
  }

  public void setInitialMargin(BigDecimal initialMargin) {
    this.initialMargin = initialMargin;
  }

  public BigDecimal getMaintMargin() {
    return maintMargin;
  }

  public void setMaintMargin(BigDecimal maintMargin) {
    this.maintMargin = maintMargin;
  }

  public BigDecimal getOpenOrderInitialMargin() {
    return openOrderInitialMargin;
  }

  public void setOpenOrderInitialMargin(BigDecimal openOrderInitialMargin) {
    this.openOrderInitialMargin = openOrderInitialMargin;
  }

  public BigDecimal getPositionInitialMargin() {
    return positionInitialMargin;
  }

  public void setPositionInitialMargin(BigDecimal positionInitialMargin) {
    this.positionInitialMargin = positionInitialMargin;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public BigDecimal getUnrealizedProfit() {
    return unrealizedProfit;
  }

  public void setUnrealizedProfit(BigDecimal unrealizedProfit) {
    this.unrealizedProfit = unrealizedProfit;
  }

  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  public String getEntryPrice() {
    return entryPrice;
  }

  public void setEntryPrice(String entryPrice) {
    this.entryPrice = entryPrice;
  }

  public String getMaxNotional() {
    return maxNotional;
  }

  public void setMaxNotional(String maxNotional) {
    this.maxNotional = maxNotional;
  }

  public String getPositionSide() {
    return positionSide;
  }

  public void setPositionSide(String positionSide) {
    this.positionSide = positionSide;
  }

  public BigDecimal getPositionAmt() {
    return positionAmt;
  }

  public void setPositionAmt(BigDecimal positionAmt) {
    this.positionAmt = positionAmt;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("initialMargin", initialMargin)
        .append("maintMargin", maintMargin)
        .append("openOrderInitialMargin", openOrderInitialMargin)
        .append("positionInitialMargin", positionInitialMargin)
        .append("symbol", symbol)
        .append("unrealizedProfit", unrealizedProfit)
        .append("entryPrice", entryPrice)
        .append("maxNotional", maxNotional)
        .append("positionSide", positionSide)
        .append("isolated", isolated)
        .append("leverage", leverage)
        .append("positionAmt", positionAmt)
        .toString();
  }
}
