package com.spero.comsumer;

import com.spero.framework.ProxyFactory;
import com.spero.provider.service.HelloService;

/**
 * 服务消费者
 *
 * @author wujh
 * @date 2022/9/22
 */
public class Comsumer {
  public static void main(String[] args) {
    HelloService helloService = ProxyFactory.getProxy(HelloService.class);
    String result = helloService.sayHello("gghz");
    System.out.println(result);
  }
}
