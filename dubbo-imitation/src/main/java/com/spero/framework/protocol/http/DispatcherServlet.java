package com.spero.framework.protocol.http;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http统一接收请求servlet
 *
 * @author wujh
 * @date 2022/9/23
 */
public class DispatcherServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    new HttpServerHandler().handler(req, resp);
  }
}
