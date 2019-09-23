package lesson4;

//import java.util.Arrays;
//import java.util.Random;
import java.util.Scanner;

public class MainClass {

    static Scanner sc = new Scanner(System.in);

    final static int SIZE = 5;
    final static int LINE_SIZE = 4;

    final static char DOT_X = 'X';
    final static char DOT_O = 'O';
    final static char DOT_EMPTY = '.';

    static int[][] map = new int[SIZE][SIZE];


    public static void main(String[] args) {
        //инициализация массива
        initMap();
        //рисуем игровое поле
        printMap();
        do {
            //ход игрока
            humenTurn();
            if (checkWin()) {
                System.out.print("Вы победили!");
                break;
            }
            if (isFull()) {
                break;
            }
            //ход ii
            iiTurn();
            if (checkWin()) {
                System.out.print("Победил ii!");
                break;
            }
            if (isFull()) {
                break;
            }
        } while (true);

    }


    static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = 0;
            }
        }
    }

    static void printMap() {
        System.out.print(" ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] < 0) {
                    System.out.print(DOT_O + " ");
                } else if (map[i][j] > 0) {
                    System.out.print(DOT_X + " ");
                } else {
                    System.out.print(DOT_EMPTY + " ");
                }
            }
            System.out.println();
        }
    }

    static boolean isCellValid(int x, int y) {
        return (x >= 0 && x < SIZE && y >= 0 && y < SIZE && map[y][x] == 0);
    }

    static void humenTurn() {
        do {
            System.out.println("Ваш ход X/Y");
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            if (isCellValid(x, y)) {
                map[y][x] = 1;
                break;
            }
        } while (true);
        printMap();
    }

    static void iiTurn() {
        iiThink();
        printMap();
    }

    static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        System.out.println("Ничья!");
        return true;
    }

    static boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkWin(i, j)) return true;                   //вертикаль
                if (checkWin(0, i, j)) return true;             //горизонталь
                if (checkWin(1, i - j, j)) return true;     //диагональ 1
                if (checkWin(-1, i + j, j)) return true;    //диагональ 2
            }
        }
        return false;
    }

    static boolean checkWin(int i, int j) {
        int sum = 0;
        for (int y = i; y < LINE_SIZE + i; y++) {
            if (y >= SIZE || j >= SIZE) break;
            sum = sum + map[y][j];
        }
        return (Math.abs(sum) >= LINE_SIZE);
    }

    static boolean checkWin(int k, int b, int j) {
        int sum = 0;
        int y;
        for (int x = j; x < LINE_SIZE + j; x++) {
            y = k * x + b;
            if (y < 0 || y >= SIZE || x >= SIZE) break;
            sum = sum + map[y][x];
        }
        return (Math.abs(sum) >= LINE_SIZE);
    }


    static void iiThink() {
        //ii обдумывает ход
        //поиск угрозы
        int threat = 0;
        int maxThreat = 0;
        int x = 0;
        int y = 0;
        int metod = -1;
        boolean result = false;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                threat = iiThink(i, j);
                if (threat > maxThreat) {
                    maxThreat = threat;
                    x = j;
                    y = i;
                    metod = 0;
                }
                threat = iiThink(0, i, j);
                if (threat > maxThreat) {
                    maxThreat = threat;
                    x = j;
                    y = i;
                    metod = 1;
                }
                threat = iiThink(1, i - j, j);
                if (threat > maxThreat) {
                    maxThreat = threat;
                    x = j;
                    y = i;
                    metod = 2;
                }
                threat = iiThink(-1, i + j, j);
                if (threat > maxThreat) {
                    maxThreat = threat;
                    x = j;
                    y = i;
                    metod = 3;
                }
            }
        }

        if (metod < 0) {
            result = false;
        } else if (metod == 0) {
            result = iiGo(y, x);
        } else if (metod == 1) {
            result = iiGo(0, y, x);
        } else if (metod == 2) {
            result = iiGo(1, y - x, x);
        } else if (metod == 3) {
            result = iiGo(-1, y + x, x);
        }

        if (!result) {
            //интелекта не хватило, заполняем первое попавшееся
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == 0) {
                        map[i][j] = -1;
                        break;
                    }
                }
            }
        }

    }

    static int iiThink(int i, int j) {
        int sum = 0;
        for (int y = i; y < LINE_SIZE + i; y++) {
            if (y >= SIZE || j >= SIZE || (map[y][j] == -1 && y == i)) {
                sum = 0;
                break;
            }
            sum = sum + map[y][j];
        }
        return sum;
    }

    static int iiThink(int k, int b, int j) {
        int sum = 0;
        int y;
        for (int x = j; x < LINE_SIZE + j; x++) {
            y = k * x + b;
            if (y < 0 || y >= SIZE || x >= SIZE || (map[y][x] == -1 && x == j)) {
                sum = 0;
                break;
            }
            sum = sum + map[y][x];
        }
        return sum;
    }

    static boolean iiGo(int i, int j) {
        for (int y = i; y < LINE_SIZE + i; y++) {
            if (y >= SIZE || j >= SIZE) break;
            if (map[y][j] == 0) {
                map[y][j] = -1;
                return true;
            }
        }
        return false;
    }

    static boolean iiGo(int k, int b, int j) {
        int y;
        for (int x = j; x < LINE_SIZE + j; x++) {
            y = k * x + b;
            if (y < 0 || y >= SIZE || x >= SIZE) break;
            if (map[y][x] == 0) {
                map[y][x] = -1;
                return true;
            }
        }
        return false;
    }

}
