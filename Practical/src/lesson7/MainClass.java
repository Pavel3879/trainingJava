package lesson7;

import java.util.Random;

public class MainClass {
    public static void main(String[] args) {

        Random random = new Random();
        Plate plate = new Plate(100);
        Cat[] cat = new Cat[10];
        for (int i = 0; i < cat.length; i++) {
            cat[i] = new Cat("Кот №" + (i + 1), random.nextInt(16) + 10);
        }

        for (int i = 0; i < cat.length; i++) {
            cat[i].eat(plate);
            //cat[i].info();
            plate.info();
        }

        for (int i = 0; i < cat.length; i++) {
            //System.out.println(cat[i].toString());
            cat[i].info();
        }

        plate.info();
        plate.addFood(25);
        plate.info();

    }
}