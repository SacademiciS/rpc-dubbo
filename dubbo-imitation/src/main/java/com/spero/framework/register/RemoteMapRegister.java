package com.spero.framework.register;

import com.spero.framework.URL;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册中心实现
 *
 * @author wujh
 * @date 2022/9/22
 */
public class RemoteMapRegister {
  // 映射接口与实例提供远程调用URL
  private static Map<String, List<URL>> REGISTER = new HashMap<>();

  public static void regist(String interfaceName, URL url) {

    List<URL> list = REGISTER.get(interfaceName);
    if (list == null) {
      list = new ArrayList<>();
    }
    list.add(url);

    REGISTER.put(interfaceName, list);

    saveFile();
  }

  public static List<URL> get(String interfaceName) {
    REGISTER = getFile();

    List<URL> list = REGISTER.get(interfaceName);
    return list;
  }

  private static void saveFile() {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("E:\\worklist\\TL\\temp.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(REGISTER);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static Map<String, List<URL>> getFile() {
    try {
      FileInputStream fileInputStream = new FileInputStream("E:\\worklist\\TL\\temp.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      return (Map<String, List<URL>>) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
