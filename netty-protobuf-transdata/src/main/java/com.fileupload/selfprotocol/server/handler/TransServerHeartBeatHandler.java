package com.fileupload.selfprotocol.server.handler;

import com.fileupload.selfprotocol.pojo.TransMessageData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TransServerHeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TransMessageData.MessageData messageData = (TransMessageData.MessageData) msg;
        if (messageData.getType() == TransMessageData.MessageData.DataType.PING) {
            TransMessageData.MessageData req = TransMessageData.MessageData.newBuilder()
                    .setType(TransMessageData.MessageData.DataType.PONG).build();
//            System.out.println("Send-Client:PONG,time:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ctx.writeAndFlush(req);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}

