package dto;

import java.sql.Date;

public class MovieDTO {
	private String title;
	private String description;
	private Date rel_date;
	private int id;
	
	public MovieDTO() {}
	
	public MovieDTO(String title, String description, Date rel_date, int id) {
		super();
		this.title = title;
		this.description = description;
		this.rel_date = rel_date;
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRel_date() {
		return rel_date;
	}
	public void setRel_date(Date rel_date) {
		this.rel_date = rel_date;
	}
}
