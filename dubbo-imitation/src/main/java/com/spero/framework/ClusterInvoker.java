package com.spero.framework;

import com.spero.framework.register.RemoteMapRegister;
import java.util.ArrayList;
import java.util.List;

/**
 * 集群调用者
 *
 * @author wujh
 * @date 2022/9/23
 */
public class ClusterInvoker implements Invoker {

  private List<Invoker> invokers = new ArrayList<>();

  public List<Invoker> getInvokers() {
    return invokers;
  }

  public void addInvokers(Invoker invoker) {
    this.invokers.add(invoker);
  }

  public static Invoker join(Class interfaceClass) {
    ClusterInvoker clusterInvoker = new ClusterInvoker();

    // 从注册中心查看urls
    List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());

    // 从url封装具体调用者实现
    urlList.forEach(url -> {
      Protocol protocol = ProtocolFactory.getProtocol(url.getProtocol());
      Invoker invoker = protocol.refer(url);
      clusterInvoker.addInvokers(invoker);
    });

    return clusterInvoker;
  }

  @Override
  public String invoke(Invocation invocation) {
    // 随机负载选择一个调用者
    Invoker invoker = LoadBalance.random(invokers);
    return invoker.invoke(invocation);
  }
}
