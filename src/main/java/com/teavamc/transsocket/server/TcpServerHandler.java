package com.teavamc.transsocket.server;

import com.teavamc.transsocket.transmessage.TransServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName TcpServerHandler
 * @date 2019/5/2 15:05
 **/
public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {

    private static Logger log = LogManager.getLogger(TcpServerHandler.class);

    private final String OPENLED1 = "01";
    private final String CLOSLED1 = "02";
    private final String OPENBEEP = "03";
    private final String CLOSEBEEP = "04";
    private final String SUCCEED = "1";
    private final String FAILED = "0";

    /**
     * @return void
     * @Description 打印接收到的内容，并回传
     * @author 张超 teavamc
     * @date 2019/5/4
     * @Time 16:25
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (OPENLED1.equals(msg)) {
            log.info("TCP Server收到开启LED1的指令：" + msg);
            ctx.channel().writeAndFlush(SUCCEED);
            TransServerHandler.sendMessage("S"+msg+"E\n");
        } else if (CLOSLED1.equals(msg)) {
            log.info("TCP Server收到关闭LED1的指令：" + msg);
            ctx.channel().writeAndFlush(SUCCEED);
            TransServerHandler.sendMessage("S"+msg+"E\n");
        } else if (OPENBEEP.equals(msg)) {
            log.info("TCP Server收到开启Beep的指令：" + msg);
            ctx.channel().writeAndFlush(SUCCEED);
            TransServerHandler.sendMessage("S"+msg+"E\n");
        } else if (CLOSEBEEP.equals(msg)) {
            log.info("TCP Server收到关闭Beep的指令：" + msg);
            ctx.channel().writeAndFlush(SUCCEED);
            TransServerHandler.sendMessage("S"+msg+"E\n");
        } else {
            log.info("不明指令：" + msg);
            ctx.channel().writeAndFlush(FAILED);
        }
    }

    /**
     * @return void
     * @Description
     * @author 张超 teavamc
     * @date 2019/5/4
     * @Time 16:50
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("exceptionCaught!cause:" + cause.toString());
        ctx.close();
    }

}
