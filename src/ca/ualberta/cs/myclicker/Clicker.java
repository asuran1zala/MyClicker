package ca.ualberta.cs.myclicker;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import ca.ualberta.cs.myclicker.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Clicker extends Activity
{
	public static final String FILENAME = "clicker.sav";
	public TextView number;
	public int count;
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.clicker_activity);
        
        
        
        TextView txtProduct = (TextView) findViewById(R.id.Clicker_Name);
        number  = (TextView) findViewById(R.id.number);
        
        //String cNames = loadFromFile();
        //number.setText(cNames.toString());
        
        Intent i = getIntent();
        // getting attached intent data
        String cName = i.getStringExtra("cName");
        // displaying selected product name
        txtProduct.setText(cName + ": ");
        
        
        Button addbutton = (Button) findViewById(R.id.add);
        addbutton.setOnClickListener(new OnClickListener() 
        {

			@Override
        	public void onClick(View v) 
        	{
				setResult(RESULT_OK);
				String text = number.getText().toString();
				
				count = Integer.parseInt(text);
				
				count++;
        		number.setText(String.valueOf(count));
        		text = number.getText().toString();
        		saveInFile("text", new Date(System.currentTimeMillis()));
        		
        	}
        });
        Button minusbutton = (Button) findViewById(R.id.minus);
        minusbutton.setOnClickListener(new OnClickListener() 
        {

			@Override
        	public void onClick(View v) 
        	{
				setResult(RESULT_OK);
				String text = number.getText().toString();
				
				count = Integer.parseInt(text);
				
				count--;
				if(count < 0)
				{
					count = 0;
				}
        		number.setText(String.valueOf(count));
        		text = number.getText().toString();
        		saveInFile("text", new Date(System.currentTimeMillis()));
        		
        	}
        });
        
	}

	
	private String loadFromFile() {
		String line = "";
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			line = in.readLine();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(text).getBytes());
			
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
