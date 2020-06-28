package server

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame
import io.netty.buffer.Unpooled


class OnMessagehandler: SimpleChannelInboundHandler<Object>() {
  
  override fun channelActive(ctx: ChannelHandlerContext ){
      print("channelActive    ");
  }

  override fun channelRead0(ctx: ChannelHandlerContext , msg: Object ){
      print("channelRead0    ");
      if(msg is TextWebSocketFrame){
          print("收到消息："+(msg as TextWebSocketFrame).text());
      }else if(msg is BinaryWebSocketFrame){
          print("收到二进制消息："+(msg as BinaryWebSocketFrame).content().readableBytes());
          val binaryWebSocketFrame: BinaryWebSocketFrame = BinaryWebSocketFrame(Unpooled.buffer().writeBytes("xxx".toByteArray()));
          ctx.channel().writeAndFlush(binaryWebSocketFrame);
      }
  }

  override fun channelUnregistered(ctx: ChannelHandlerContext){
      print("channelUnregistered");
  }

  override fun channelInactive(ctx: ChannelHandlerContext){
      print("channelInactive");
  }
}
