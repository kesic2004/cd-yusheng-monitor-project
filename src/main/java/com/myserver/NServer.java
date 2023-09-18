package com.myserver;

import com.model.currentuser.service.CurrentuserService;
import com.model.device.service.DeviceService;
import com.model.fj.service.FjBottleService;
import com.model.fj.service.FjDwService;
import com.model.license.service.LicenseService;
import com.model.special.service.SpecialService;
import com.model.tblclient.service.TblclientService;
import com.model.tbldata.service.TbldataService;
import com.model.tbldevicelog.service.TbldevicelogService;
import com.model.tbltoken.service.TbltokenService;
import com.model.user.service.UserService;
import com.model.xk.service.XkCylinderrecService;
import com.model.xk.service.XkStationmapService;
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
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

public class NServer implements Runnable {
   private static final Logger logger = Logger.getLogger(NServer.class);
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
   CurrentuserService currentuserService;
   @Autowired
   private LicenseService licenseService;
   @Autowired
   private XkCylinderrecService xkCylinderrecService;
   @Autowired
   private SpecialService specialService;
   @Autowired
   private XkStationmapService xkStationmapService;
   @Autowired
   private FjBottleService fjBottleService;
   @Autowired
   private FjDwService fjDwService;
   @Autowired
   private TbltokenService tbltokenService;
   AtomicInteger connectNum = new AtomicInteger();

   @PostConstruct
   public void Ini() throws Exception {
      (new Thread(this)).start();
   }

   public void run() {
      EventLoopGroup bossGroup = new NioEventLoopGroup();
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      logger.info("### 准备运行端口：" + this.port);

      try {
         ServerBootstrap b = new ServerBootstrap();
         ((ServerBootstrap)((ServerBootstrap)b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)).childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
               NServer.logger.info("-------------有客户端连接--------------");
               NServer.logger.info("IP:" + ch.localAddress().getHostName());
               NServer.logger.info("Port:" + ch.localAddress().getPort());
               ch.pipeline().addLast(new ChannelHandler[]{new IdleStateHandler(30L, 30L, 30L, TimeUnit.SECONDS)});
               ch.pipeline().addLast(new ChannelHandler[]{new DataDecoder()});
               ch.pipeline().addLast(new ChannelHandler[]{new DataHandler(NServer.this.connectNum, NServer.this.tbldataService, NServer.this.tbldevicelogService, NServer.this.userService, NServer.this.deviceService, NServer.this.tblclientService, NServer.this.currentuserService, NServer.this.licenseService, NServer.this.xkCylinderrecService, NServer.this.specialService, NServer.this.xkStationmapService, NServer.this.applicationContext, NServer.this.fjBottleService, NServer.this.fjDwService, NServer.this.tbltokenService)});
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
