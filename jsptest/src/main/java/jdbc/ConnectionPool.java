package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public final class ConnectionPool {
	static { // runs before it becomes an object; i.e. code runs automatically when it is loaded into the memory
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// member variables
	private Vector<Connection> free;
	private Vector<Connection> used;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String password = "hr";
	private int initialCons = 10; // connections made when initialized
	private int maxCons = 20; // max number of connections
	private int numCons = 0; // total number of connections
	
	// singleton (1); a member variable must point to itself and it cannot be accessed from outside (private)
	private static ConnectionPool connectionPool;

	// singleton (3); create a method (usually getInstance()) that returns object ConnectionPool
	// from the outside, only this method can be used to access it (ConnectionPool.getInstance();)
	public static ConnectionPool getInstance() {
		try {
			if (connectionPool == null) { // if connection pool has not been made,
				synchronized (ConnectionPool.class) {
					connectionPool = new ConnectionPool(); // it creates one. if it exists, it returns the one that already exists
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connectionPool;
	}

	// singleton (2); the constructor cannot be allowed to be made from outside the class (private)
	private ConnectionPool() throws SQLException {
		free = new Vector<Connection>(initialCons);
		used = new Vector<Connection>(initialCons);
		while (numCons < initialCons) {
			addConnection();
		}
	}

	// saves connection into free connection
	private void addConnection() throws SQLException {
		free.add(getNewConnection());
	}

	// 새로운 커넥션 객체를 생성함.
	private Connection getNewConnection() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("About to connect to " + con);
		++numCons; // increase count of connections made
		return con;
	}

	// change connections activated on free to used
	public synchronized Connection getConnection() throws SQLException {
		// if connection doesnt exist on free, construct maxCons Connections
		if (free.isEmpty()) {
			while (numCons < maxCons) {
				addConnection();
			}
		}
		Connection _connection;
		_connection = free.get(free.size() - 1);
		free.remove(_connection);
		used.add(_connection);
		return _connection;
	}

	// return connections that are used to free
	public synchronized void releaseConnection(Connection _con) throws SQLException {
		boolean flag = false;
		if (used.contains(_con)) {
			used.remove(_con);
			numCons--;
			flag = true;
		} else {
			throw new SQLException("it does not exist in ConnectionPool");
		}
		try {
			if (flag) {
				free.add(_con);
				numCons++;
			} else {
				_con.close();
			}
		} catch (SQLException e) {
			try {
				_con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void closeAll() {// return all Connection resources
		// delete all connections on used
		for (int i = 0; i < used.size(); i++) {
			Connection _con = (Connection) used.get(i);
			used.remove(i--);
			try {
				_con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// delete all connections on free
		for (int i = 0; i < free.size(); i++) {
			Connection _con = (Connection) free.get(i);
			free.remove(i--);
			try {
				_con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getMaxCons() {
		return maxCons;
	}

	public int getNumCons() {
		return numCons;
	}
}