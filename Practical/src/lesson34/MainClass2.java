package lesson34;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass2 {

    private final int COUNT = 5;
    private final int CNT_THREAD = 3;
    private ExecutorService executorService;

    public static void main(String[] args) {
        MainClass2 mainClass = new MainClass2();
        mainClass.ExecutorServiceEx();
    }

    public void ExecutorServiceEx() {
        CountDownLatch[] cdl = new CountDownLatch[CNT_THREAD];
        executorService = Executors.newFixedThreadPool(CNT_THREAD);
        System.out.println("start");
        for (int i = 0; i < CNT_THREAD; i++) {
            cdl[i] = new CountDownLatch(COUNT);
            executorService.execute(new MyThread(cdl[i], "Thread." + i));
        }

        for (int i = 0; i < CNT_THREAD; i++) {
            try {
                cdl[i].await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        System.out.println("end");
    }


    class MyThread implements Runnable {
        String name;
        CountDownLatch latch;

        public MyThread(CountDownLatch latch, String name) {
            this.name = name;
            this.latch = latch;
            new Thread(this);
        }

        public void run() {
            for (int i = 0; i < COUNT; i++) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printMsg(name + " - " + i);
                latch.countDown();
            }
            printMsg(name + " completed");
        }
    }

    private synchronized void printMsg(String msg) {
        System.out.println(msg);
    }

}
