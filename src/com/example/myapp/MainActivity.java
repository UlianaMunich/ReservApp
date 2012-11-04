package com.example.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Adapter;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
 /* public class SpinnerDemo extends Activity
    implements Adapter.OnItemSelectedListener {
    private TextView selection;
    private static final String[] items={"Dresden", "Leipzig", "Hamburg", "Berlin"};
@Override
public void onCreate(Bundle icicle) {
super.onCreate(icicle);
setContentView(R.layout.main);
selection=(TextView)findViewById(R.id.selection);
Spinner spin=(Spinner)findViewById(R.id.spinner);
spin.setOnItemSelectedListener(this);
ArrayAdapter<String> aa=new ArrayAdapter<String>(this,
android.R.layout.simple_spinner_item,
items);
aa.setDropDownViewResource(
android.R.layout.simple_spinner_dropdown_item);
spin.setAdapter(aa);
}
@Override
public void onItemSelected(AdapterView<?> parent,
View v, int position, long selection.setText(items[position]);
id) {
}
@Override
public void onNothingSelected(AdapterView<?> parent) {
selection.setText("");
}
}
*/
}
