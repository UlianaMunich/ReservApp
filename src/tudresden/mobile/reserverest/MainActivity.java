package tudresden.mobile.reserverest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;    
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.MyLocation;
import tudresden.mobile.reserverest.backend.RestaurantManager;
import android.view.MenuItem;

public class MainActivity extends Activity 
    implements AdapterView.OnItemSelectedListener  {	
	
	private Spinner spin;
	private Button mapButton;
	
	/* dimakuv: 
	 *   Background Task for fetching list of cities from our backend server into Spinner
	 */
	public class GetCitiesTask extends AsyncTask<Void, Void, ArrayAdapter<String>> {
		
		@Override
		protected ArrayAdapter<String> doInBackground(Void... urls) {
			ArrayAdapter<String> aa = null;

			String cities[] = RestaurantManager.getCities();
			if (cities != null) {
	           aa = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, 
	        		   cities);
	           aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			}	        
	        
			return aa;
		}
		
		@Override
		protected void onPostExecute(ArrayAdapter<String> aa) {			
			// NOTE: this method is executed in main, UI thread
			if (aa != null) {
				spin.setAdapter(aa);
			} else {
        	   	Toast.makeText(MainActivity.this, "WARNING: Didn't retrieve cities!", Toast.LENGTH_SHORT).show();		        	   
           }
		}
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

		RestaurantManager.initConnection();
		
		// find location from GPS or network, whichever is available
		MyLocation.getInstance(this).updateLocation();
		
       // dimakuv: check network connection
       ConnectivityManager connMgr = (ConnectivityManager) 
               getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
           if (networkInfo != null && networkInfo.isConnected()) {
               new GetCitiesTask().execute();
           } else {
        	   	Toast.makeText(this, "WARNING: NO CONNECTION!", Toast.LENGTH_SHORT).show();		        	   
           }
        
       spin=(Spinner)findViewById(R.id.spinner1);
       spin.setOnItemSelectedListener(this);
       
       ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
    		   new String[]{});
       aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin.setAdapter(aa);

       mapButton = (Button)findViewById(R.id.button1);
       mapButton.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		// open Google Maps view
				Intent intent = new Intent(MainActivity.this, MapActivity.class);
	        	startActivity(intent);
			}
		});
       
    }
    
   @Override
   public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
	    if (position == 0) {
	    	// "-- choose city --" (default) is selected
	    	return;
	    }
	    	
		Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
		Bundle b = new Bundle();
		b.putString("city", spin.getSelectedItem().toString());
		intent.putExtras(b);
		
    	startActivity(intent);
    }
   
 //Options menu   
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
 // handling OptionsMenu event
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       // Handle item selection
       switch (item.getItemId()) {
	   	case R.id.profile:
	        	// go to UserProfile
	        	Intent mainIntent = new Intent(MainActivity.this, UserProfile.class);
	        	startActivity(mainIntent);
	        	Toast.makeText(getBaseContext(), "Profile", Toast.LENGTH_LONG).show();	
	        	return true;
        case R.id.exit:  
        	   Toast.makeText(getBaseContext(), "Updated", Toast.LENGTH_LONG).show();	
        return true;

       }
	return false;
   };
   
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
//		Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();	
	}

}
