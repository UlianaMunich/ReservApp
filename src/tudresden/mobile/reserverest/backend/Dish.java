package tudresden.mobile.reserverest.backend;

public class Dish {
	private int id;
	private int restaurant_id;
	private String name_en;
	private String name_de;
	private String desc_en;
	private String desc_de;
	private int number;
	private String type;
	private double price;
	private int curr_quantity;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_de() {
		return name_de;
	}

	public void setName_de(String name_de) {
		this.name_de = name_de;
	}

	public String getDesc_en() {
		return desc_en;
	}

	public void setDesc_en(String desc_en) {
		this.desc_en = desc_en;
	}

	public String getDesc_de() {
		return desc_de;
	}

	public void setDesc_de(String desc_de) {
		this.desc_de = desc_de;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCurr_quantity() {
		return curr_quantity;
	}

	public void setCurr_quantity(int curr_quantity) {
		this.curr_quantity = curr_quantity;
	}
	
	public Dish(int id, int restaurant_id, String name_en, String name_de,
			String desc_en, String desc_de, int number, String type,
			double price, int curr_quantity) {
		super();
		this.id = id;
		this.restaurant_id = restaurant_id;
		this.name_en = name_en;
		this.name_de = name_de;
		this.desc_en = desc_en;
		this.desc_de = desc_de;
		this.number = number;
		this.type = type;
		this.price = price;
		this.curr_quantity = curr_quantity;
	}
	
	@Override
	public String toString() {
		return id + ";" + restaurant_id
				+ ";" + name_en + ";" + name_de
				+ ";" + desc_en + ";" + desc_de + ";"
				+ number + ";" + type + ";" + price
				+ ";" + curr_quantity;
	}
	
}
