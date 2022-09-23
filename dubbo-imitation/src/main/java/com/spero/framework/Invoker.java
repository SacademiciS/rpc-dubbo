package com.spero.framework;

/**
 * 消息者调用远程接口具体实现
 *
 * @author wujh
 * @date 2022/9/22
 */
public interface Invoker {

  String invoke(Invocation invocation);

}
