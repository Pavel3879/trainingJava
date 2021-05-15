package lesson22;

public class MainClass {
    final static int LENGTH = 4;

    public static void main(String[] args) {
        String[][] strings = new String[LENGTH][LENGTH];

        try {
            stringToArray2("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0", strings);
            System.out.println(calc(strings));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }

    static float calc(String[][] strings) throws NumberFormatException {
        int v;
        float result = 0;

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                v = Integer.parseInt(strings[i][j]);
                result += v;
            }
        }
        return result / 2;
    }

    static void stringToArray2(String s, String[][] strings) throws IndexOutOfBoundsException {
        String[] strings1;
        String[] strings2;
        strings1 = s.split("\n");
        checkArrayLength(strings1, LENGTH);
        for (int i = 0; i < strings1.length; i++) {
            strings2 = strings1[i].split(" ");
            checkArrayLength(strings2, LENGTH);
            strings[i] = strings2;//.clone();
        }
    }

    static void checkArrayLength(String[] strings, int length) throws IndexOutOfBoundsException {
        if (strings == null || strings.length != length) {
            throw new IndexOutOfBoundsException("Размер матрицы, полученной из строки, не равен 4x4");
        }
        ;
    }

}
