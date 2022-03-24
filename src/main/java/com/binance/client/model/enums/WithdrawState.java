package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/** withdraw, deposit. */
public enum WithdrawState {
  SUBMITTED("submitted"),
  REEXAMINE("reexamine"),
  CANCELED("canceled"),
  PASS("pass"),
  REJECT("reject"),
  PRETRANSFER("pre-transfer"),
  WALLETTRANSFER("wallet-transfer"),
  WALEETREJECT("wallet-reject"),
  CONFIRMED("confirmed"),
  CONFIRMERROR("confirm-error"),
  REPEALED("repealed");

  private static final EnumLookup<WithdrawState> lookup = new EnumLookup<>(WithdrawState.class);
  private final String code;

  WithdrawState(String code) {
    this.code = code;
  }

  public static WithdrawState lookup(String name) {
    return lookup.lookup(name);
  }

  @Override
  public String toString() {
    return code;
  }
}
