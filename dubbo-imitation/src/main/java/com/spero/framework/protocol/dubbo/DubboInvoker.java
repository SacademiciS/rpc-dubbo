package com.spero.framework.protocol.dubbo;

import com.spero.framework.Invoker;
import com.spero.framework.URL;
import com.spero.framework.Invocation;

/**
 * dobbo协议调用者实现
 *
 * @author wujh
 * @date 2022/9/23
 */
public class DubboInvoker implements Invoker {

  private URL url;

  public DubboInvoker(URL url) {
    this.url = url;
  }

  @Override
  public String invoke(Invocation invocation) {
    NettyClient nettyClient = new NettyClient();
    return nettyClient.send(url.getHostname(), url.getPort(), invocation);
  }
}
