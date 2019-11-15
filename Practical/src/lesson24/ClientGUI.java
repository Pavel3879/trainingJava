package lesson24;


import lesson24.logger.LogItem;
import lesson24.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;
import java.util.Date;
import java.text.DateFormat;

public class ClientGUI extends JFrame implements ActionListener, KeyListener, WindowListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static double dividerLocation = 0.7;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final DefaultListModel userListModel = new DefaultListModel();
    private final JList<String> userList = new JList<>(userListModel);

    private final JSplitPane splitHorizontal = new JSplitPane();

    static Logger logger;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");
        setAlwaysOnTop(true);

        log.setLineWrap(true);
        JScrollPane scrollLog = new JScrollPane(log);
        scrollLog.setMinimumSize(new Dimension(200, HEIGHT));
        JScrollPane scrollUsers = new JScrollPane(userList);
        scrollUsers.setMinimumSize(new Dimension(100, HEIGHT));
        //окно чата закрыть бы для редактирования
        //log.setEnabled(false);

        splitHorizontal.setLeftComponent(scrollLog);
        splitHorizontal.setRightComponent(scrollUsers);
        splitHorizontal.setOneTouchExpandable(true);
        splitHorizontal.setDividerLocation(dividerLocation);

        String[] users = {"user1", "user2", "user3", "user4",
                "user5", "user6", "user7", "user8", "a very_long_named_user_in_this_chat"};

        for (int i = 0; i < users.length; i++) {
            userListModel.addElement(users[i]);
        }
        //userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);
        tfMessage.addKeyListener(this);
        btnSend.addActionListener(this);
        btnLogin.addActionListener(this);

        this.addWindowListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        //add(scrollLog, BorderLayout.CENTER);
        //add(scrollUsers, BorderLayout.EAST);
        add(splitHorizontal, BorderLayout.CENTER);

        add(panelBottom, BorderLayout.SOUTH);
        add(panelTop, BorderLayout.NORTH);
        setVisible(true);
    }

    void saveLogger() {
        if (logger != null) {
            try (FileOutputStream outputStream = new FileOutputStream(logger.getFileName());
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
                objectOutputStream.writeObject(logger);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void loadLogger() {
        String login = tfLogin.getText();
        try (FileInputStream fileInputStream = new FileInputStream(Logger.getFileName(login));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            logger = (Logger) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (logger == null) logger = new Logger(login);
        log.setText("");
        for (LogItem logItem: logger.getLogItems(100)) {
            String msg = String.format("%s: %s\n", dateFormat.format(logItem.getDate()), logItem.getMsg());
            log.append(msg);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend) {
            if (tfMessage.getText().trim().length() != 0) {
                String msg = String.format("%s: %s\n", dateFormat.format(new Date()), tfMessage.getText());
                log.append(msg);
                /* В теории данный функционал должен быть доступен только после залогинивания
                 * и при удачном подключении для соответствующего логина создаем logger,
                 * но пока все это не реализовано проверяем создан ли logger и если нет, то создаем
                 */
                if (logger == null) logger = new Logger(tfLogin.getText());
                logger.Add(LogItem.TypeMsg.OUT, logger.getLogin(), tfMessage.getText());
            }
            tfMessage.setText("");
        } else if (src == btnLogin) {
            //тут куча проверок, если все хорошо, то выгружаем текущий лог, при его наличии, и подгружаем лог для нового пользователя
            saveLogger();
            log.setText("");
            loadLogger();
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = e.getClass().getCanonicalName() + ": " +
                e.getMessage() + "\n\t" + ste[0];

        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object src = e.getSource();
        if (src == tfMessage) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) btnSend.doClick();
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //*
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //*
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveLogger();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
