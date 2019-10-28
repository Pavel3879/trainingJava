package lesson35;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private CountDownLatch cdlReady;
    private CountDownLatch cdlFinish;
    private static CountDownLatch countDownLatch = new CountDownLatch(MainClass.CARS_COUNT);        //ждем, пока все будут готовы


    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch cdlReady, CountDownLatch cdlFinish) {
        this.race = race;
        this.speed = speed;
        this.cdlReady = cdlReady;
        this.cdlFinish = cdlFinish;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            this.cdlReady.countDown();
            this.countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //не начнем, пока все не будут готовы
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (MainClass.firstFinish < 0) {
            MainClass.firstFinish = CARS_COUNT;
            System.out.println(this.name + " WIN!!!");
            //MainClass.writeLogger(Level.INFO, "WIN!!!");
        }

        this.cdlFinish.countDown();

    }
}
