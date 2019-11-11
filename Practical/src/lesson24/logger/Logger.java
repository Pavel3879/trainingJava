package lesson24.logger;

import java.io.Serializable;
import java.util.ArrayList;

public class Logger implements Serializable {

    private static String FILENAME = "history_%s.log";
    ArrayList<LogItem> logItems = new ArrayList<>(100);
    String login = new String();

    //при такой реализации накладываются требования к логину!!!
    public Logger(String login) {
        this.login = login;
    }

    public void Add(LogItem.TypeMsg typeMsg, String login, String msg) {
        logItems.add(new LogItem(typeMsg, login, msg));
    }

    public ArrayList<LogItem> getLogItems(int cnt) {
        int numStart;
        int numEnd;
        numStart = Math.max(logItems.size() - cnt, 0);
        numEnd = logItems.size();
        return new ArrayList<LogItem>(logItems.subList(numStart, numEnd));
    }

    public String getFileName() {
        return String.format("history_%s.log", this.login);
    }

    public static String getFileName(String login) {
        return String.format(FILENAME, login);
    }

    public String getLogin() {
        return login;
    }
}

