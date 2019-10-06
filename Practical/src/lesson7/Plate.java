package lesson7;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int food) {
        if (food > this.food) {
            return false;
        }
        this.food -= food;
        return true;
    }

    public void info() {
        System.out.println("Осталось корма в тарелке: " + this.food);
    }

    public int getFood() {
        return food;
    }

    public void addFood(int food) {
        this.food += food;
        System.out.printf("В тарелку добавлено %d корма\n", food);
    }
}
