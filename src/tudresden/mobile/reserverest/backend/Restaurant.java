package tudresden.mobile.reserverest.backend;

public class Restaurant {
	private int id;
	private String name;
	private String city;
	private double longitude;
	private double latitude;
	private String cuisine;
	private String desc;
	private int rating;
	private String address;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Restaurant(int id, String name, String city, double latitude,
			double longitude, String cuisine, String desc, int rating, String address) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cuisine = cuisine;
		this.desc = desc;
		this.rating = rating;
		this.address = address;
	}

	@Override
	public String toString() {
		return name + ";" + city + ";" + latitude + ";" + longitude + ";" + cuisine + ";" +
				desc + ";" + rating + ";" + address;
	}
}
