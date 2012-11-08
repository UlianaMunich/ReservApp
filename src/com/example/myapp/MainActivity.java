package com.example.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;    
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity 
    implements AdapterView.OnItemSelectedListener  {
	private static final String[] items={"Dresden", "Leipzig", "Hamburg", "Berlin"};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

       Spinner spin=(Spinner)findViewById(R.id.spinner11111);
       spin.setOnItemSelectedListener(this);
       
       ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
    		   items);
       aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin.setAdapter(aa);
       
       findViewById(R.id.button1);
       final Button saveButton = (Button)findViewById(R.id.button1);
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
