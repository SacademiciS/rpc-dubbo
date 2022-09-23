package com.spero.framework.protocol.dubbo;

import com.spero.framework.Invoker;
import com.spero.framework.Protocol;
import com.spero.framework.URL;
import com.spero.framework.register.LocalRegister;
import com.spero.framework.register.RemoteMapRegister;

/**
 * dubbo协议实现
 *
 * @author wujh
 * @date 2022/9/22
 */
public class DubboProtocol implements Protocol {
  @Override
  public void export(URL url) {
    LocalRegister.register(url.getInterfaceName(), url.getImplClass());
    RemoteMapRegister.regist(url.getInterfaceName(), url);
    new NettyServer().start(url.getHostname(), url.getPort());
  }

  @Override
  public Invoker refer(URL url) {
    return new DubboInvoker(url);
  }
}
