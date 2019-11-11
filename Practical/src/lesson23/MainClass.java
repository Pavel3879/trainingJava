package lesson23;

import lesson23.contact.phone.directory.TelephoneDirectory;

import java.util.HashMap;

public class MainClass {
    static String[] strings = {
            "word1", "word2", "word3", "word4", "word5",
            "word2", "word3", "word4", "word5", "word6",
            "word3", "word4", "word5", "word6", "word7",
            "word4", "word5", "word6", "word7", "word8"};

    public static void main(String[] args) {
        System.out.println(1);
        /*
         *1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
         *из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
         */
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            if (hm.containsKey(strings[i])) {
                hm.put(strings[i], hm.get(strings[i]) + 1);
            } else {
                hm.put(strings[i], 1);
            }
        }

        System.out.println("список уникальных слов");
        hm.forEach((k, v) -> {
            if (v == 1) System.out.printf("key: %s\n", k);
        });
        System.out.println("Посчитать сколько раз встречается каждое слово.");
        System.out.println(hm.toString());

        System.out.println(2);
        /*
         *2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров и электронных почт.
         * В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона по фамилии.
         * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
         */
        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();
        telephoneDirectory.add("Иванов", "123456","email1@mail.ru");
        telephoneDirectory.add("Петров", "234567","email2@mail.ru");
        telephoneDirectory.add("Сидоров", "345678","email3@mail.ru");
        telephoneDirectory.add("Иванов", "456789","email4@mail.ru");
        telephoneDirectory.add("Иванов", "567890","email5@mail.ru");
        telephoneDirectory.add("Петров", "777777","email6@mail.ru");

        telephoneDirectory.printContactInfo("Иванов");
        telephoneDirectory.printContactInfo("Петров");
        telephoneDirectory.printContactInfo("Сидоров");
        telephoneDirectory.printContactInfo("unknown name");

    }
}
