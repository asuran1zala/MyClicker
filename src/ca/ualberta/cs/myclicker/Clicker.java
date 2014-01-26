package ca.ualberta.cs.myclicker;


import ca.ualberta.sc.myclicker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Clicker extends Activity
{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.clicker_activity);
        
        TextView txtProduct = (TextView) findViewById(R.id.Clicker_Name);
        
        Intent i = getIntent();
        // getting attached intent data
        String cName = i.getStringExtra("cName");
        // displaying selected product name
        txtProduct.setText(cName + ":");
	}
}
