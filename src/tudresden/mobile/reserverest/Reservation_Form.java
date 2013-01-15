package tudresden.mobile.reserverest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.DeviceManager;
import tudresden.mobile.reserverest.backend.Reservation;
import tudresden.mobile.reserverest.backend.RestaurantManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
	Button btnPayDishes;
	
	void saveReservation() throws Exception {
   		String fname = ""; String lname = "";
   		
   		String[] names = etName.getText().toString().split(" ");
   		if (names[0] != "") {
   			fname = names[0];
   			if (names.length > 1) 
   				lname = names[1]; 
   		} else {
   			throw new Exception("Please specify name!");
   		}
   		
   		Date datetime;
   		try {
   			datetime = formatter.parse(etDate.getText().toString() + " " + etTime.getText().toString());
   		}
   		catch (ParseException e) {
   			throw new Exception("Date and time must be in format `28.12.2012 20:30`!");
   		}
   		
   		// send reservation request to server
   		Reservation reservation = new Reservation(
   				DeviceManager.getClientID(), // client id
   				RestaurantManager.getRestaurant().getId(), // restaurant_id
   				fname, lname, // clientFirstname, clientLastname
   				etEmail.getText().toString(),
   				etPhone.getText().toString(),
   				datetime,
   				0 // table number
   				);
   		RestaurantManager.setReservation(reservation);		
	}
			
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
        
        Reservation reservation = RestaurantManager.getReservation(); 
        if (reservation != null) {
        	etName.setText( reservation.getClientFname() + " " + reservation.getClientLname() );
        	etEmail.setText( reservation.getClientEmail() );
        	etPhone.setText( reservation.getClientPhone() );
        	etDate.setText(formatterDate.format(reservation.getDatetime()) );
        	etTime.setText(formatterTime.format(reservation.getDatetime()) );
        }

        btnShowBasket = (Button) findViewById(R.id.btnShowBasket);        
        btnShowBasket.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		try {
					saveReservation();
					
	    	   		// go to Basket activity
					Intent intent = new Intent(Reservation_Form.this, BasketActivity.class);
		        	startActivity(intent);
				} catch (Exception e) {
	    	    	Toast.makeText(Reservation_Form.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		});

        btnPayDishes = (Button) findViewById(R.id.btnPayDishes);
        
        btnPayDishes.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		try {
					saveReservation();

					if (RestaurantManager.pushReservation()) {
	        	   		// go to Payment activity
						Intent intent = new Intent(Reservation_Form.this, Payment.class);
			        	startActivity(intent);
	    	   		} else {
		    	    	Toast.makeText(Reservation_Form.this, "Error while pushing to server!", Toast.LENGTH_SHORT).show();
	    	   		}
				} catch (Exception e) {
	    	    	Toast.makeText(Reservation_Form.this, e.getMessage(), Toast.LENGTH_SHORT).show();
				}
    	   		
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
	   	case R.id.update:
	   		Toast.makeText(getBaseContext(), "Updated", Toast.LENGTH_LONG).show();	
	        return true;
	   	case R.id.about:
	   		LayoutInflater inflater = getLayoutInflater();
	   		View layout = inflater.inflate(R.layout.toast_center,
	   		                               (ViewGroup) findViewById(R.id.toast_layout_root));	   		
	   		Toast t = new Toast(getApplicationContext());
	   		TextView tv = (TextView) layout.findViewById(R.id.tvToastCenter);
	   		tv.setText("Authors:\nUliana Andriieshyna & Dmitry Kuvayskiy\n\u00A9 2013");
	   		t.setView(layout);
	   		t.setDuration(Toast.LENGTH_LONG);
	   		t.show();
	        return true;
        case R.id.exit:  
        	Intent intent = new Intent(Intent.ACTION_MAIN);
        	intent.addCategory(Intent.CATEGORY_HOME);
        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	startActivity(intent);
        	return true;
       }
	return false;
   };

}
