package xogame;

import java.util.Random;
import java.util.Scanner;

public class Logic {

    static int[][] map;

    static int SIZE_X;
    static int SIZE_Y;
    static int LINE_SIZE;

    static int lineWinX1;
    static int lineWinY1;
    static int lineWinX2;
    static int lineWinY2;

    static Random random = new Random();
    static boolean gameFinished = false;

    static void go() {
        gameFinished = true;
        if (checkWin()) {
            System.out.println("Вы победили! Поздравляем!");
            return;
        }
        if (isFull()) {
            return;
        }

        iiTurn();
        if (checkWin()) {
            System.out.println("Компьютер победил! ");
            return;
        }
        if (isFull()) {
            return;
        }
        gameFinished = false;
    }

    static void initMap() {
        map = new int[SIZE_Y][SIZE_X];
        lineWinX1 = -1;
        lineWinY1 = -1;
        lineWinX2 = -1;
        lineWinY2 = -1;
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                map[i][j] = 0;
            }
        }
    }

    static void humanTurn(int x, int y) {
        if (gameFinished) {
            return;
        }
        if (isCellValid(y, x)) {
            map[y][x] = 1;
            go();
        }
    }

    static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE_X || y >= SIZE_Y) {
            return false;
        }
        return map[y][x] == 0;
    }

    static void iiTurn() {
        iiThink();
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

        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
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
            for (int i = 0; i < SIZE_Y; i++) {
                for (int j = 0; j < SIZE_X; j++) {
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
            if (y >= SIZE_Y || j >= SIZE_Y || (map[y][j] == -1 && y == i)) {
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
            if (y < 0 || y >= SIZE_Y || x >= SIZE_X || (map[y][x] == -1 && x == j)) {
                sum = 0;
                break;
            }
            sum = sum + map[y][x];
        }
        return sum;
    }

    static boolean isFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        System.out.println("Ничья!");
        return true;
    }

    static boolean checkWin() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
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
            if (y >= SIZE_Y || j >= SIZE_Y) break;
            sum = sum + map[y][j];
            if (Math.abs(sum) >= LINE_SIZE) {
                lineWinX1 = j;
                lineWinY1 = i;
                lineWinX2 = j;
                lineWinY2 = y;
                return true;
            }
        }
        return false;
    }

    static boolean checkWin(int k, int b, int j) {
        int sum = 0;
        int x0 = j;
        int y0 = k * x0 + b;
        int y;

        for (int x = j; x < LINE_SIZE + j; x++) {
            y = k * x + b;
            if (y < 0 || y >= SIZE_Y || x >= SIZE_X) break;
            sum = sum + map[y][x];
            if (Math.abs(sum) >= LINE_SIZE) {
                lineWinX1 = x0;
                lineWinY1 = y0;
                lineWinX2 = x;
                lineWinY2 = y;
                return true;
            }
        }
        return false;
    }

    static boolean iiGo(int i, int j) {
        for (int y = i; y < LINE_SIZE + i; y++) {
            if (y >= SIZE_Y || j >= SIZE_Y) break;
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
            if (y < 0 || y >= SIZE_Y || x >= SIZE_X) break;
            if (map[y][x] == 0) {
                map[y][x] = -1;
                return true;
            }
        }
        return false;
    }


}
