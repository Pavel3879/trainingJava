package lesson2;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {

        System.out.println("1 задание");
        //1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int[] array1 = {1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(array1));
        reverseArray(array1);
        System.out.println(Arrays.toString(array1));
        System.out.println("============================================================================================");

        //2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        System.out.println("2 задание");
        int[] array2 = new int[8];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = i * 3;
        }
        System.out.println(Arrays.toString(array2));
        System.out.println("============================================================================================");

        //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        System.out.println("3 задание");
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(array3));
        for (int i = 0; i < array3.length; i++) {
            if (array3[i] < 6) array3[i] = array3[i] * 2;
        }
        System.out.println(Arrays.toString(array3));
        System.out.println("============================================================================================");

        //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
        // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        System.out.println("4 задание");
        int[][] array4 = new int[9][9];
        for (int i = 0; i < array4.length; i++) {
            array4[i][i] = 1;
            array4[i][array4[i].length - i - 1] = 1;
        }
        for (int i = 0; i < array4.length; i++) {
            System.out.println(Arrays.toString(array4[i]));
        }
        System.out.println("============================================================================================");

        //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        System.out.println("5 задание");
        int[] array5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = array5[0];
        int max = array5[0];
        for (int i = 0; i < array5.length; i++) {
            min = Math.min(min, array5[i]);
            max = Math.max(max, array5[i]);
        }
        System.out.println(min);
        System.out.println(max);
        System.out.println("============================================================================================");

        //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
        // если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
        // checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
        System.out.println("6 задание");
        int[] array6 = {12, 1, 1, 1, 2, 1, 6};
        System.out.println(checkBalance(array6));                                                 // 1 вариант
        System.out.println(checkBalance(array6, 0, 0, 0, 0));   // 2 вариант
        System.out.println("============================================================================================");

        //7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
        // при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        // Немного отклонился от исходных данных, ввел 3 параметр

        System.out.println("7 задание");
        int[] array7 = {1, 2, 3, 4, 5, 6, 7};
        offsetArray(array7, 2);
        System.out.println(Arrays.toString(array7));
        System.out.println("============================================================================================");


    }

    static void reverseArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0 ? 1 : 0);
        }
    }

    static boolean checkBalance(int[] array) {
        boolean result = false;
        int[] array1 = new int[array.length];
        int[] array2 = new int[array.length];

        array1[0] = array[0];
        array2[0] = array[array.length - 1];

        //прямая сумма
        for (int i = 1; i < array.length; i++) {
            array1[i] = array1[i - 1] + array[i];
        }
        //обратная сумма
        for (int i = 1; i < array.length; i++) {
            array2[i] = array2[i - 1] + array[array.length - i - 1];
        }

        int i = 0;
        int j = 0;
        do {
            if (array1[i] < array2[j]) i++;
            else if (array1[i] > array2[j]) j++;
            else if (array1[i] == array2[j] && (i + j) == array.length - 2) result = true;
            else {
                i++;
                j++;
            }
            if ((i + j + 2) > array.length || result) break;
        } while (true);
        System.out.println(i);
        System.out.println(j);
        return result;
    }

    static boolean checkBalance(int[] array, int leftCnt, int leftSum, int rightCnt, int rightSum) {
        if ((leftCnt + rightCnt) > array.length) return false;
        if (leftSum == rightSum && (leftCnt + rightCnt) == array.length) {
            System.out.printf("leftCnt %d; leftSum %d", leftCnt, leftSum);
            System.out.println("");
            System.out.printf("rightCnt %d; rightSum %d", rightCnt, rightSum);
            System.out.println("");
            return true;
        } else if (leftSum < rightSum) {
            leftSum = leftSum + array[leftCnt];
            return checkBalance(array, ++leftCnt, leftSum, rightCnt, rightSum);
        } else if (leftSum > rightSum) {
            rightSum = rightSum + array[array.length - rightCnt - 1];
            return checkBalance(array, leftCnt, leftSum, ++rightCnt, rightSum );
        } else {
            leftSum = leftSum + array[leftCnt];
            rightSum = rightSum + array[array.length - rightCnt - 1];
            return checkBalance(array, ++leftCnt, leftSum, ++rightCnt, rightSum);
        }
    }

    static void offsetArray(int[] array, int offset, int num) {
        if (num < array.length) {
            int i = num + offset;
            if (i >= array.length) {
                i %= array.length;
            } else if (i < 0) {
                i = array.length + i;
            }
            int item = array[num];
            offsetArray(array, offset, num + 1);
            array[i] = item;
        }
    }

    static void offsetArray(int[] array, int n) {
        if (Math.abs(n) >= array.length) n = n % array.length;
        offsetArray(array, n, 0);
    }

}