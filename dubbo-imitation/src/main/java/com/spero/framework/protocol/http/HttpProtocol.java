package com.spero.framework.protocol.http;

import com.spero.framework.Invoker;
import com.spero.framework.Protocol;
import com.spero.framework.URL;
import com.spero.framework.register.LocalRegister;
import com.spero.framework.register.RemoteMapRegister;

/**
 * http协议实现
 *
 * @author wujh
 * @date 2022/9/22
 */
public class HttpProtocol implements Protocol {
  @Override
  public void export(URL url) {
    // 本地注册
    LocalRegister.register(url.getInterfaceName(), url.getImplClass());
    // 注册中心注册
    RemoteMapRegister.regist(url.getInterfaceName(), url);
    // 启用tomcat
    new HttpServer().start(url.getHostname(), url.getPort());
  }

  @Override
  public Invoker refer(URL url) {
    return new HttpInvoker(url);
  }
}
