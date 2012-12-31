package tudresden.mobile.reserverest;

import java.text.SimpleDateFormat;
import java.util.List;

import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.Dish;
import tudresden.mobile.reserverest.backend.Reservation;
import tudresden.mobile.reserverest.backend.Restaurant;
import tudresden.mobile.reserverest.backend.RestaurantManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class EndActivity extends Activity {

    final static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm"); 

    TextView tvReservationSummary;
    ListView lvReserved;
	Button btnToMain;
			
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_layout);
        
        Reservation reservation = RestaurantManager.getReservation();
        List<Dish> dishes = reservation.getDishes();
        Restaurant restaurant = RestaurantManager.getRestaurant();
        
        String[] dishNames = null;
        String summary = "Summary of your reservation:\n";
        summary += "  - Restaurant: " + restaurant.getName() + "\n";
        summary += "  - Address: " + restaurant.getAddress() + "\n";
        summary += "  - Client info:\n";
        summary += "     - name: " + reservation.getClientFname() + " " + reservation.getClientLname() + "\n";
        summary += "     - email: " + reservation.getClientEmail() + "\n";
        summary += "     - phone: " + reservation.getClientPhone() + "\n";        
        summary += "  - Date and time: " + formatter.format(reservation.getDatetime()) + "\n";
        
        if (dishes != null && dishes.size() > 0) {
        	summary += "\n  - Dishes:";
        
	        dishNames = new String[dishes.size()];
			for (int i = 0; i < dishes.size(); i++) {
				dishNames[i] = dishes.get(i).getName_en();
			}
        }
        
        tvReservationSummary  = (TextView) findViewById(R.id.tvReservationSummary1);
        tvReservationSummary.setText(summary);
        
        lvReserved = (ListView) findViewById(R.id.lvReserved1);      
        if (dishes != null && dishes.size() > 0) {
	        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, 
	        		R.layout.listitem_text, R.id.tvItem,  
	     		    dishNames);
	        lvReserved.setAdapter(aa);
        }

        btnToMain = (Button) findViewById(R.id.btnToMain1);        
        btnToMain.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		// go to Main activity
				Intent intent = new Intent(EndActivity.this, MainActivity.class);
	        	startActivity(intent);
			}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation__form, menu);
        return true;
    }
}
