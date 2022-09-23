package com.spero.provider.impl;

import com.spero.provider.service.HelloService;

/**
 * 远程调用接口实现
 * @author wujh
 * @date 2022/9/22
 */
public class HelloServiceImpl implements HelloService {
  @Override
  public String sayHello(String userName) {
    return "Hello! " + userName;
  }
}
