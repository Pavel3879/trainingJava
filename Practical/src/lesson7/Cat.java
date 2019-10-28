package lesson7;

import lesson7.Plate;

public class Cat {

    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    private String condition() {
        return satiety ? "сыт" : "голоден";
    }

    @Override
    public String toString() {
        return String.format("%s, аппетит - %d, состояние - %s", name, appetite, condition());
    }

    public void info() {
        System.out.printf("%s, состояние - %s\n", name, condition());
    }

    public void eat(Plate plate) {
        satiety = plate.decreaseFood(appetite);
        if (satiety) {
            System.out.printf("%s съел %d корма\n", name, appetite);
        } else {
            System.out.printf("В тарелке недостаточно корма для %s, необходимо %d, в наличии %d\n",
                    name, appetite, plate.getFood());
        }
    }

}
