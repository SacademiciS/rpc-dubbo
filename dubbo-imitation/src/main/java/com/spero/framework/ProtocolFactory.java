package com.spero.framework;

import com.spero.framework.protocol.dubbo.DubboProtocol;
import com.spero.framework.protocol.http.HttpProtocol;

/**
 * 协议实现工厂
 *
 * @author wujh
 * @date 2022/9/22
 */
public class ProtocolFactory {

  public static Protocol getProtocol(String protocolName) {
    switch (protocolName) {
      case "dubbo":
        return new DubboProtocol();
      case "http":
        return new HttpProtocol();
      default:
        break;
    }
    return new HttpProtocol();
  }
}
