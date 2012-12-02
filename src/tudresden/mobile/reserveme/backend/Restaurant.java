package tudresden.mobile.reserveme.backend;

public class Restaurant {
	private int id;
	private String name;
	private String city;
	private double longitude;
	private double latitude;
		
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

	public Restaurant(int id, String name, String city, double latitude,
			double longitude) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
	}
/*
	@Override
	public String toString() {
		return name;		
	}
*/
}
