package lesson33.logger;

import java.io.Serializable;
import java.util.ArrayList;

public class Logger implements Serializable {

    ArrayList<LogItem> logItems = new ArrayList<>(100);
    String login = new String();

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
        return String.format("history_%s.txt", this.login);
    }
}
