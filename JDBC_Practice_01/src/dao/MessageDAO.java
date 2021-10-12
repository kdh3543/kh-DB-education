package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MessageDTO;

public class MessageDAO {
	
	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "practice";
		String password = "practice";
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	
	public int insert(String writer, String message) throws Exception{
		Connection con = getConnection();
		
		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "insert into message values(seq.nextval,'"+writer+"','"+message+"')"; int
		 * result = stat.executeUpdate(sql);
		 */
		
		String sql = "insert into message values(seq.nextval, ? , ?)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, writer);
		pstat.setString(2, message);
		
		int result = pstat.executeUpdate();
		con.close();
		return result;
	}
	
	public int delete(int seq) throws Exception{
		Connection con = getConnection();
		
		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "delete from message where seq = " + seq; int result =
		 * stat.executeUpdate(sql);
		 */
		
		String sql = "delete from message where seq = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, seq);
		int result = pstat.executeUpdate();
		
		con.close();
		
		return result;
	}
	
	public int update(String writer, String message, int seq) throws Exception{
		Connection con = getConnection();
		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "update message set writer = '"+writer+"', " +
		 * "message = '"+message+"' where seq = "+seq+""; int result =
		 * stat.executeUpdate(sql);
		 */
		
		String sql = "update message set writer = ?, message = ? where seq = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, writer);
		pstat.setString(2, message);
		pstat.setInt(3, seq);
		
		int result = pstat.executeUpdate();
		
		con.close();
		
		return result;
		
	}
	
	public ArrayList<MessageDTO> select() throws Exception{
		Connection con = getConnection();
		//Statement stat = con.createStatement();
		String sql = "select * from message order by 1";
		PreparedStatement pstat = con.prepareStatement(sql);
		
		ResultSet rs = pstat.executeQuery();
		ArrayList<MessageDTO> mes = new ArrayList<>();
		
		while(rs.next()) {
			int seq = rs.getInt("seq");
			String writer = rs.getString("writer");
			String message = rs.getString("message");
			
			MessageDTO m = new MessageDTO(seq,writer,message);
			mes.add(m);
		}
		
		con.close();
		return mes;
	}
	
	public MessageDTO selectId(int id) throws Exception{
		Connection con = getConnection();
		//Statement stat = con.createStatement();
		String sql = "select * from message where seq = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, id);
		
		ResultSet rs = pstat.executeQuery();
	
		MessageDTO m = null;
		
		if(rs.next()) {
			int seq = rs.getInt("seq");
			String writer = rs.getString("writer");
			String message = rs.getString("message");
			m = new MessageDTO(seq,writer,message);	
		}
		con.close();
		return m;	
	}
	
	public ArrayList<MessageDTO> selectWriter(String send) throws Exception{
		Connection con = getConnection();
		/* Statement stat = con.createStatement(); */
		String sql = "select * from message where writer = ? order by 1";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, send);
		
		ResultSet rs = pstat.executeQuery();
		ArrayList<MessageDTO> mes = new ArrayList<>();
		
		while(rs.next()) {
			int seq = rs.getInt("seq");
			String writer = rs.getString("writer");
			String message = rs.getString("message");
			
			MessageDTO m = new MessageDTO(seq,writer,message);
			mes.add(m);
		}
		
		con.close();
		return mes;
	}
}
