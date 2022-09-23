package com.spero.framework.protocol.dubbo;

import com.spero.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.Callable;

/**
 * Netty客户端消息发送处理
 *
 * @author wujh
 * @date 2022/9/23
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

  private ChannelHandlerContext context;
  private Invocation invocation;
  private String result;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    context = ctx;
  }

  @Override
  public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    result = msg.toString();
    notify();
  }

  @Override
  public synchronized Object call() throws Exception {
    context.writeAndFlush(this.invocation);
    wait();
    return result;
  }

  public void setInvocation(Invocation invocation) {
    this.invocation = invocation;
  }
}
