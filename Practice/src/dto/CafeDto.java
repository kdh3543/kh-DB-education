package dto;

import java.sql.Date;

public class CafeDto {
	private int id;
	private String menu;
	private int price;
	private Date reg_date;
	
	public CafeDto() {}

	public CafeDto(int id, String menu, int price, Date reg_date) {
		super();
		this.id = id;
		this.menu = menu;
		this.price = price;
		this.reg_date = reg_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}
