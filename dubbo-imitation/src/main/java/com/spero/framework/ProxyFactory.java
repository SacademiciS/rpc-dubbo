package com.spero.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 消费者远程接口代理工厂
 *
 * @author wujh
 * @date 2022/9/23
 */
public class ProxyFactory<T> {

  public static <T> T getProxy(final Class interfaceClass) {
    return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[] {interfaceClass},
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String mock = System.getProperty("mock");
            if (mock != null && mock.startsWith("return:")) {
              String result = mock.replace("return:", "");
              return result;
            }

            Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args,
                method.getParameterTypes());
            Invoker invoker = ClusterInvoker.join(interfaceClass);
            return invoker.invoke(invocation);
          }
        });
  }
}
