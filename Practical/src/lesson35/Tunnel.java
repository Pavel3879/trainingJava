package lesson35;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    //true - разрешения будут предоставляться в том порядке, в каком они запрашивали доступ
    private static Semaphore semaphore = new Semaphore(MainClass.CARS_COUNT / 2, true);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);

                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}