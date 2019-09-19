package lesson3;

import java.util.Random;
import java.util.Scanner;

public class MainClass {

    static Scanner scaner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        //1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
        // При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
        // После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
        guessMumber();

        //2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
        // "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
        // "pear", "pepper", "pineapple", "pumpkin", "potato"};
        //При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
        //сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
        //apple – загаданное
        //apricot - ответ игрока
        //ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
        //Для сравнения двух слов посимвольно, можно пользоваться:
        //String str = "apple";
        //str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
        //Играем до тех пор, пока игрок не отгадает слово
        //Используем только маленькие буквы
        guessWord();

    }

    static void guessMumber() {
        final int countAttempts = 3;
        int numberSearch;
        int numberEntered;
        boolean result;

        do {
            numberSearch = random.nextInt(10);
            result = false;
            for (int attempt = 0; attempt < countAttempts; attempt++) {
                System.out.println("Введите число от 0 до 9");
                numberEntered = scaner.nextInt();
                if (numberEntered == numberSearch) {
                    result = true;
                    break;
                } else if (numberEntered > numberSearch) {
                    System.out.println("Перелет");
                } else if (numberEntered < numberSearch) {
                    System.out.println("Недолет");
                }
            }
            if (result) {
                System.out.println("Число угадано!");
            } else {
                System.out.println("Вы исчерпали лимит попыток, загаданное число - " + numberSearch);
            }

            System.out.println("Хотите повторить? 1 – да / 0 – нет");
        } while (scaner.nextInt() == 1);
    }

    static void guessWord() {
        final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String wordSearch = words[random.nextInt(words.length)];
        String wordEntered;
        //System.out.println(wordSearch);
        do {
            System.out.println("Угадайте слово");
            wordEntered = scaner.nextLine();
            if (wordEntered.equals(wordSearch)) {
                System.out.println("Слово угадано!");
                break;
            } else {
                for (int i = 0; i < 15; i++) {
                    if (wordSearch.length() > i && wordEntered.length() > i) {
                        System.out.print(wordSearch.charAt(i) == wordEntered.charAt(i) ? wordSearch.charAt(i) : '#');
                    } else {
                        System.out.print('#');
                    }
                }
                System.out.println();
            }
        } while (true);
    }

}
