package com.spero.framework;

/**
 * 消息传输协议
 *
 * @author wujh
 * @date 2022/9/22
 */
public interface Protocol {
  // 导出
  void export(URL url);

  // 引入
  Invoker refer(URL url);
}
