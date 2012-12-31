package tudresden.mobile.reserverest.backend;

import java.util.Date;
import java.util.List;

public class Reservation {
	private int clientId;
	private int restaurantId;
	private String clientFname;
	private String clientLname;
	private String clientEmail;
	private String clientPhone;
	private Date datetime;
	private int tableNumber;
	
	private List<Dish> dishes = null;
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getClientFname() {
		return clientFname;
	}
	public void setClientFname(String clientFname) {
		this.clientFname = clientFname;
	}
	public String getClientLname() {
		return clientLname;
	}
	public void setClientLname(String clientLname) {
		this.clientLname = clientLname;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public List<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public Reservation(int clientId, int restaurantId, String clientFname,
			String clientLname, String clientEmail, String clientPhone,
			Date datetime, int tableNumber) {
		this.clientId = clientId;
		this.restaurantId = restaurantId;
		this.clientFname = clientFname;
		this.clientLname = clientLname;
		this.clientEmail = clientEmail;
		this.clientPhone = clientPhone;
		this.datetime = datetime;
		this.tableNumber = tableNumber;
	}
	
}
