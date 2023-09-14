package com.myserver;

import com.model.device.service.DeviceService;
import com.model.license.service.LicenseService;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.service.TbldataService;
import com.model.tbldevicelog.service.TbldevicelogService;
import com.model.user.service.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class NServerEx {
   private static final Logger logger = Logger.getLogger(NServerEx.class);
   @Value("${netty.port}")
   private int port;
   @Autowired
   private TbldataService tbldataService;
   @Autowired
   private TbldevicelogService tbldevicelogService;
   @Autowired
   private UserService userService;
   @Autowired
   private DeviceService deviceService;
   @Autowired
   private TblclientService tblclientService;
   @Autowired
   private ApplicationContext applicationContext;
   @Autowired
   private LicenseService licenseService;
   AtomicInteger connectNum = new AtomicInteger();

   public void startRun() {
      EventLoopGroup bossGroup = new NioEventLoopGroup();
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      logger.info("@@@ 准备运行端口：" + this.port);

      try {
         ServerBootstrap b = new ServerBootstrap();
         ((ServerBootstrap)((ServerBootstrap)b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)).childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
               NServerEx.logger.info("-------------有客户端连接--------------");
               BizTool.PrintTime(">>>[initChannel]当前时间：");
               NServerEx.logger.info(">>>[initChannel] RemoteIp:RemotePort => " + ch.remoteAddress().getAddress().getHostAddress() + ":" + ch.remoteAddress().getPort());
               ch.pipeline().addLast(new ChannelHandler[]{new IdleStateHandler(45L, 45L, 45L, TimeUnit.SECONDS)});
               ch.pipeline().addLast(new ChannelHandler[]{new DataDecoder()});
               ch.pipeline().addLast(new ChannelHandler[]{new DataHandler(NServerEx.this.connectNum, NServerEx.this.tbldataService, NServerEx.this.tbldevicelogService, NServerEx.this.userService, NServerEx.this.deviceService, NServerEx.this.tblclientService, NServerEx.this.licenseService, NServerEx.this.applicationContext)});
            }
         }).option(ChannelOption.SO_BACKLOG, 128)).childOption(ChannelOption.SO_KEEPALIVE, true);
         ChannelFuture f = b.bind(this.port).sync();
         f.channel().closeFuture().sync();
      } catch (Exception var5) {
         var5.printStackTrace();
         workerGroup.shutdownGracefully();
         bossGroup.shutdownGracefully();
      }

   }
}
