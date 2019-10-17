package lesson6.animals;

import java.util.Random;

public class Animal {
    private double maxRun = 0;
    private double maxSwim = 0;
    private double maxHeightObstacle = 0;
    private Random random = new Random(); //разброс в 10% в обе строны

    public Animal(double maxRun, double maxSwim, double maxHeightObstacle) {
        this.maxRun = settingArgument(maxRun);
        this.maxSwim = settingArgument(maxSwim);
        this.maxHeightObstacle = settingArgument(maxHeightObstacle);
    }

    private double settingArgument(double value) {
        if (value > 0) {
            return value + value / 100 * (random.nextInt(21) - 10);
        }
        return value;
    }

    @Override
    public String toString() {
        return "животное";
    }

    public void run(double value) {
        if (maxRun == 0) {
            System.out.println(this.toString() + " не умеет бегать");
        } else if (maxRun < value) {
            System.out.println(this.toString() + " - непосильная дистанция " + value + ", лимит в метрах - " + maxRun);
        } else {
            System.out.println(this.toString() + " - дистанция " + value + "м. преодолена");
        }
    }

    public void swim(double value) {
        if (maxSwim == 0) {
            System.out.println(this.toString() + " не умеет плавать");
        } else if (maxSwim < value) {
            System.out.println(this.toString() + " - столько не проплыть " + value + ", лимит в метрах - " + maxSwim);
        } else {
            System.out.println(this.toString() + " - дистанция " + value + "м. проплыта");
        }
    }

    public void jumpOverObstacle(double value) {
        if (maxHeightObstacle == 0) {
            System.out.println(this.toString() + " не умеет прыгать");
        } else if (maxHeightObstacle < value) {
            System.out.println(this.toString() + " - непосильная высота препятствия " + value + ", лимит в метрах - " + maxHeightObstacle);
        } else {
            System.out.println(this.toString() + " - препятствие " + value + "м. взято");
        }
    }


}
