package lesson25;


import com.sun.deploy.util.ArrayUtil;

import java.sql.Array;
import java.util.Arrays;

public class MainClass{

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {

        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        MainClass mainClass = new MainClass();
        mainClass.metod1(arr.clone());
        mainClass.metod2(arr.clone());

    }

    private void metod1(float[] arr){

        System.out.println("метод 1 - начало");
        long a = System.currentTimeMillis();
        MyThread myThread = new MyThread(arr, 0);
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("метод 1 отработал за : " + (System.currentTimeMillis() - a));
    }

    private void metod2(float[] arr){
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.out.println("метод 2 - начало");
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println("поделили массив: " + (System.currentTimeMillis() - a));

        MyThread myThread1 = new MyThread(a1, 0);
        myThread1.start();
        MyThread myThread2 = new MyThread(a2, h);
        myThread2.start();

        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("посчитали: " + (System.currentTimeMillis() - a));
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("склеили массив: " + (System.currentTimeMillis() - a));
        System.out.println("метод 2 отработал за : " + (System.currentTimeMillis() - a));


    }

    class MyThread extends Thread {
        float[] arr;
        int offset;

        public MyThread(float[] arr, int offset) {
            this.arr = arr;
            this.offset = offset;
        }

        @Override
        public void run()    //Этот метод будет выполнен в побочном потоке
        {
            long a = System.currentTimeMillis();
            for (int i = 0, n = offset; i < this.arr.length; i++, n++) {
                arr[i] = (float) (this.arr[i] * Math.sin(0.2f + n / 5) * Math.cos(0.2f + n / 5) * Math.cos(0.4f + n / 2));
            }
            System.out.println("поток отработал за : " + (System.currentTimeMillis() - a));
        }
    }

}
