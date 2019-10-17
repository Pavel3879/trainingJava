package lesson6.animals;

public class Dog extends Animal {

    public Dog() {
        super(500, 10, 0.5);
    }

    @Override
    public String toString() {
        return "Собака";
    }

}