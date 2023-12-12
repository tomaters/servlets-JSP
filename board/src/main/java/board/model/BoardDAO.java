package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	// singleton constructor
	private static BoardDAO instance = null;
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized(BoardDAO.class) {
				instance = new BoardDAO();
			}
		} return instance;
	}
	
	/* menu DAO methods */
	// method to return total number of words
	public int getArticleCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int total = 0;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement("SELECT count(*) FROM board");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) total = resultSet.getInt(1); 
		} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null) 
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null) 
					try { preparedStatement.close();
					} catch(SQLException e) {}
				if(connection != null) 
					try { connection.close();
					} catch(SQLException e) {}
			}
		return total;
	}
	// method to retrieve data from articles and return as List object
	public List<BoardVO> getArticles(int start, int end) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<BoardVO> articleList = null;
		try {
			connection = ConnectionUtil.getConnection();
			// display all information of selected articles
			preparedStatement = connection.prepareStatement("SELECT * FROM (SELECT ROWNUM rnum, num, writer, email, subject, pass, readcount, ref, step, depth, content, ip FROM"
					+ "(SELECT * FROM board ORDER BY ref DESC, step ASC WHERE rnum=? and rnum<=?");
			// location of articles to retrieve in range form
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, end);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				// if articles in range exist in DB, create ArrayList object with initial capacity of 5 to store data in
				articleList = new ArrayList<BoardVO>(5);
				do {
					BoardVO article = new BoardVO();
					article.setNum(resultSet.getInt("num"));
					article.setWriter(resultSet.getString("writer"));
					article.setEmail(resultSet.getString("email"));
					article.setSubject(resultSet.getString("subject"));
					article.setPass(resultSet.getString("pass"));
					article.setRegdate(resultSet.getTimestamp("regdate"));
					article.setReadcount(resultSet.getInt("readcount"));
					article.setRef(resultSet.getInt("ref"));
					article.setStep(resultSet.getInt("step"));
					article.setDepth(resultSet.getInt("depth"));
					article.setContent(resultSet.getString("content"));
					article.setIp(resultSet.getString("ip"));
					articleList.add(article);
				} while(resultSet.next());
			}
		} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null) 
					try { resultSet.close();
					} catch(SQLException e) {}
				if(preparedStatement != null) 
					try { preparedStatement.close();
					} catch(SQLException e) {}
				if(connection != null) 
					try { connection.close();
					} catch(SQLException e) {}
			}
		 	return articleList;
	}
	
	public void insertArticle(BoardVO article) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int num = article.getNum();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int number = 0;
		String sql = "";
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement("SELECT MAX(num) FROM board");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				number = resultSet.getInt(1) + 1;
			} else number = 1;
			// if it is a response text
			if(num != 0 ) {
				sql = "UPDATE board SET step=step+1 WHERE ref=? AND step>?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, ref);
				preparedStatement.setInt(2, step);
				preparedStatement.executeUpdate();
				step = step + 1;
				depth = depth + 1;
			} else {
				// if it is a new text
				ref = number;
				step = 0;
				depth = 0;
			} // insert query
			sql = "INSERT INTO board(num, writer, email, subject, pass, regdate, ref, step, depth, content, ip VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, article.getWriter());
			preparedStatement.setString(2, article.getEmail());
			preparedStatement.setString(3, article.getSubject());
			preparedStatement.setString(4, article.getPass());
			preparedStatement.setTimestamp(5, article.getRegdate());
			preparedStatement.setInt(6, ref);
			preparedStatement.setInt(7, step);
			preparedStatement.setInt(8, depth);
			preparedStatement.setString(9, article.getContent());
			preparedStatement.setString(10, article.getIp());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) 
				try { resultSet.close();
				} catch(SQLException e) {}
			if(preparedStatement != null) 
				try { preparedStatement.close();
				} catch(SQLException e) {}
			if(connection != null) 
				try { connection.close();
				} catch(SQLException e) {}	
		}
	}
	
	public BoardVO getArticle(int num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BoardVO article = null;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE board SET readcount=readcount+1 WHERE num = ?");
			preparedStatement.setInt(1, num);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("SELECT * FROM board WHERE num=?");
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				article = new BoardVO();
				article.setNum(resultSet.getInt("num"));
				article.setWriter(resultSet.getString("writer"));
				article.setEmail(resultSet.getString("email"));
				article.setSubject(resultSet.getString("subject"));
				article.setPass(resultSet.getString("pass"));
				article.setRegdate(resultSet.getTimestamp("regdate"));
				article.setReadcount(resultSet.getInt("readcount"));
				article.setRef(resultSet.getInt("ref"));
				article.setStep(resultSet.getInt("step"));
				article.setDepth(resultSet.getInt("depth"));
				article.setContent(resultSet.getString("content"));
				article.setIp(resultSet.getString("ip"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) 
				try { resultSet.close();
				} catch(SQLException e) {}
			if(preparedStatement != null) 
				try { preparedStatement.close();
				} catch(SQLException e) {}
			if(connection != null) 
				try { connection.close();
				} catch(SQLException e) {}	
		} return article;
	}
}
