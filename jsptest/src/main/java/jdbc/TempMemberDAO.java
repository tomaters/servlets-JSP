package jdbc; 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TempMemberDAO {

	public TempMemberDAO() {}

	public Vector<TempMemberVO> getMemberList() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ConnectionPool connectionPool = null;
		Vector<TempMemberVO> vectorList = new Vector<TempMemberVO>();
		try {
			connectionPool = ConnectionPool.getInstance();
			connection = connectionPool.getConnection();
			String strQuery = "select * from tempmember";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(strQuery);
			while (resultSet.next()) {
				TempMemberVO tempMemberVO = new TempMemberVO();
				tempMemberVO.setId(resultSet.getString("id"));
				tempMemberVO.setPasswd(resultSet.getString("passwd"));
				tempMemberVO.setName(resultSet.getString("name"));
				tempMemberVO.setMem_num1(resultSet.getString("mem_num1"));
				tempMemberVO.setMem_num2(resultSet.getString("mem_num2"));
				tempMemberVO.setEmail(resultSet.getString("e_mail"));
				tempMemberVO.setPhone(resultSet.getString("phone"));
				tempMemberVO.setZipcode(resultSet.getString("zipcode"));
				tempMemberVO.setAddress(resultSet.getString("address"));
				tempMemberVO.setJob(resultSet.getString("job"));
				vectorList.add(tempMemberVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
		return vectorList;
	}
}