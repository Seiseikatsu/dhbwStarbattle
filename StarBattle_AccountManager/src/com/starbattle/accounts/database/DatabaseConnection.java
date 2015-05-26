package com.starbattle.accounts.database;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.h2.server.web.WebServer;
import org.h2.tools.Server;

public class DatabaseConnection {
    private static final String CONNSTRING = "jdbc:h2:./StarBattle";
    private static final String USERNAME = "StarBattle";
    private static final String PASSWD = "notSecure";
    private Connection conn;
    private Server web;

    public DatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(CONNSTRING, USERNAME, PASSWD);

        WebServer webServer = new WebServer();
        web = new Server(webServer, new String[] { "-webPort", "8082" });
        web.start();
        Server server = new Server();
        webServer.setShutdownHandler(server);
        String url = webServer.addSession(conn);
        System.out.println("To debug Database connect to the following URL:");
        System.out.println(url);
        System.out.println("Or log in with\n\tJDBC URL: " + CONNSTRING + "\n\tUsername: " + USERNAME + "\n\tPassword: " + PASSWD);
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() throws SQLException {
        conn.close();
        web.stop();
    }

    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public void test() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TEST");
        stmt.executeQuery();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException, NoSuchAlgorithmException {
        new DatabaseConnection();

        Thread.sleep(199999);
    }
}
