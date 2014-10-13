package com.starbattle.accounts.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.server.web.WebServer;
import org.h2.tools.Server;

public class DatabaseConnection {
	private static final String CONNSTRING = "jdbc:h2:./StarBattle";
	private static final String USERNAME = "StarBattle";
	private static final String PASSWD = "notSecure";
	Connection conn;

	public DatabaseConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection(CONNSTRING, USERNAME, PASSWD);
		
        WebServer webServer = new WebServer();
        Server web = new Server(webServer, new String[] { "-webPort", "8082" });
        web.start();
        Server server = new Server();
        //server.web = web;
        webServer.setShutdownHandler(server);
        String url = webServer.addSession(conn);
        System.out.println("To debug Database connect to the following URL:");
        System.out.println(url);
        System.out.println("Or log in with\n\tJDBC URL: " + CONNSTRING + "\n\tUsername: " + USERNAME + "\n\tPassword: " + PASSWD);
        
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		conn.close();
	}

	public void test() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TEST");
		ResultSet rs = stmt.executeQuery();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		DatabaseConnection dc = new DatabaseConnection();
		//dc.test();
		
		Thread.sleep(199999);
	}
}
