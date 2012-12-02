package tudresden.mobile.reserveme;

import java.util.ArrayList;
import java.util.List;

import tudresden.mobile.reserveme.MainActivity.GetCitiesTask;
import tudresden.mobile.reserveme.backend.Restaurant;
import tudresden.mobile.reserveme.backend.RestaurantManager;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantListActivity extends Activity {

	List<Restaurant> restaurantsList = new ArrayList<Restaurant>();
	String city;
	
	ListView lvRestaurants;
	
	
	/* dimakuv: 
	 *   Background Task for fetching list of restaurants from our backend server into Spinner
	 */
	public class GetRestaurantsTask extends AsyncTask<String, Void, RestaurantArrayAdapter> {
		
		@Override
		protected RestaurantArrayAdapter doInBackground(String... cities) {
			RestaurantArrayAdapter aa = null;

			restaurantsList = RestaurantManager.getRestaurants(cities[0]);		

			if (restaurantsList != null) {
				aa = new RestaurantArrayAdapter(
						getApplicationContext(), R.layout.listitem_restaurants, restaurantsList);
			}	        
	        
			return aa;
		}
		
		@Override
		protected void onPostExecute(RestaurantArrayAdapter aa) {			
			// NOTE: this method is executed in main, UI thread
			if (aa != null) {
				lvRestaurants.setAdapter(aa);			
			} else {
        	   	Toast.makeText(RestaurantListActivity.this, "WARNING: Didn't retrieve restaurants!", Toast.LENGTH_SHORT).show();		        	   
           }
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Grab the city parameter from MainActivity
		Bundle b = getIntent().getExtras();
		this.city = b.getString("city");
		
		// Set the View layer
		setContentView(R.layout.list_restaurants);
		setTitle(this.city);
		
		// Get reference to ListView holder
		lvRestaurants = (ListView) this.findViewById(R.id.listview_restaurants);
		
        new GetRestaurantsTask().execute(city);

		// Create a customized ArrayAdapter
		// empty in the beginning
		RestaurantArrayAdapter adapter = new RestaurantArrayAdapter(
				getApplicationContext(), R.layout.listitem_restaurants, restaurantsList);
				
		// Set the ListView adapter
		lvRestaurants.setAdapter(adapter);
	}

}
