package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

public enum QueryDirection {
  PREV("prev"),
  NEXT("next");

  private static final EnumLookup<QueryDirection> lookup = new EnumLookup<>(QueryDirection.class);
  private final String code;

  QueryDirection(String code) {
    this.code = code;
  }

  public static QueryDirection lookup(String name) {
    return lookup.lookup(name);
  }

  @Override
  public String toString() {
    return code;
  }
}
