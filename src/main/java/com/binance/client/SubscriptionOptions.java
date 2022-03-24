package com.binance.client;

import com.binance.client.exception.BinanceApiException;
import java.net.URI;

/** The configuration for the subscription APIs */
public class SubscriptionOptions {

  private String uri = "wss://api.binance.pro/";
  private boolean isAutoReconnect = true;
  /** websockets的连接在这个时间内没有收到回复，则会被系统认为服务器没回应了，会尝试重连。单位（ms），书写格式：时*分*秒*1000 */
  private int receiveLimitMs = 15 * 60 * 1000;

  private int connectionDelayOnFailure = 15;

  public SubscriptionOptions(SubscriptionOptions options) {
    this.uri = options.uri;
    this.isAutoReconnect = options.isAutoReconnect;
    this.receiveLimitMs = options.receiveLimitMs;
    this.connectionDelayOnFailure = options.connectionDelayOnFailure;
  }

  public SubscriptionOptions() {}

  public boolean isAutoReconnect() {
    return isAutoReconnect;
  }

  /**
   * When the connection lost is happening on the subscription line, specify whether the client
   * reconnect to server automatically.
   *
   * <p>The connection lost means:
   *
   * <ul>
   *   <li>Caused by network problem
   *   <li>The connection close triggered by server (happened every 24 hours)
   *   <li>No any message can be received from server within a specified time, see {@link
   *       #setReceiveLimitMs(int)} (int)}
   * </ul>
   *
   * @param isAutoReconnect The boolean flag, true for enable, false for disable
   * @return Return self for chaining
   */
  public SubscriptionOptions setAutoReconnect(boolean isAutoReconnect) {
    this.isAutoReconnect = isAutoReconnect;
    return this;
  }

  public int getReceiveLimitMs() {
    return receiveLimitMs;
  }

  /**
   * Set the receive limit in millisecond. If no message is received within this limit time, the
   * connection will be disconnected.
   *
   * @param receiveLimitMs The receive limit in millisecond.
   */
  public void setReceiveLimitMs(int receiveLimitMs) {
    this.receiveLimitMs = receiveLimitMs;
  }

  public int getConnectionDelayOnFailure() {
    return connectionDelayOnFailure;
  }

  /**
   * If auto reconnect is enabled, specify the delay time before reconnect.
   *
   * @param connectionDelayOnFailure The delay time in second.
   */
  public void setConnectionDelayOnFailure(int connectionDelayOnFailure) {
    this.connectionDelayOnFailure = connectionDelayOnFailure;
  }

  public String getUri() {
    return uri;
  }

  /**
   * Set the URI for subscription.
   *
   * @param uri The URI name like "wss://api.binance.pro".
   */
  public void setUri(String uri) {
    try {
      URI u = new URI(uri);
      this.uri = u.toString();
    } catch (Exception e) {
      throw new BinanceApiException(
          BinanceApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
    }
    this.uri = uri;
  }
}
