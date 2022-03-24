package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/** buy, sell. */
public enum TradeDirection {
  BUY("buy"),
  SELL("sell");

  private static final EnumLookup<TradeDirection> lookup = new EnumLookup<>(TradeDirection.class);
  private final String code;

  TradeDirection(String side) {
    this.code = side;
  }

  public static TradeDirection lookup(String name) {
    return lookup.lookup(name);
  }

  @Override
  public String toString() {
    return code;
  }
}
