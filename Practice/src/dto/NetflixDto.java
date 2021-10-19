package dto;

import java.sql.Date;

public class NetflixDto {
	private int id;
	private String title;
	private String description;
	private Date rel_date;
	
	public NetflixDto() {}
	
	public NetflixDto(int id, String title, String description, Date rel_date) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.rel_date = rel_date;
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
