package com.example.myapp;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
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
import org.restlet.engine.Engine;
import org.restlet.ext.httpclient.HttpClientHelper;

public class MainActivity extends Activity 
    implements AdapterView.OnItemSelectedListener  {	
	
	private Spinner spin;
	private Button saveButton;
	
	/* dimakuv: 
	 *   Background Task for checking connection and
	 *   fetching list of restaurants from our backend server into Spinner
	 */
	public class TestConnection extends AsyncTask<Void, Void, ArrayAdapter<String>> {
		
		@Override
		protected ArrayAdapter<String> doInBackground(Void... urls) {
           ArrayAdapter<String> aa = null;

	        // -- test if our server is up and running --
           
	        try {
	        	// NOTE: don't use "localhost" or "127.0.0.1", but your real IP 
	            URL myUrl = new URL("http://192.168.0.100:8182/restaurants/list");
	            URLConnection connection = myUrl.openConnection();
	            connection.setConnectTimeout(1000000);
	            connection.connect();
	        } catch (IOException e) {
	            e.toString();
	        }
	        
	        // -- fetch the list of restaurants using RESTlet framework --
	        
	        // initialize RESTlet client to use Apache HTTP Client
	        Engine.getInstance().getRegisteredClients().clear();
	        Engine.getInstance().getRegisteredClients().add(new HttpClientHelper(null)); 

        	// NOTE: don't use "localhost" or "127.0.0.1", but your real IP 
	        ClientResource cr = new ClientResource("http://192.168.0.100:8182/restaurants/list");
	        try {
	            // GET list of restaurants in JSON notation from the remote server
	     	   String json_list = cr.get(MediaType.APPLICATION_JSON).getText();
	     	   JSONObject jsonObj = new JSONObject(json_list);
	     	   JSONArray json_restaurants = jsonObj.getJSONArray("restaurants");
	     	   
	     	   String[] items = new String[json_restaurants.length()];
	     	   for (int i = 0; i < json_restaurants.length(); i++) {
	     		   items[i] = json_restaurants.getString(i);
	     	   }

	     	   // TODO: is there a better way to dynamically change spinner's list?
	           aa = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, 
	        		   items);
	           aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     	   
	        } catch (Exception e) {
	 			e.printStackTrace();
	        }
	        
	        
			return aa;
		}
		
		@Override
		protected void onPostExecute(ArrayAdapter<String> aa) {
			// NOTE: this method is executed in main, UI thread
	        spin.setAdapter(aa);			
		}
	}
	
	private static final String[] items={"Dresden", "Leipzig", "Hamburg", "Berlin"};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

       // dimakuv: check network connection
       ConnectivityManager connMgr = (ConnectivityManager) 
               getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
           if (networkInfo != null && networkInfo.isConnected()) {
        	   // dimakuv: uncomment this statement to connect to backend server
               // new TestConnection().execute();
           }
        
       spin=(Spinner)findViewById(R.id.spinner1);
       spin.setOnItemSelectedListener(this);
       
       ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
    		   items);
       aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin.setAdapter(aa);

       saveButton = (Button)findViewById(R.id.button1);
       saveButton.setOnClickListener(new View.OnClickListener() {						
    	   	@Override
			public void onClick(View v) {
				Intent contactsIntent = new Intent(MainActivity.this,ActivityNiarestRestauranes.class);
	        	startActivity(contactsIntent);
			}
		});
       
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
    	Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();		
    }
    
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();	
		
	}

}
