package com.spero.framework;

import java.util.List;
import java.util.Random;

/**
 * 负载策略
 *
 * @author wujh
 * @date 2022/9/23
 */
public class LoadBalance {
  public static Invoker random(List<Invoker> list) {
    Random random = new Random();
    int n = random.nextInt(list.size());
    return list.get(n);
  }
}
