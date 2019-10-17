package lesson31.Boxs;

import lesson31.fruits.Orange;

public final class OrangeBox extends Box<Orange, OrangeBox> {

    @Override
    public String toString() {
        return "коробка с апельсинами";
    }

}
