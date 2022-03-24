package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/** created, accrual, cleared, invalid. */
public enum LoanOrderStates {
  CREATED("created"),
  ACCRUAL("accrual"),
  CLEARED("cleared"),
  INVALID("invalid");

  private static final EnumLookup<LoanOrderStates> lookup = new EnumLookup<>(LoanOrderStates.class);
  private final String code;

  LoanOrderStates(String state) {
    this.code = state;
  }

  public static LoanOrderStates lookup(String name) {
    return lookup.lookup(name);
  }

  @Override
  public String toString() {
    return code;
  }
}
