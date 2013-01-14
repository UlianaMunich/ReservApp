package tudresden.mobile.reserverest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class UserProfile extends Activity {
	private Button payDishes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_user_profile);
		
		//button to Activity with different methods of payment
		payDishes = (Button)findViewById(R.id.pay_dishes);
	    payDishes.setOnClickListener(new View.OnClickListener() {						
	    	   	@Override
				public void onClick(View v) {
	    	   		// open Google Maps view
					Intent intent = new Intent(UserProfile.this, Payment.class);
		        	startActivity(intent);
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.layout_user_profile, menu);
		return true;
	}

}
