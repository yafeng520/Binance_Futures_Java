package com.binance.client.model.event;

import com.binance.client.constant.BinanceApiConstants;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table
public class AggregateTradeEvent {

  private String eventType;

  /** 事件时间，即这个交易创建的时间 */
  @Column private Long eventTime;

  private String symbol;

  @Id private Long id;

  @Column private BigDecimal price;

  @Column private BigDecimal qty;

  private Long firstId;

  private Long lastId;

  /** 成交时间，即这个交易成交的时间 */
  @Column private Long time;

  @Column private Boolean isBuyerMaker;

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public Long getEventTime() {
    return eventTime;
  }

  public void setEventTime(Long eventTime) {
    this.eventTime = eventTime;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getQty() {
    return qty;
  }

  public void setQty(BigDecimal qty) {
    this.qty = qty;
  }

  public Long getFirstId() {
    return firstId;
  }

  public void setFirstId(Long firstId) {
    this.firstId = firstId;
  }

  public Long getLastId() {
    return lastId;
  }

  public void setLastId(Long lastId) {
    this.lastId = lastId;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Boolean getIsBuyerMaker() {
    return isBuyerMaker;
  }

  public void setIsBuyerMaker(Boolean isBuyerMaker) {
    this.isBuyerMaker = isBuyerMaker;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime)
        .append("symbol", symbol)
        .append("id", id)
        .append("price", price)
        .append("qty", qty)
        .append("firstId", firstId)
        .append("lastId", lastId)
        .append("time", time)
        .append("isBuyerMaker", isBuyerMaker)
        .toString();
  }
}
