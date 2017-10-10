import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest {
  public static void main(String[] args) {
    //实例初始化
    ByteBuf buffer = Unpooled.buffer(100);
    String value = "学习ByteBuf";
    buffer.writeBytes(value.getBytes());
    System.out.println("获取readerIndex:" + buffer.readerIndex());
    System.out.println("获取writerIndex:" + buffer.writerIndex());
    byte[] vArray = new byte[buffer.writerIndex()];
    buffer.readBytes(vArray);
    System.out.println("获取readerIndex:" + buffer.readerIndex());
    System.out.println("获取writerIndex:" + buffer.writerIndex());
    System.out.println(new String(vArray));
    buffer.release();
  }
} 