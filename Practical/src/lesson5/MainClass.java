package lesson5;

public class MainClass {
    public static void main(String[] args) {
        Worker[] workers = new Worker[5];
        workers[0] = new Worker("Иванов", "Иван", "Иванович", "Директор", "IIIvanov@mail.com", "+7-910-492-82-01", 15000, 40);
        workers[1] = new Worker("Петров", "Петр", "Петрович", "Бухгалтер", "PPPetrov@mail.com", "+7-910-492-82-02", 10000, 50);
        workers[2] = new Worker("Сидоров", "Роман", "Романович", "Спецалист", "RRSidorov@mail.com", "+7-910-492-82-10", 9000, 25);
        workers[3] = new Worker("Титова", "Наталья", "Владимировна", "Консультант", "NVTitiva@mail.com", "+7-910-492-82-11", 8000, 35);
        workers[4] = new Worker("Петрова", "Татьяна", "Сергеевна", "Секретарь", "TSPetrova@mail.com", "+7-910-492-82-03", 7000, 45);

        for (int i = 0; i < workers.length; i++) {
            if (workers[i].getAge() > 40) {
                workers[i].info();
            }
        }

    }
}
