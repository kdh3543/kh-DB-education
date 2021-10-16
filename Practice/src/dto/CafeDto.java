package dto;

public class CafeDto {
	private int id;
	private String menu;
	private int price;
	
	public CafeDto() {}
	public CafeDto(int id, String menu, int price) {
		super();
		this.id = id;
		this.menu = menu;
		this.price = price;
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
	
	
}
