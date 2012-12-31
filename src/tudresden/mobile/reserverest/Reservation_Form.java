package tudresden.mobile.reserverest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.Reservation;
import tudresden.mobile.reserverest.backend.RestaurantManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reservation_Form extends Activity {

    final static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm"); 
    final static SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.yyyy"); 
    final static SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm"); 

	EditText etEmail;
	EditText etName;
	EditText etPhone;
	EditText etDate;
	EditText etTime;
	
	Button btnShowBasket;
	Button btnPushReserve;
			
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation__form);
        
        etName 	= (EditText) findViewById(R.id.etName);
        etEmail	= (EditText) findViewById(R.id.etEmail);        
        etPhone	= (EditText) findViewById(R.id.etPhone);
        
        etDate 	= (EditText) findViewById(R.id.etDate);   
        etDate.setText( formatterDate.format( new Date() ));
        
        etTime 	= (EditText) findViewById(R.id.etTime);        
        etTime.setText( formatterTime.format( new Date() ));

        btnShowBasket = (Button) findViewById(R.id.btnShowBasket);        
        btnShowBasket.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		// go to Basket activity
				Intent intent = new Intent(Reservation_Form.this, BasketActivity.class);
	        	startActivity(intent);
			}
		});

        btnPushReserve = (Button) findViewById(R.id.btnPushReserve);        
        btnPushReserve.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		String fname = "";
    	   		String lname = "";
    	   		
    	   		String[] names = etName.getText().toString().split(" ");
    	   		if (names[0] != "") {
    	   			fname = names[0];
    	   			if (names.length > 1) 
    	   				lname = names[1]; 
    	   		} else {
	    	    	Toast.makeText(Reservation_Form.this, "Please specify name!", Toast.LENGTH_SHORT).show();
    	   			return;    	   			
    	   		}
    	   		
    	   		Date datetime;
    	   		try {
    	   			datetime = formatter.parse(etDate.getText().toString() + " " + etTime.getText().toString());
    	   		}
    	   		catch (ParseException e) {
	    	    	Toast.makeText(Reservation_Form.this, "Date or time is incorrect!", Toast.LENGTH_SHORT).show();
    	   			return;
    	   		}
    	   		
    	   		// send reservation request to server
    	   		Reservation reservation = new Reservation(
    	   				123, // client id
    	   				RestaurantManager.getRestaurant().getId(), // restaurant_id
    	   				fname, lname, // clientFirstname, clientLastname
    	   				etEmail.getText().toString(),
    	   				etPhone.getText().toString(),
    	   				datetime,
    	   				0 // table number
    	   				);
    	   		RestaurantManager.setReservation(reservation);
    	   		
    	   		if (RestaurantManager.pushReservation()) {
        	   		// go to End activity
    				Intent intent = new Intent(Reservation_Form.this, EndActivity.class);
    	        	startActivity(intent);
    	   		} else {
	    	    	Toast.makeText(Reservation_Form.this, "Error while pushing to server!", Toast.LENGTH_SHORT).show();
    	   		}
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation__form, menu);
        return true;
    }
}
