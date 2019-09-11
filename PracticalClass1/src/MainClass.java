public class MainClass {
    public static void main(String[] args) {

        //2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte variableByte = 127;
        short variableShort = 32767;
        int variableInt = 2147483647;
        long variableLong = 21474836470L;
        float variableFloat = 1000.123F;
        double variableDouble = 1000.123456;
        char variableChar = 'A';
        boolean variableBoolean = true;
        String variableString = "string";

        //3. Написать метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;
        System.out.println(calculate(2, 3, 10, 5));

        //4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
        // если да – вернуть true, в противном случае – false;
        System.out.println(hitTesting(2, 3));
        System.out.println(hitTesting(6, 4));
        System.out.println(hitTesting(6, 7));
        System.out.println(hitTesting(11, 9));
        System.out.println(hitTesting(11, 12));

        //5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль
        // положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
        procedureTestPositive(-1);
        procedureTestPositive(0);
        procedureTestPositive(1);

        //6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
        System.out.println(functionTestPositive(-1));
        System.out.println(functionTestPositive(0));
        System.out.println(functionTestPositive(1));

        //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
        // метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
        printName("Павел");

        //8. Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
        // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
        testLeapYear(0);
        testLeapYear(20);
        testLeapYear(100);
        testLeapYear(140);
        testLeapYear(200);
        testLeapYear(300);
        testLeapYear(400);
        testLeapYear(500);
        testLeapYear(800);
        testLeapYear(1200);

    }

    static double calculate(int a, int b, int c, int d) {
        //Метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат;
        return a * (b + (c / d));
    }

    static boolean hitTesting(int a, int b) {
        //Метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно);
        int res = a + b;
        return (res > 10) && (res <= 20);
    }

    static void procedureTestPositive(int a) {
        //Метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль
        // положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
        System.out.println("число " + a + (a < 0 ? " отрицательное" : " положительное"));
    }

    static boolean functionTestPositive(int a) {
        //Метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
        return a < 0;
    }

    static void printName(String name) {
        //Метод, которому в качестве параметра передается строка, обозначающая имя,
        // метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
        System.out.println("Привет, " + name + "!");
    }

    static void testLeapYear(int year) {
        //Метод, который определяет является ли год високосным, и выводит сообщение в консоль.
        /*
        год, номер которого кратен 400, — високосный;
        остальные годы, номер которых кратен 100, — невисокосные;
        остальные годы, номер которых кратен 4, — високосные.
        */
        boolean res = (year > 0) && ((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0)));
        System.out.println(year + " год " + (res ? "високосный" : "невисокосный"));
    }

}
