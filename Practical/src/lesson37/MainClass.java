package lesson37;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class MainClass {
    final static int MIN_PRIORITY = 1;
    final static int MAX_PRIORITY = 10;

    public static void main(String[] args) {
        start(TestClass1.class);
        start("lesson37.TestClass1");
        start(TestClass2.class);  //тест ошибки
    }

    public static void start(String className) {
        Class aClass;
        try {
            aClass = Class.forName(className);
            start(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class aClass) {
        Map<String, Method> commands = new TreeMap<>();   //в TreeMap автоматическая сортировка по ключу
        int countMethodBefore = 0;
        int countMethodAfter = 0;
        int countMethodTest = 0;

        System.out.println("\n" + aClass.getSimpleName() + ": ");

        for (Method method : aClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                commands.put(String.format("%02d", MIN_PRIORITY - 1) + method.getName(), method);
                countMethodBefore++;
            }
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                commands.put(String.format("%02d", test.priority()) + method.getName(), method);
                countMethodTest++;
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                commands.put(String.format("%02d", MAX_PRIORITY + 1) + method.getName(), method);
                countMethodAfter++;
            }
        }

        System.out.println("countMethodBefore:\t" + countMethodBefore);
        System.out.println("countMethodTest:\t" + countMethodTest);
        System.out.println("countMethodAfter:\t" + countMethodAfter);

        for (String key : commands.keySet()) {
            System.out.println(key);
        }
        System.out.println();

        if (countMethodTest == 0) {
            throw new RuntimeException ("В качестве тестов выступают классы с наборами методов с аннотациями @Test!");
        }

        if (countMethodBefore != 1 || countMethodAfter != 1) {
            throw new RuntimeException ("Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре!");
        }

        try {
            Object testCase = aClass.newInstance();
            for (String key : commands.keySet()) {
                commands.get(key).invoke(testCase);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
