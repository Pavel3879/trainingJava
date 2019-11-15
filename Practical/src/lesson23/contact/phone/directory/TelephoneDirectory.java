package lesson23.contact.phone.directory;

import java.util.HashMap;
import java.util.LinkedList;

public class TelephoneDirectory {
    HashMap<String, LinkedList> hm = new HashMap<>();

    public void add(String name, String phone, String email) {
        if (!hm.containsKey(name)) {
            hm.put(name, new LinkedList<Contact>());
        }
        //можно допилить проверку тлф и email, дабы не вносить дубликаты
        hm.get(name).add(new Contact(phone, email));
    }

    public void printContactInfo(String name) {
        System.out.printf("%s - %s\n", name, hm.containsKey(name) ? hm.get(name).toString() : "информация не найдена");
    }

}
