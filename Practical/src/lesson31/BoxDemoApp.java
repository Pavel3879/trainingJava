package lesson31;

import lesson31.Boxs.AppleBox;
import lesson31.Boxs.OrangeBox;
import lesson31.fruits.Apple;
import lesson31.fruits.Orange;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BoxDemoApp {

    public static void main(String[] args) {

        Integer intArr[] = new Integer[]{1, 2};
        String strArray[] = new String[]{"str1", "str2"};

        // 1.
        System.out.println("1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);");
        System.out.println("массив intArr до swap: " + Arrays.toString(intArr));
        swap(intArr, 0, 1);
        System.out.println("массив intArr после swap: " + Arrays.toString(intArr));

        System.out.println("массив strArray до swap: " + Arrays.toString(strArray));
        swap(strArray, 0, 1);
        System.out.println("массив strArray после swap: " + Arrays.toString(strArray));


        // 2.
        System.out.println("2. Написать метод, который преобразует массив в ArrayList;");
        ArrayList arrayList = new ArrayList();

        convertArrayToList(intArr, arrayList);
        System.out.println(Arrays.toString(intArr));
        System.out.println(arrayList.toString());

        arrayList.clear();
        convertArrayToList(strArray, arrayList);
        System.out.println("strArray: " + Arrays.toString(strArray));
        System.out.println("arrayList: " + arrayList.toString());


        // 3.
        System.out.println("3. Большая задача;");
        AppleBox appleBox1 = new AppleBox();
        AppleBox appleBox2 = new AppleBox();
        OrangeBox orangeBox1 = new OrangeBox();
        OrangeBox orangeBox2 = new OrangeBox();

        for (int i = 0; i < 10; i++) {
            appleBox1.addFruit(new Apple());
            orangeBox1.addFruit(new Orange());
        }

        System.out.print(appleBox1.weightInfo());
        System.out.print(orangeBox1.weightInfo());
        System.out.println("Сравниваем appleBox1 и orangeBox1 - " + orangeBox1.compare(appleBox1));

        for (int i = 0; i < 5; i++) {
            appleBox1.addFruit(new Apple());
            appleBox2.addFruit(new Apple());
            orangeBox2.addFruit(new Orange());
        }

        System.out.print(appleBox1.weightInfo());
        System.out.print(orangeBox1.weightInfo());
        System.out.println("Сравниваем appleBox1 и orangeBox1 - " + orangeBox1.compare(appleBox1));

        appleBox2.addFruit(new Apple());

        System.out.println("пересыпаем яблоки");
        System.out.print(appleBox1.weightInfo());
        System.out.print(appleBox2.weightInfo());
        System.out.println("пересыпали");
        appleBox1.fill(appleBox2);//запрет пересыпки сам в себя не реализован!
        System.out.print(appleBox1.weightInfo());
        System.out.print(appleBox2.weightInfo());

        System.out.println("пересыпаем апельсины");
        System.out.print(orangeBox1.weightInfo());
        System.out.print(orangeBox2.weightInfo());
        System.out.println("пересыпали");
        orangeBox1.fill(orangeBox2);//запрет пересыпки сам в себя не реализован!
        System.out.print(orangeBox1.weightInfo());
        System.out.print(orangeBox2.weightInfo());


    }

    static <T> void swap(T array[], Integer index1, Integer index2) {
        if (index1 == index2 || index1 >= array.length || index2 >= array.length) {
            return;
        }
        T temp = array[index2];
        array[index2] = array[index1];
        array[index1] = temp;
    }

    static <T> void convertArrayToList(T array[], ArrayList<T> list) {
        Collections.addAll(list, array);
    }


}