package lesson35;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {

    public static final int CARS_COUNT = 10;
    public static volatile int firstFinish = -1;
    private static final Logger logger = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {

        CountDownLatch countDownLatchReady = new CountDownLatch(CARS_COUNT);        //ждем пока все подготовятся
        CountDownLatch countDownLatchFinish = new CountDownLatch(CARS_COUNT);       //ждем пока все доедут до финиша

        //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        writeLogger(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), countDownLatchReady, countDownLatchFinish);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }


        try {
            countDownLatchReady.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        //writeLogger(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            countDownLatchFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        //writeLogger(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        writeLogger(Level.INFO, "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static void writeLogger(Level level, String msg){
        logger.log(level, msg);
    }


}





