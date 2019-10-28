package lesson34;

import java.util.ArrayList;

public class MainClass {

    private final int COUNT = 5;
    private final int CNT_THREAD = 3;
    private final Object mon = new Object();
    private int aTurn = 0;

    public static void main(String[] args) {
        (new MainClass()).ExecutorServiceEx();
    }

    public void ExecutorServiceEx() {
        ArrayList<MyThread> threads = new ArrayList<>();

        for (int i = 0; i < CNT_THREAD; i++) {
            threads.add(new MyThread(String.valueOf((char)(i+65)), i));
        }

    }

    class MyThread implements Runnable {
        String msg;
        int num;

        public MyThread(String msg, int num) {
            this.msg = msg;
            this.num = num;
            new Thread(this).start();
        }

        public void run() {
            for (int i = 0; i < COUNT; i++) {
                try {
                    Thread.sleep(num * 100);    //дабы убедиться, что порядок будет соблюден при любом раскладе
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printMsg(msg, num);
            }
        }
    }

    private void printMsg(String msg, int turn) {
        synchronized (mon) {
            //System.out.println(msg + " turn");
            if (aTurn != turn) {
                try {
                    //System.out.println(msg + " wait");
                    mon.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (aTurn != turn) {
                printMsg(msg, turn);
            } else {
                System.out.println(msg);
                aTurn = (turn + 1) % CNT_THREAD;
                //System.out.println(msg + " notify " + aTurn);

                //работают оба варианта, но при такой реализации для данной задачи, думаю, notifyAll актуальнее
                //mon.notify();
                mon.notifyAll();

            }
        }
    }

}
