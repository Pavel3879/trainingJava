package lesson36;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Calculator {

    int[] metod1(int[] array) {
        if (array == null) {
            return new int[]{};
        }
        int num = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                num = i;
                break;
            }
        }
        if (num < 0) {
            throw new RuntimeException("Массив не содержит 4!");
        } else {
            return Arrays.copyOfRange(array, num + 1, array.length);
        }
    }

    boolean metod2(int[] array) {
        if (array == null) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1 || array[i] == 4) {
                return true;
            }
        }
        return false;
    }

}
