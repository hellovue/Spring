package org.github.spring.footstone;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> { //1
  @Override
  public void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
    out.add(msg.toString());  //2
  }
}
