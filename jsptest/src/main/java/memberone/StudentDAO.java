package memberone;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

public class StudentDAO {
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.err.println("Connection failed");
		}
		return conn;
	}
}
