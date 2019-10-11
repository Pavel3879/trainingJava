package lesson6;

import lesson6.animals.Animal;
import lesson6.animals.Cat;
import lesson6.animals.Dog;

public class MainClass {
    public static void main(String[] args) {

        Animal cat = new Cat();
        Animal dog = new Dog();

        // бег: кот 200 м., собака 500 м.;
        cat.run(100);
        cat.run(200);
        cat.run(300);

        dog.run(400);
        dog.run(500);
        dog.run(600);

        // плавание: кот не умеет плавать, собака 10 м.
        cat.swim(0);
        cat.swim(1);
        cat.swim(2);

        dog.swim(5);
        dog.swim(10);
        dog.swim(15);

        // прыжок: кот 2 м., собака 0.5 м.;
        cat.jumpOverObstacle(1);
        cat.jumpOverObstacle(2);
        cat.jumpOverObstacle(3);

        dog.jumpOverObstacle(0.4);
        dog.jumpOverObstacle(0.5);
        dog.jumpOverObstacle(0.6);

    }
}
