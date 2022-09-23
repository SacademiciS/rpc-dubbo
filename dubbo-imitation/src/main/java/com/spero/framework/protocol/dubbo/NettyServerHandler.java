package com.spero.framework.protocol.dubbo;

import com.spero.framework.Invocation;
import com.spero.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.lang.reflect.Method;

/**
 * Netty服务端接收消息处理
 *
 * @author wujh
 * @date 2022/9/22
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    Invocation invocation = (Invocation) msg;
    Class serviceImpl = LocalRegister.get(invocation.getInterfaceName());

    Method method = serviceImpl.getMethod(invocation.getMethodName(), invocation.getParamType());
    Object result = method.invoke(serviceImpl.newInstance(), invocation.getParams());

    System.out.println("Netty-------------" + result.toString());
    ctx.writeAndFlush("Netty:" + result);
  }
}
