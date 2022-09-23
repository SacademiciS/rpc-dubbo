package com.spero.framework.protocol.http;

import com.spero.framework.Invocation;
import com.spero.framework.register.LocalRegister;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 * http服务端接收请求处理
 *
 * @author wujh
 * @date 2022/9/23
 */
public class HttpServerHandler {
  public void handler(HttpServletRequest req, HttpServletResponse resp) {
    try {
      Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
      String interfaceName = invocation.getInterfaceName();
      Class impClass = LocalRegister.get(interfaceName);
      Method method = impClass.getMethod(invocation.getMethodName(), invocation.getParamType());
      String result = (String) method.invoke(impClass.newInstance(), invocation.getParams());

      System.out.println("tomcat:" + result);
      IOUtils.write(result, resp.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
