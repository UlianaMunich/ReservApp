package tudresden.mobile.reserverest.backend;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import tudresden.mobile.reserverest.settings.ReserveRestSettings;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.engine.Engine;
import org.restlet.ext.httpclient.HttpClientHelper;
import org.restlet.resource.ClientResource;

public class RestaurantManager {
	
	// NOTE: don't use "localhost" or "127.0.0.1", but your real IP 
//	private static final String host = "http://192.168.0.100:8182/reserveme/";
	private static final String host = "http://141.76.41.223:8182/reserveme/";
	
	private static final String get_cities_path = host + "cities"; 
	private static final String get_restaurants_path = host + "restaurants"; 
	
	public static boolean initConnection() {
        // initialize RESTlet client to use Apache HTTP Client
        Engine.getInstance().getRegisteredClients().clear();
        Engine.getInstance().getRegisteredClients().add(new HttpClientHelper(null)); 

/*        
        // test if our server is up and running        
        try {
        	// NOTE: don't use "localhost" or "127.0.0.1", but your real IP 
            URL myUrl = new URL(host);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(1000000);
            connection.connect();
        } catch (IOException e) {
        	return false;
        }
*/        
        return true;
	}
	
	public static String[] getCities() {
		if (!ReserveRestSettings.USEINTERNET) {
			String[] cities = {"Dresden", "Leipzig", "Hamburg", "Berlin"};
		    return cities;
		}

        ClientResource cr = new ClientResource(get_cities_path);
        String[] cities = null;
        
        try {
            // GET list of cities in JSON notation from the remote server
     	   String json_list = cr.get(MediaType.APPLICATION_JSON).getText();
     	   JSONObject jsonObj = new JSONObject(json_list);
     	   JSONArray json_cities = jsonObj.getJSONArray("cities");
     	   
     	   cities = new String[json_cities.length()];
     	   for (int i = 0; i < json_cities.length(); i++) {
     		   cities[i] = json_cities.getString(i);
     	   }
        } catch (Exception e) {
 			// nothing to do
        }
		
        return cities;
	}
	
	public static List<Restaurant> getRestaurants(String city) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();

		if (!ReserveRestSettings.USEINTERNET) {
			restaurants.add(new Restaurant(4, "Vapiano", "Dresden", 51.073345, 13.736809, "italy", "Pasta | Pizza | Bar", 8, "St. Petersburger Straße 26"));
			restaurants.add(new Restaurant(3, "BurgerKing", "Dresden", 51.041692, 13.734798, "usa", "Burgers, sandwitches, naggets, french fries and much, much more!", 6, "Wiener Platz 4"));
			restaurants.add(new Restaurant(2, "McDonalds Altstadt", "Dresden", 51.050378, 13.737202, "usa", "I'm lovin' it!", 5, "Hansastraße 56"));
			restaurants.add(new Restaurant(1, "Espitas", "Dresden", 51.050409, 13.737262, "mexico", "The best of Mexico!", 9, "Louisenstraße 39"));
		    return restaurants;
		}

        ClientResource cr = new ClientResource(get_restaurants_path + "/" + city);   
        try {
            // GET list of restaurants in JSON notation from the remote server
     	   String json_list = cr.get(MediaType.APPLICATION_JSON).getText();
     	   JSONObject jsonObj = new JSONObject(json_list);
     	   JSONArray json_restaurants = jsonObj.getJSONArray("restaurants");
     	   
     	   for (int i = 0; i < json_restaurants.length(); i++) {
     		   String[] rest_info = json_restaurants.getString(i).split(";");
     		   Restaurant restaurant = new Restaurant(i,
     				   					rest_info[0], 
     				   					rest_info[1], 
     				   					Double.valueOf(rest_info[2]),
     				   					Double.valueOf(rest_info[3]),
     				   					rest_info[4],
     				   					rest_info[5],
     				   					Integer.valueOf(rest_info[6]),
     				   					rest_info[7]);
     		  restaurants.add(restaurant);
     	   }
        } catch (Exception e) {
        	e.toString();
 			// nothing to do
        }
		
		return restaurants;
	}

	public static List<Dish> getDishes(String type) {
		List<Dish> dishes = new ArrayList<Dish>();

		if (!ReserveRestSettings.USEINTERNET) {
			if (type == "main") {
				dishes.add(new Dish(1, 0, 
						"Cheesy nachos mit Espitas-Chili Con Carne-dip", 
						"Cheesy nachos mit Espitas-Chili Con Carne-dip",
						"Heißes Chili Con Carne zum dippen",
						"Heißes Chili Con Carne zum dippen",
						1, "main", 3.9, 999));
				dishes.add(new Dish(2, 0, 
						"Cheesy nachos mit Spinat-Frishkäse-dip", 
						"Cheesy nachos mit Spinat-Frishkäse-dip",
						"Warmer Dip aus Spinat und Frischkäse",
						"Warmer Dip aus Spinat und Frischkäse",
						2, "main", 3.9, 999));
				dishes.add(new Dish(3, 0, 
						"Cheesy nachos mit Käse-Jalapeño-dip", 
						"Cheesy nachos mit Käse-Jalapeño-dip",
						"Warmer Dip aus Käse und feurigen Jalapeños",
						"Warmer Dip aus Käse und feurigen Jalapeños",
						3, "main", 3.9, 999));
				dishes.add(new Dish(4, 0, 
						"Cheesy nachos mit Espitas-Red-Hot-Salsa", 
						"Cheesy nachos mit Espitas-Red-Hot-Salsa",
						"Warmer Dip aus feuriger Salsa-Sauce",
						"Warmer Dip aus feuriger Salsa-Sauce",
						4, "main", 3.9, 999));
				dishes.add(new Dish(5, 0, 
						"Fresh Guacamole + Homemade Nachos", 
						"Fresh Guacamole + Homemade Nachos",
						"Homemade Nachos, geschnittene Avocado, Korianderblätter, gehackter Knoblauch, Zwiebel, Limettenecken, halbierte Cherry-Tomaten, mit Salz und Pfeffer",
						"Homemade Nachos, geschnittene Avocado, Korianderblätter, gehackter Knoblauch, Zwiebel, Limettenecken, halbierte Cherry-Tomaten, mit Salz und Pfeffer",
						5, "main", 9.9, 999));
			} else if (type == "soup") {
				dishes.add(new Dish(6, 0, 
						"Süsskartofellsuppe mit Chorizo", 
						"Süsskartofellsuppe mit Chorizo",
						"Cremige Süsskartofellsuppe mit spanischer Salami & Brot",
						"Cremige Süsskartofellsuppe mit spanischer Salami & Brot",
						6, "main", 3.9, 999));
				dishes.add(new Dish(7, 0, 
						"Espitas Chili Con Carne", 
						"Espitas Chili Con Carne",
						"Schweine- und Rindfleisch gepaart mit Kidneybohnen, Mais, Tomaten, Zwiebeln, Paprika, Jalapeños, Sour Cream und zwei kleinen Weizentortillas",
						"Schweine- und Rindfleisch gepaart mit Kidneybohnen, Mais, Tomaten, Zwiebeln, Paprika, Jalapeños, Sour Cream und zwei kleinen Weizentortillas",
						7, "main", 3.9, 999));
				
			} else if (type == "salad") {
				dishes.add(new Dish(8, 0, 
						"Caesar Salad", 
						"Caesar Salad",
						"Romanasalat mit Parmesandressing, knusprigen Croutons und gehobeltem Parmesan",
						"Romanasalat mit Parmesandressing, knusprigen Croutons und gehobeltem Parmesan",
						8, "salad", 6.9, 999));
				dishes.add(new Dish(9, 0, 
						"Caesar Salad", 
						"Caesar Salad",
						"Romanasalat mit Parmesandressing, knusprigen Croutons und gehobeltem Parmesan",
						"Romanasalat mit Parmesandressing, knusprigen Croutons und gehobeltem Parmesan",
						9, "salad", 7.9, 999));
				dishes.add(new Dish(8, 0, 
						"Caesar Salad", 
						"Caesar Salad",
						"Romanasalat mit Parmesandressing, knusprigen Croutons und gehobeltem Parmesan",
						"Romanasalat mit Parmesandressing, knusprigen Croutons und gehobeltem Parmesan",
						8, "salad", 6.9, 999));
				
			}

		    return dishes;
		}

		//TODO: get dishes from backend
		
		return dishes;
	}
	
}
