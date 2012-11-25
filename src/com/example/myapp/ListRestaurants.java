package com.example.myapp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ListRestaurants extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the View layer
		setContentView(R.layout.list_view_of_restaurants);
		setTitle("Nearest Restaurants");

		// Create Parser 
		
		//RestaurantsParser countryParser = new RestaurantsParser();
		//InputStream inputStream = getResources().openRawResource(
			//	R.raw.countries);
		
		// Parse the input stream
		//countryParser.parse(inputStream);

		// Get Countries
		//List<Restaurants> countryList = countryParser.getList();
		
		
		// Create a customized ArrayAdapter
		//RestaurantsArrayAdapter adapter = new RestaurantsArrayAdapter(
				//getApplicationContext(), R.layout.list_view_of_restaurants, countryList);
		
		// Get reference to ListView holder
		//ListView lv = (ListView) this.findViewById(R.id.RestaurantID);
		
		// Set the ListView adapter
		//lv.setAdapter(adapter);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
