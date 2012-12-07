package tudresden.mobile.reserverest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.MyLocation;
import tudresden.mobile.reserverest.backend.Restaurant;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantArrayAdapter extends ArrayAdapter<Restaurant> {
	private static final String CUISINE_DIR = "images/cuisine/";
	private Context context;
	private ImageView restaurantIcon;
	private TextView restaurantName;
	private TextView restaurantDistance;
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();

	public RestaurantArrayAdapter(Context context, int textViewResourceId,
			List<Restaurant> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.restaurants = objects;
	}

	public int getCount() {
		return this.restaurants.size();
	}

	public Restaurant getItem(int index) {
		return this.restaurants.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listitem_restaurants, parent, false);
		}

		// Get item
		Restaurant restaurant = getItem(position);
		
		restaurantIcon = (ImageView) row.findViewById(R.id.restaurant_icon);
		restaurantName = (TextView) row.findViewById(R.id.restaurant_name);
		restaurantDistance = (TextView) row.findViewById(R.id.restaurant_distance);

		//Set country name
		restaurantName.setText(restaurant.getName());
		
		// Set country icon using File path
		try {
			String imgFilePath = CUISINE_DIR + restaurant.getCuisine() + ".png";
			Bitmap bitmap = BitmapFactory.decodeStream(this.context.getResources().getAssets().open(imgFilePath));
			restaurantIcon.setImageBitmap(bitmap);
		} catch (IOException e) {
			String imgFilePath = CUISINE_DIR + "default.png";
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(this.context.getResources().getAssets().open(imgFilePath));
				restaurantIcon.setImageBitmap(bitmap);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// Set restaurant distance from current location
		Location myLocation = MyLocation.getInstance(null).getLocation();
		if (myLocation != null) {
	        Location restaurantLocation = new Location("foobar");
	        restaurantLocation.setLatitude(restaurant.getLatitude());
	        restaurantLocation.setLongitude(restaurant.getLongitude());
	        
	        float distance = myLocation.distanceTo(restaurantLocation);
			restaurantDistance.setText(String.format("%.2f", distance) + " m");
		} else {
			restaurantDistance.setText("??? m");			
		}
		
		return row;
	}
}
