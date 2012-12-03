package tudresden.mobile.reserveme;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Reservation_Form extends Activity {

			
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__form);
        
     // Set the View layer
     			//setContentView(R.layout.list_restaurants);
     			//setTitle("ReserveRest");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation__form, menu);
        return true;
    }
}
