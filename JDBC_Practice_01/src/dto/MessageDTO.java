package dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MessageDTO {
	private int seq;
	private String writer;
	private String message;
	private Date visitDate;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public MessageDTO(int seq, String writer, String message, Date input) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.message = message;
		this.visitDate = input;
	}

	public MessageDTO() {}
	
	public String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy≥‚MMø˘dd¿œ");
		return sdf.format(visitDate);
	}
	
	
	
}
