import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;

public class ConnectionPool {
    // max connection that connection pool can store
    private static final int CON_POOL_SIZE = 5;
    // just to read url username and password of sql database from file.
    private static Properties p = new Properties();
    private static String userName;
    private static String url;
    private static String password;
    // connections stored in connection pool
    private Vector<Connection> conPool = new Vector<>(CON_POOL_SIZE, 1);
    private Vector<Connection> activeConnections = new Stack<>();

    // private constructor, users should not instantiate a class object
    private ConnectionPool() {
        for (int i = 0; i < CON_POOL_SIZE; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conPool.add(connection);
        }
    }

    private static ConnectionPool instance = null;

    // get connections that are inside the connection pool.
    public static ConnectionPool getInstance() {
        if (instance == null) instance = new ConnectionPool();
        return instance;
    }

    // static block, activates before constructor and reads the datasource files.
    static {
        try (FileInputStream f = new FileInputStream(".//src//main//resources//datasource.properties")) {
            p.load(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = p.getProperty("url");
        userName = p.getProperty("username");
        password = p.getProperty("password");
    }

    // used to create connection
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // return the connection
    public synchronized Connection retrieve() {
        Connection newConn = null;
        if (conPool.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = (Connection) conPool.lastElement();
            conPool.removeElement(newConn);
        }
        activeConnections.addElement(newConn);
        return newConn;
    }

    // put the connections back
    public synchronized void putBack(Connection c) {
        if (c != null) {
            if (activeConnections.removeElement(c)) {
                conPool.addElement(c);
            } else {
                throw new NullPointerException("Connection is not in the Active Connections array");
            }
        }
    }
}