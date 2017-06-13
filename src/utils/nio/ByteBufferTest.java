package utils.nio;

import utils.StringUtils;

import java.nio.ByteBuffer;

/**
 * 文件名称: ByteBufferTest.java
 * 编写人: yh.zeng
 * 编写时间: 17-6-11 下午4:27
 * 文件描述: ByteBuffer的常用方法测试
 */
public class ByteBufferTest
{

    public static void main(String args[]) {
        allocateTest();
        flipFunTest();
        clearFunTest();
        rewindFunTest();
        markFunTest();
        sliceFunTest();
        asReadOnlyBufferFunTest();
    }

    /**
     * 测试ByteBuffer.allocate(int capacity)方法
     * 作用：分配指定capacity大小的缓冲区，默认存的数据为0
     * 例如：分配指定15字节的缓冲区，那么缓冲区中默认存的是15个0
     */
    public static void allocateTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        System.out.println("【allocateTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        System.out.println("【allocateTest】刚分配(allocate)的buffer缓冲区中的数据：");
        for(int i = 0; i < buffer.limit(); i++){
              System.out.println(buffer.get());
        }
    }

    /**
     * 测试ByteBuffer.flip()方法
     * 作用：重置位置为0，上限值变成重置前的位置
     * 例如：有个容量为15字节的buffer，重置前的位置为10，那么重置后，位置 0，上限 10 ，容量 15
     */
    public static void flipFunTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        System.out.println("【flipFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        for(int i = 0; i < 10; i++){
            buffer.put((byte)i);
        }
        System.out.println("【flipFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        buffer.flip();
        System.out.println("【flipFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        for(int i = 0; i < 4; i++){
            buffer.get();
        }
        System.out.println("【flipFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        buffer.flip();
        System.out.println("【flipFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        System.out.println("【flipFunTest】buffer缓冲区中的数据：");
        for(int i = 0; i < buffer.limit(); i++){
              System.out.println(buffer.get());
        }
    }


    /**
     * 测试ByteBuffer.clear()方法
     * 作用：位置重置为0，但是和flip()方法不同，上限值为重置前的缓冲区的容量大小
     */
    public static void clearFunTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        System.out.println("【clearFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        for(int i = 0; i < 10; i++){
            buffer.put((byte)i);
        }
        System.out.println("【clearFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  "位置：" + buffer.position());
        buffer.clear();
        System.out.println("【clearFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  "位置：" + buffer.position());
        for(int i = 0; i < 4; i++){
            buffer.get();
        }
        System.out.println("【clearFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        buffer.clear();
        System.out.println("【clearFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        System.out.println("【clearFunTest】buffer缓冲区中的数据：");
        for(int i = 0; i < buffer.limit(); i++){
              System.out.println(buffer.get());
        }
    }


    /**
     * 测试ByteBuffer.rewind()方法
     * 作用：位置重置为0，上限值不会改变，上限值还是重置前的值
     */
    public static void rewindFunTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() + " 位置：" + buffer.position());
        for(int i = 0; i < 10; i++){
            buffer.put((byte)i);
        }
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        buffer.rewind();
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        for(int i = 0; i < 4; i++){
            buffer.get();
        }
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        buffer.rewind();
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        for(int i = 0; i < 4; i++){
            buffer.get();
        }
        buffer.flip();
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        buffer.rewind();
        System.out.println("【rewindFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        System.out.println("【rewindFunTest】buffer缓冲区中的数据：");
        for(int i = 0; i < buffer.limit(); i++){
              System.out.println(buffer.get());
        }
    }

    /**
     * 测试ByteBuffer.mark()方法
     * 作用：可以对当前位置进行标记，以后使用ByteBuffer.reset()方法
     *      可以使缓冲区的位置重置为以前标记的位置,从而可以返回到标记的位置
     *      对缓冲区的数据进行操作
     */
    public static void markFunTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        for(int i=0; i < 10; i++){
            buffer.put((byte)i);
        }
        buffer.clear();
        System.out.println("【markFunTest】buffer缓冲区中的数据：");
        for(int i = 0; i < buffer.limit(); i++){
              System.out.println(buffer.get());
        }
        buffer.clear();
        //使用mark方法进行标记，在位置为4处进行标记
        buffer.position(4);
        buffer.mark();
        System.out.println("标志的位置：" + buffer.position());
        System.out.println("【markFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        buffer.reset(); //将此缓冲区的位置重置为以前标记的位置
        System.out.println("【markFunTest】上限：" + buffer.limit() + " 容量：" + buffer.capacity() +  " 位置：" + buffer.position());
        //判断在当前位置和上限(最大的位置)之间是否有元素
        boolean isFirst = true;
        while(buffer.hasRemaining()){
            if(isFirst){
                System.out.println("【markFunTest】当前位置和上限(最大的位置)之间的数据：");
                isFirst = false;
            }
            System.out.println(buffer.get());
        }
        //修改标志的位置的元素值
        buffer.reset();
        buffer.put((byte)100);
        buffer.clear();
        System.out.println("【markFunTest】buffer缓冲区中的数据：");
        for(int i = 0; i < buffer.limit(); i++){
              System.out.println(buffer.get());
        }
    }


    /**
     * 测试ByteBuffer.slice()方法
     * 作用：划分子缓冲区（也可以称之为分片），子缓冲区的数据和父缓冲区的数据共享
     */
    public static void sliceFunTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        for(int i=0; i < 10; i++){
            buffer.put((byte)i);
        }

        //位置3、4、5划分成一个子缓冲区
        buffer.position(3);
        buffer.limit(6);
        ByteBuffer subBuffer = buffer.slice();

        System.out.println("【sliceFunTest】划分出来的子缓冲区的上限：" + subBuffer.limit() + " 容量：" + subBuffer.capacity() +  " 位置：" + subBuffer.position());
        for(int i = 0; i < subBuffer.limit(); i++){
            byte data = subBuffer.get();
            subBuffer.put(i, (byte)(data * 10) );
        }

        System.out.println("【sliceFunTest】buffer缓冲区中的数据：");
        buffer.clear();
        for(int i = 0; i < buffer.capacity(); i++){
              System.out.println(buffer.get());
        }
    }

    /**
     * 测试ByteBuffer.asReadOnlyBuffer方法
     * 作用：相当于复制一个缓冲区，但是这个缓冲区却不可以进行修改操作，是一个只读缓冲区。
     *      可以通过修改原始的缓冲区的数据来改变这个只读缓冲区的数据
     */
    public static void asReadOnlyBufferFunTest(){
        ByteBuffer buffer = ByteBuffer.allocate(15); //15字节缓冲区，注意：分配的缓冲区，默认存的是15个0
        for(int i=0; i < 10; i++){
            buffer.put((byte)i);
        }
        //创建只读缓冲区
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        readOnlyBuffer.flip();
        System.out.println("【asReadOnlyBufferFunTest】只读缓冲区，上限：" + readOnlyBuffer.limit() + " 容量：" + readOnlyBuffer.capacity() +  " 位置：" + readOnlyBuffer.position());
        boolean isFirst = true;
        while(readOnlyBuffer.hasRemaining()){
            if(isFirst){
                System.out.println("【asReadOnlyBufferFunTest】只读缓冲区的数据：");
                isFirst = false;
            }
            System.out.println(readOnlyBuffer.get());
        }


        readOnlyBuffer.position(1);
        try {
            readOnlyBuffer.put((byte)10);
        } catch (Exception e) {
            System.out.println("【asReadOnlyBufferFunTest】只读缓冲区的数据不可以修改，进行修改操作会抛出以下异常：");
            System.out.println(StringUtils.getExceptionMessage(e));
        }


        //可以通过修改原始的缓冲区的数据来改变这个只读缓冲区的数据
        buffer.position(1);
        buffer.put((byte)10);
        isFirst = true;
        readOnlyBuffer.position(0);
        while(readOnlyBuffer.hasRemaining()){
            if(isFirst){
                System.out.println("【asReadOnlyBufferFunTest】只读缓冲区的数据：");
                isFirst = false;
            }
            System.out.println(readOnlyBuffer.get());
        }

    }

}
