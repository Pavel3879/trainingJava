package lesson36;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        int[] array = {1, 4, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(calculator.metod1(array)));

        int[] array2 = {1, 2, 3, 5, 6, 7, 4};
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.toString(calculator.metod1(array2)));


        int[] array3 = {8, 2, 3, 5, 6, 7};
        System.out.println(Arrays.toString(array3));
        System.out.println(calculator.metod2(array3));


    }
}
