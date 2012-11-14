package tudresden.mobile.reserveme;

import java.util.ArrayList;
import java.util.List;

import tudresden.mobile.reserveme.backend.Restaurant;
import tudresden.mobile.reserveme.backend.RestaurantManager;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class RestaurantListActivity extends Activity {

	List<Restaurant> countryList= new ArrayList<Restaurant>();
	String city;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Grab the city parameter from MainActivity
		Bundle b = getIntent().getExtras();
		this.city = b.getString("city");
		
		// Set the View layer
		setContentView(R.layout.list_restaurants);
		setTitle(this.city);

		List<Restaurant> countryList = RestaurantManager.getRestaurants(this.city);		
		
		// Create a customized ArrayAdapter
		RestaurantArrayAdapter adapter = new RestaurantArrayAdapter(
				getApplicationContext(), R.layout.listitem_restaurants, countryList);
		
		// Get reference to ListView holder
		ListView lv = (ListView) this.findViewById(R.id.listview_restaurants);
		
		// Set the ListView adapter
		lv.setAdapter(adapter);
	}

}
