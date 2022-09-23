package com.spero.framework.protocol.http;

import com.spero.framework.Invoker;
import com.spero.framework.URL;
import com.spero.framework.Invocation;

/**
 * Http协议调用者实现
 *
 * @author wujh
 * @date 2022/9/23
 */
public class HttpInvoker implements Invoker {

  private URL url;

  public HttpInvoker(URL url) {
    this.url = url;
  }

  @Override
  public String invoke(Invocation invocation) {
    HttpClient httpClient = new HttpClient();
    return httpClient.send(url.getHostname(), url.getPort(), invocation);
  }
}
