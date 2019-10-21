package lesson33;

import lesson33.logger.LogItem;
import lesson33.logger.Logger;

import java.io.*;
import java.util.ArrayList;

public class MainClass {

    static Logger logger = new Logger("Pavel");
    static Logger loggerNew;

    public static void main(String[] args) throws IOException {

        //заполняем и сохраняем все, хотя надо бы еще добавить лимит записей дабы лог не разростался
        fillLogger();
        printTopMsg(logger,20);
        saveLogger();

        //восстанавливаем лог из файла для логина Pavel
        loadLogger("Pavel");
        printTopMsg(loggerNew, 100);

    }

    static void fillLogger() {
        for (int i = 1; i < 500; i += 2) {
            logger.Add(LogItem.TypeMsg.IN, "Ivan", "msg" + i);
            logger.Add(LogItem.TypeMsg.OUT, "Pavel", "msg" + (i + 1));
        }
    }

    static void printTopMsg(Logger log, int cnt) {
        if (log != null) {
            ArrayList<LogItem> list = log.getLogItems(cnt);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
        }
    }

    static void saveLogger() {
        try (FileOutputStream outputStream = new FileOutputStream(logger.getFileName());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(logger);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadLogger(String login) {
        try (FileInputStream fileInputStream = new FileInputStream(String.format("history_%s.txt", login));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            loggerNew = (Logger) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
