package dao;

import java.sql.Connection;
import java.sql.Date;
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

	public int insert(MessageDTO dto) throws Exception{


		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "insert into message values(seq.nextval,'"+writer+"','"+message+"')"; int
		 * result = stat.executeUpdate(sql);
		 */
		String sql = "insert into message values(seq.nextval, ? , ?, ?)";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			pstat.setDate(3, dto.getVisitDate());

			int result = pstat.executeUpdate();

			return result;
		}
	}

	public int delete(int seq) throws Exception{
		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "delete from message where seq = " + seq; int result =
		 * stat.executeUpdate(sql);
		 */
		String sql = "delete from message where seq = ?";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){

			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();

			con.close();

			return result;
		}
	}

	public int update(MessageDTO dto) throws Exception{

		/*
		 * Statement stat = con.createStatement(); String sql =
		 * "update message set writer = '"+writer+"', " +
		 * "message = '"+message+"' where seq = "+seq+""; int result =
		 * stat.executeUpdate(sql);
		 */
		Connection con = getConnection();
		String sql = "update message set writer = ?, message = ?, visit_date = ? where seq = ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, dto.getWriter());
		pstat.setString(2, dto.getMessage());
		pstat.setDate(3, dto.getVisitDate());
		pstat.setInt(4, dto.getSeq());

		int result = pstat.executeUpdate();
		return result;
	}

	public ArrayList<MessageDTO> select() throws Exception{

		//Statement stat = con.createStatement();

		String sql = "select * from message order by 1";
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){

			ArrayList<MessageDTO> mes = new ArrayList<>();
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String writer = rs.getString("writer");
				String message = rs.getString("message");
				Date visitDate = rs.getDate("visit_Date");
				MessageDTO m = new MessageDTO(seq,writer,message,visitDate);
				mes.add(m);
			}

			return mes;
		}
	}

	public MessageDTO selectId(int id) throws Exception{

		//Statement stat = con.createStatement();

		String sql = "select * from message where seq = ?";
		try(Connection con = getConnection();				
				PreparedStatement pstat = con.prepareStatement(sql);
				){

			pstat.setInt(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				MessageDTO m = null;

				if(rs.next()) {
					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String message = rs.getString("message");
					Date visitDate = rs.getDate("visit_Date");
					m = new MessageDTO(seq,writer,message,visitDate);	
				}
				return m;	
			}
		}
	}

	public ArrayList<MessageDTO> selectWriter(String send) throws Exception{

		/* Statement stat = con.createStatement(); */
		String sql = "select * from message where writer = ? order by 1";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, send);
			try(ResultSet rs = pstat.executeQuery();){
				ArrayList<MessageDTO> mes = new ArrayList<>();

				while(rs.next()) {
					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String message = rs.getString("message");
					Date visitDate = rs.getDate("visit_Date");
					
					MessageDTO m = new MessageDTO(seq,writer,message,visitDate);
					mes.add(m);
				}
				return mes;
			}
		}
	}
}
