package com.spero.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册实现
 *
 * @author wujh
 * @date 2022/9/22
 */
public class LocalRegister {
  // 映射接口与具体实现
  private static Map<String, Class> map = new HashMap<>();

  public static void register(String interfaceName, Class implClass) {
    map.put(interfaceName, implClass);
  }

  public static Class get(String interfaceName) {
    return map.get(interfaceName);
  }
}
