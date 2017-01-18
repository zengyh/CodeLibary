package utils.http.httpclient;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


class Task implements Runnable
{
    private int x;            //线程编号
    Lock lock;
    CountDownLatch latch;

    public Task(int x ,Lock lock, CountDownLatch latch) {
        this.x = x;
        this.lock = lock;
        this.latch = latch;
    }

    public void run() {
        latch.countDown();
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
            }
        }

        System.out.println(x + " thread doing something!");
        System.out.println("第" + x + "个线程执行完毕");
    }
}

class Lock{
    public synchronized  void notifyOne(){
        this.notify();
    }

    public synchronized  void notifyEveryOne(){
        this.notifyAll();
    }
}


public class ThreadPoolTest
{

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Lock();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5, 5,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10, false),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            threadPool.execute(new Task(i, lock, latch));
        }
        latch.await();

        //lock.notifyEveryOne();
        lock.notifyOne();
        //threadPool.shutdown();

        /*for (int i = 6; i <= 10; i++) {
            threadPool.execute(new Task(i));
        }*/
    }


}