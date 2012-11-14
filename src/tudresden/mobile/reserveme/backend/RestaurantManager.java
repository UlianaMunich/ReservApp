package tudresden.mobile.reserveme.backend;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.engine.Engine;
import org.restlet.ext.httpclient.HttpClientHelper;
import org.restlet.resource.ClientResource;

public class RestaurantManager {
	
	// NOTE: don't use "localhost" or "127.0.0.1", but your real IP 
	private static final String host = "http://192.168.0.100:8182/restaurants/";
	
	private static final String get_cities_path = host + "cities"; 
	
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
        ClientResource cr = new ClientResource(get_cities_path);
        String[] cities = null;
        
        try {
            // GET list of restaurants in JSON notation from the remote server
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
	
	// just a mock for now
	public static List<Restaurant> getRestaurants(String city) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();

		// TODO in the end it will actually be sorted by distance
		restaurants.add(new Restaurant(3, "BurgerKing", "Dresden", 51.041692, 13.734798));
		restaurants.add(new Restaurant(2, "McDonalds Altstadt", "Dresden", 51.050378, 13.737202));
		restaurants.add(new Restaurant(1, "Espitas", "Dresden", 51.050409, 13.737262));
		
		return restaurants;
	}

}
