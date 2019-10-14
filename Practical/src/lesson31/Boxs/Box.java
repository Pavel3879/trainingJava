package lesson31.Boxs;

import lesson31.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public abstract class Box<T extends Fruit, B extends Box> {
    private List<T> fruitList;

    public Box() {
        this.fruitList = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        fruitList.add(fruit);
    }

    public float getWeight() {
        float result = 0;
        for (int i = 0; i < fruitList.size(); i++) {
            result += fruitList.get(i).getWeight();
        }
        return result;
    }

    public boolean compare(Box box) {
        return this.getWeight() == box.getWeight();
    }

    public List<T> getFruitList() {
        return fruitList;
    }

    public void fill(B box) {
        this.fruitList.addAll(box.getFruitList());
        box.getFruitList().clear();
    }

    public String weightInfo() {
        return String.format("%s вес - %-3.1f%n",  toString(), getWeight());
    }
}
