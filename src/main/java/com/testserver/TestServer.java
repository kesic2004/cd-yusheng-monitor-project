package com.testserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;

public class TestServer {
   private final int port;
   private static final Logger logger = Logger.getLogger(TestServer.class);

   public TestServer(int port) {
      this.port = port;
   }

   public void start() throws Exception {
      EventLoopGroup bossGroup = new NioEventLoopGroup();
      NioEventLoopGroup group = new NioEventLoopGroup();

      try {
         ServerBootstrap sb = new ServerBootstrap();
         sb.option(ChannelOption.SO_BACKLOG, 1024);
         ((ServerBootstrap)((ServerBootstrap)sb.group(group, bossGroup).channel(NioServerSocketChannel.class)).localAddress(this.port)).childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
               TestServer.logger.info("-------------有客户端连接--------------");
               TestServer.logger.info("IP:" + ch.localAddress().getHostName());
               TestServer.logger.info("Port:" + ch.localAddress().getPort());
               ch.pipeline().addLast(new ChannelHandler[]{new TestDecoder()});
               ch.pipeline().addLast(new ChannelHandler[]{new TestServerHandler()});
            }
         });
         ChannelFuture cf = sb.bind().sync();
         logger.info("socket服务端启动成功，监听端口： " + cf.channel().localAddress());
         System.out.println("socket服务端启动成功，监听端口： " + cf.channel().localAddress());
         cf.channel().closeFuture().sync();
      } finally {
         group.shutdownGracefully().sync();
         bossGroup.shutdownGracefully().sync();
      }

   }
}
