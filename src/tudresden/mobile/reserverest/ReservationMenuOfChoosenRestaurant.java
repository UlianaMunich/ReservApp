package tudresden.mobile.reserverest;

import java.io.IOException;

import tudresden.mobile.reserverest.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReservationMenuOfChoosenRestaurant extends Activity {
	static final String RATINGS_DIR = "images/ratings/";
	
	private int rest_id = 0;
	private String rest_name;
	
	private TextView tvDesc;
	private TextView tvAddress;
	private ImageView ivRating;
	
	private Button ReservationButton;
	private Button SeeMenu;
		
 	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_menu_of_choosen_restaurant);
        
		Bundle b = getIntent().getExtras();
		rest_name = b.getString("name"); 
     	setTitle(rest_name);
     	
     	tvDesc = (TextView) findViewById(R.id.rest_desc);
     	tvAddress = (TextView) findViewById(R.id.rest_address);
     	ivRating = (ImageView) findViewById(R.id.rest_rating);
     	
		// Grab the parameters from RestaurantListActivity
		rest_id = b.getInt("rest_id");
		tvDesc.setText(b.getString("desc"));
		tvAddress.setText(b.getString("address"));
		
		// String imgFilePath = RATINGS_DIR + b.getInt("rating") + ".png";
		String imgFilePath = RATINGS_DIR + "mock.png";
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(this.getResources().getAssets().open(imgFilePath));
			ivRating.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// set buttons
        ReservationButton = (Button)findViewById(R.id.button_to_reservation);
        ReservationButton.setOnClickListener(new View.OnClickListener() {						
     	   	@Override
 			public void onClick(View v) {
     	   		// open next activity for reservation a table
 				Intent intent = new Intent(ReservationMenuOfChoosenRestaurant.this, Reservation_Form.class);
 				startActivity(intent);
     	   		System.out.println("Click reservation");
 			}
 		});
        SeeMenu = (Button) findViewById(R.id.button_to_see_menu);
        SeeMenu.setOnClickListener(new View.OnClickListener() {						
     	   	@Override
 			public void onClick(View v) {
     	   		// open next activity with the list of all menu
 				Intent intent = new Intent(ReservationMenuOfChoosenRestaurant.this, MenuList.class);
				Bundle b = new Bundle();
				b.putInt("rest_id", rest_id);
				b.putString("rest_name", rest_name);
				intent.putExtras(b);
 				
 				startActivity(intent);
     	   		System.out.println("Click see menu");
 			}
 		});
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation_menu_of_choosen_restaurant, menu);
        return true;
    }
}
