package com.spero.provider;

import com.spero.framework.Protocol;
import com.spero.framework.ProtocolFactory;
import com.spero.framework.URL;
import com.spero.provider.impl.HelloServiceImpl;
import com.spero.provider.service.HelloService;

/**
 * 服务提供者
 * @author wujh
 * @date 2022/9/22
 */
public class Provider {
  public static void main(String[] args) {
    String protocolName = System.getProperty("protocol");
    URL url = new URL(protocolName, "localhost", 8080, HelloService.class.getName(),
        HelloServiceImpl.class);
    Protocol protocol = ProtocolFactory.getProtocol(protocolName);
    protocol.export(url);
  }
}
