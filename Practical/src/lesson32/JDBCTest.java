package lesson32;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCTest {

    private Connection connection = null;
    private String URL = "jdbc:sqlite:users.db";
    private List<String> listObsceneWords = new ArrayList<>();


    public static void main(String[] args) {

        JDBCTest test = new JDBCTest();
        if (test.open()) {
            //Добавить в сетевой чат авторизацию через базу данных SQLite.
            //Создаем тамблицу с логинами/паролями
            //test.createTableUsers();
            //Запрос на добавление логина/пароля
            //test.addLogin();
            //Запрос на смену логина
            //test.changeLogin();
            //вывод таблицы user
            //test.printUsers();

            //Добавить в БД таблицу для хранения "нецензурных слов или неправильных граммически слов"
            //test.createTableObsceneWords();
            //вывод таблицы ObsceneWords
            //test.printObsceneWords();
            //чтение ObsceneWords в буфер
            test.readObsceneWords();
            //контроль цензуры
            test.censor("word1 awgr sgbetdbh");
            test.censor("word awgr sgbetdbh");
            test.censor("word2 awgr sgbetdbh");
            test.censor("wordd awgr sgbetdbh");

            test.close();
        }


    }

    boolean open() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTableUsers() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table user(" +
                    "ID integer primary key autoincrement, " +
                    "LOGIN varchar(32)," +
                    "PASS varchar(32));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTableObsceneWords() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table ObsceneWords(" +
                    "ID integer primary key autoincrement, " +
                    "WORD varchar(32));");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement("insert into ObsceneWords(WORD) values(?)")) {
            statement.setString(1, "word1".toUpperCase());
            statement.addBatch();
            statement.setString(1, "word2".toUpperCase());
            statement.addBatch();
            statement.setString(1, "Word3".toUpperCase());
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    void printUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select ID, LOGIN, PASS from user order by LOGIN");
            while (rs.next()) {
                System.out.printf("login: %s, pass: %s\n", rs.getString("LOGIN"), rs.getString("PASS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void printObsceneWords() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select WORD from ObsceneWords order by WORD");
            while (rs.next()) {
                System.out.println(rs.getString("WORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void readObsceneWords() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select WORD from ObsceneWords order by WORD");
            while (rs.next()) {
                listObsceneWords.add(rs.getString("WORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        System.out.println("Enter pass: ");
        String pass = scanner.nextLine();

        if (loginIsFree(login)) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "insert into user (LOGIN, PASS) values(?, ?)")) {
                statement.setString(1, login);
                statement.setString(2, pass);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("this login is not free");
        }

    }

    void changeLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter old login: ");
        String oldLogin = scanner.nextLine();
        System.out.println("Enter new login: ");
        String newLogin = scanner.nextLine();
        System.out.println("Enter pass: ");
        String pass = scanner.nextLine();

        //можно добавить проверку пароля при смене логина
        if (loginIsFree(oldLogin)) {
            System.out.println("old login does not exist");
            return;
        }

        if (!loginIsFree(newLogin)) {
            System.out.println("new login is not free");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "update user set LOGIN = :NEWLOGIN where LOGIN like :OLDLOGIN")) {
            statement.setString(1, newLogin);
            statement.setString(2, oldLogin);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    boolean loginIsFree(String login) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select count(1) cnt from user where LOGIN like :LOGIN")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("cnt") == 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    void censor(String msg) {
        String[] splitstring = msg.split(" ");
        for (int i = 0; i < splitstring.length; i++) {
            String s = splitstring[i];
            List<String> resultList = listObsceneWords.stream().filter(item -> item.equalsIgnoreCase(s)).collect(Collectors.toList());
            if (resultList.size() > 0) {
                System.out.println("не прошли цензуру");
                return;
            }
        }
        System.out.println("цензура пройдена");
    }


}
