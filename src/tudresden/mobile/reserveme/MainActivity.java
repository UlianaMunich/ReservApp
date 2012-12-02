package tudresden.mobile.reserveme;

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
import tudresden.mobile.reserveme.backend.MyLocation;
import tudresden.mobile.reserveme.backend.RestaurantManager;

public class MainActivity extends Activity 
    implements AdapterView.OnItemSelectedListener  {	
	
	private Spinner spin;
	private Button saveButton;
	
	/* dimakuv: 
	 *   Background Task for fetching list of cities from our backend server into Spinner
	 */
	public class GetCitiesTask extends AsyncTask<Void, Void, ArrayAdapter<String>> {
		
		@Override
		protected ArrayAdapter<String> doInBackground(Void... urls) {
			ArrayAdapter<String> aa = null;

			String cities[] = RestaurantManager.getCities();
			if (cities != null) {
	     	   // TODO: is there a better way to dynamically change spinner's list?
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
        	   // dimakuv: uncomment this statement to connect to backend server
               new GetCitiesTask().execute();
           } else {
        	   	Toast.makeText(this, "WARNING: NO CONNECTION!", Toast.LENGTH_SHORT).show();		        	   
           }
        
       spin=(Spinner)findViewById(R.id.spinner1);
       spin.setOnItemSelectedListener(this);
       
       String[] cities = {"Dresden", "Leipzig", "Hamburg", "Berlin"};
       ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
    		   cities);
       aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin.setAdapter(aa);

       saveButton = (Button)findViewById(R.id.button1);
       saveButton.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
    	   		// open list of restaurants of this city
				Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
				Bundle b = new Bundle();
				b.putString("city", spin.getSelectedItem().toString());
				intent.putExtras(b);
				
	        	startActivity(intent);
			}
		});
       
    }
    
   @Override
   public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
		Bundle b = new Bundle();
		b.putString("city", spin.getSelectedItem().toString());
		intent.putExtras(b);
		
    	startActivity(intent);
    }
    
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
//		Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();	
	}

}
