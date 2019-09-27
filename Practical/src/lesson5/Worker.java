package lesson5;

public class Worker {
    private String lastName;    //фамилия
    private String firstName;   //имя
    private String middleName;  //отчество
    private String post;        //должность
    private String email;       //email
    private String phone;       //телефон
    private int salary;         //зарплата
    private int age;            //возраст

    public Worker(String lastName, String firstName, String middleName, String post, String email, String phone, int salary, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void info() {
        System.out.println("========================================");
        System.out.printf("|%10s: %-25s |\n", "Фамилия", lastName);
        System.out.printf("|%10s: %-25s |\n", "Имя", firstName);
        System.out.printf("|%10s: %-25s |\n", "Отчество", middleName);
        System.out.printf("|%10s: %-25s |\n", "Возраст", age);
        System.out.printf("|%10s: %-25s |\n", "Должность", post);
        System.out.printf("|%10s: %-25s |\n", "email", email);
        System.out.printf("|%10s: %-25s |\n", "Телефон", phone);
        System.out.printf("|%10s: %-25s |\n", "Зарплата", salary);
        System.out.println("========================================");
    }

}
