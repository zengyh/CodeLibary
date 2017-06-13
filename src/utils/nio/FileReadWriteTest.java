package utils.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件名称: FileReadWriteTest.java
 * 编写人: yh.zeng
 * 编写时间: 17-6-8 下午9:18
 * 文件描述: IO和NIO文件读写性能对比
 */
public class FileReadWriteTest
{

    public static void main(String args[]) throws  Exception{
        FileReadWriteTest test = new FileReadWriteTest();
        long start = System.currentTimeMillis();
        //for(int i = 0; i <= 10; i++){
            test.ioCopyFile("F://file.txt","F://filecopy.txt");
        //}
        System.out.println("IO使用缓冲区耗时：" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        //for(int i = 0; i <= 10; i++){
            test.nioCopyFile("F://file.txt", "F://filecopy.txt");
        //}
        System.out.println("NIO使用缓冲区读写耗时：" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        //for(int i = 0; i <= 10; i++){
            test.nioCopyFile2("F://file.txt", "F://filecopy.txt");
        //}
        System.out.println("NIO映射到内存读写文件耗时：" + (System.currentTimeMillis() - start) + "ms");

    }


    /**
     * NIO读写通道（双通道）进行文件的读写操作，数据使用一个缓冲区
     * @param source      源文件
     * @param target      目标文件
     * @throws Exception
     */
    public void nioCopyFile(String source, String target) throws  Exception{
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        FileChannel readChannel = fis.getChannel();     //读文件通道
        FileChannel writeChannel = fos.getChannel();    //写文件通道
        ByteBuffer buffer = ByteBuffer.allocate(1024*8); //缓冲区
        buffer.clear();
        while (readChannel.read(buffer) != -1){
            buffer.flip();
            writeChannel.write(buffer);
            buffer.clear();
        }
        readChannel.close();
        writeChannel.close();
        fis.close();
        fos.close();
    }

    /**
     * NIO 将文件映射到内存进行读和写
     * @param source      源文件
     * @param target      目标文件
     * @throws Exception
     */
    public void nioCopyFile2(String source, String target) throws  Exception{
        FileInputStream fis = new FileInputStream(source);
        FileChannel readChannel = fis.getChannel();     //读文件通道
        FileChannel writeChannel = new RandomAccessFile(target,"rw").getChannel();    //写文件通道
        int fileLen = fis.available();
        CharBuffer charReadBuffer = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLen).asCharBuffer();
        CharBuffer charWriteBuffer = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileLen).asCharBuffer();
        charWriteBuffer.put(charReadBuffer);
        readChannel.close();
        fis.close();
        writeChannel.close();

    }

    /**
     * IO读写文件，使用了缓冲区
     * @param source      源文件
     * @param target      目标文件
     * @throws Exception
     */
    public void ioCopyFile(String source, String target) throws  Exception{
        FileInputStream fis = new FileInputStream(source);
        BufferedInputStream bis = new BufferedInputStream(fis);
        FileOutputStream fos = new FileOutputStream(target);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] buffer = new byte[1024*8]; //缓冲区
        int lenth = 0;
        while ((lenth = bis.read(buffer)) != -1){
           bos.write(buffer, 0 , lenth);
           bos.flush();
        }
        fis.close();
        bis.close();
        fos.close();
        bos.close();
    }

}
