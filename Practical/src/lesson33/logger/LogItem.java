package lesson33.logger;

import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;


public class LogItem implements Serializable {

    public enum TypeMsg {
        IN, OUT
    }

    private Date date = new Date();
    private TypeMsg typeMsg;
    private String login = new String();
    private String msg = new String();

    public LogItem(TypeMsg typeMsg, String login, String msg) {
        this.typeMsg = typeMsg;
        this.login = login;
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public TypeMsg getTypeMsg() {
        return typeMsg;
    }

    public String getLogin() {
        return login;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return String.format("%s\t%s\t%-10s\t%s",
                dateFormat.format(getDate()),
                getTypeMsg().toString(),
                getLogin(),
                getMsg());
    }
}
