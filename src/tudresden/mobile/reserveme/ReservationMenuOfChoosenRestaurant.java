package tudresden.mobile.reserveme;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ReservationMenuOfChoosenRestaurant extends Activity {
	private Button ReservationButton;
		
 	 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_menu_of_choosen_restaurant);
        
     // Set the View layer
     		setTitle("ReserveRest");

        ReservationButton = (Button)findViewById(R.id.button_to_reservation);
        ReservationButton.setOnClickListener(new View.OnClickListener() {						
     	   	@Override
 			public void onClick(View v) {
     	   		// open list of restaurants of this city
 				Intent intent = new Intent(ReservationMenuOfChoosenRestaurant.this, Reservation_Form.class);
 				startActivity(intent);
     	   		System.out.println("Click reservation");
 			}
 		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation_menu_of_choosen_restaurant, menu);
        return true;
    }
    
    
}
