package tudresden.mobile.reserverest;

import tudresden.mobile.reserverest.backend.RestaurantManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends Activity {
	
	ImageButton btnPushReserve2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		
		btnPushReserve2 = (ImageButton) findViewById(R.id.btnPushReserve2);
		
		btnPushReserve2.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		if (RestaurantManager.pushReservation()) {
    		   		// go to End activity
    				Intent intent = new Intent(Payment.this, EndActivity.class);
    	        	startActivity(intent);
    	   		} else {
    		    	Toast.makeText(Payment.this, "Error while pushing to server!", Toast.LENGTH_SHORT).show();
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