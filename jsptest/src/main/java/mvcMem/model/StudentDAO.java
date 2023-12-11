package mvcMem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {

	private static StudentDAO instance = null;
	private StudentDAO() {}
	public static StudentDAO getInstance() {
		if(instance == null) {
			synchronized(StudentDAO.class) {
				instance = new StudentDAO();
			}
		} return instance;
	}
	
	private Connection getConnection() {
		Connection connection = null;
		try {
			Context init = new InitialContext();
			DataSource dataSource = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
			connection = dataSource.getConnection();
		} catch(NamingException e) {
			System.out.println("Failed to make connection: ");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("Failed to make connection: ");
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean idCheck(String id) {
		boolean result = true;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id=?");
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) result = false;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null)
				try { resultSet.close();
				} catch(SQLException e) {}
			if(preparedStatement != null)
				try { preparedStatement.close();
				} catch(SQLException e2) {}
			if(connection != null) 
				try {connection.close();
				} catch(SQLException e3) {}
		} return result;
	}
	public Vector<ZipCodeVO> zipcodeRead(String dong) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM zipcode WHERE dong like " + dong + "%");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ZipCodeVO tempZipCode = new ZipCodeVO();
				tempZipCode.setZipcode(resultSet.getString("zipcode"));
				tempZipCode.setSido(resultSet.getString("sido"));
				tempZipCode.setSido(resultSet.getString("gugun"));
				tempZipCode.setSido(resultSet.getString("dong"));
				tempZipCode.setSido(resultSet.getString("bunji"));
				vecList.addElement(tempZipCode);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null)
					try { preparedStatement.close();
					} catch(SQLException e2) {}
				if(connection != null) 
					try {connection.close();
					} catch(SQLException e3) {}
			} return vecList;
		}
		
		public boolean memberInsert(StudentVO vo) {
			boolean result = false;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?,?,?,?)");
				preparedStatement.setString(1, vo.getId());
				preparedStatement.setString(2, vo.getPass());
				preparedStatement.setString(3, vo.getName());
				preparedStatement.setString(4, vo.getPhone1());
				preparedStatement.setString(5, vo.getPhone2());
				preparedStatement.setString(6, vo.getPhone3());
				preparedStatement.setString(7, vo.getEmail());
				preparedStatement.setString(8, vo.getZipcode());
				preparedStatement.setString(9, vo.getAddress1());
				preparedStatement.setString(10, vo.getAddress2());
				if(preparedStatement.executeUpdate() > 0) result = true;
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null)
					try { preparedStatement.close();
					} catch(SQLException e2) {}
				if(connection != null) 
					try {connection.close();
					} catch(SQLException e3) {}
			} return result;
		}
		
		public boolean updateMember(StudentVO vo) {
			boolean result = false;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("UPDATE student SET pass=?, phone1=?, phone2=?, phone3=?, "
						+ "email=?, zipcode=?, address1=?, address2=? WHERE id=?");
				preparedStatement.setString(1, vo.getPass());
				preparedStatement.setString(2, vo.getPhone1());
				preparedStatement.setString(3, vo.getPhone2());
				preparedStatement.setString(4, vo.getPhone3());
				preparedStatement.setString(5, vo.getEmail());
				preparedStatement.setString(6, vo.getZipcode());
				preparedStatement.setString(7, vo.getAddress1());
				preparedStatement.setString(8, vo.getAddress2());
				preparedStatement.setString(9, vo.getId());
				if(preparedStatement.executeUpdate() > 0) result = true;
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null)
					try { preparedStatement.close();
					} catch(SQLException e2) {}
				if(connection != null) 
					try {connection.close();
					} catch(SQLException e3) {}
			} return result;
		}
		
		public int loginCheck(String id, String pass) {
			int result = 2;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT pass FROM student WHERE id = ?");
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					if(resultSet.getString("pass").equals(pass)) result = 1;
					else result = 0;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null)
					try { preparedStatement.close();
					} catch(SQLException e2) {}
				if(connection != null) 
					try {connection.close();
					} catch(SQLException e3) {}
			} return result;
		}
		
		public int deleteMember(String id, String pass) {
			int result = 0;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT pass FROM student WHERE id = ?");
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					if(resultSet.getString("pass").equals(pass)) {
						preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id=?");
						preparedStatement.setString(1, id);
						preparedStatement.executeUpdate();
						result = 1;
					} else result = 0;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null)
					try { preparedStatement.close();
					} catch(SQLException e2) {}
				if(connection != null) 
					try {connection.close();
					} catch(SQLException e3) {}
			} return result;
		}
		
		public StudentVO getMember(String id) {
			StudentVO vo = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = getConnection();
				preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id=?");
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					vo = new StudentVO();
					vo.setId(resultSet.getString("id"));
					vo.setPass(resultSet.getString("pass"));
					vo.setName(resultSet.getString("name"));
					vo.setPhone1(resultSet.getString("phone1"));
					vo.setPhone2(resultSet.getString("phone2"));
					vo.setPhone3(resultSet.getString("phone3"));
					vo.setEmail(resultSet.getString("email"));
					vo.setZipcode(resultSet.getString("zipcode"));
					vo.setAddress1(resultSet.getString("address1"));
					vo.setAddress2(resultSet.getString("address2"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null)
					try { preparedStatement.close();
					} catch(SQLException e2) {}
				if(connection != null) 
					try {connection.close();
					} catch(SQLException e3) {}
			} return vo;
		}
}
