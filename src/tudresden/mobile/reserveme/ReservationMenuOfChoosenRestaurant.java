package tudresden.mobile.reserveme;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ReservationMenuOfChoosenRestaurant extends Activity {
	private Button ReservationButton;
	private Button SeeMenu;
		
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
