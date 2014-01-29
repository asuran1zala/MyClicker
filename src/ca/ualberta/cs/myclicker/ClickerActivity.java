package ca.ualberta.cs.myclicker;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.google.gson.Gson;

import ca.ualberta.cs.myclicker.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ClickerActivity extends Activity
{
	public static final String FILENAME= "file.sav";	
	public static final String TEMP = "temp.sav";
	public TextView number;
	public int count;
	public String cName = "";
	final Gson gson = new Gson();
	Clicker clicker = new Clicker();
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.clicker_activity);
        
        
        
        TextView txtProduct = (TextView) findViewById(R.id.Clicker_Name);
        number  = (TextView) findViewById(R.id.number);
        
        
        
        Intent i = getIntent();
        // getting attached intent data
        cName = i.getStringExtra("cName");
        // displaying selected product name
        txtProduct.setText(cName + ": ");
        
        String counter = loadFromFile();
        number.setText(String.valueOf(counter));
        
        
        
        
        
        Button addbutton = (Button) findViewById(R.id.add);
        addbutton.setOnClickListener(new OnClickListener() 
        {

			@Override
        	public void onClick(View v) 
        	{
				//setResult(RESULT_OK);
				String text = number.getText().toString();
				
				
				
				count = Integer.parseInt(text);
				
				count++;
        		number.setText(String.valueOf(count));
        		text = number.getText().toString();
        		clicker.setCount(text);
        		saveInFile(text, new Date(System.currentTimeMillis()));
        		
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
        		
        		//saveInFile(text, new Date(System.currentTimeMillis()));
        		
        	}
        });
        Button delbutton = (Button) findViewById(R.id.delete);
        delbutton.setOnClickListener(new OnClickListener() 
        {
        	@Override
        	public void onClick(View v) 
        	{
        		AlertDialog.Builder builder1 = new AlertDialog.Builder(ClickerActivity.this);
                builder1.setMessage("Are you sure you want delete this clicker?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert11 = builder1.create();
                alert11.show();
        	}
        });
        
	}

	
	private String loadFromFile() {
		String line = "";
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Clicker liner = gson.fromJson(in.readLine(), Clicker.class);
			while (liner != null) 
			{
				if (liner.getClickerName().equals(cName))
				{
					clicker = liner;
					line = liner.getCount();
					break;
				}
				liner = gson.fromJson(in.readLine(), Clicker.class);
			}

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
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			FileOutputStream fos = openFileOutput(TEMP,
					Context.MODE_PRIVATE);
			
			Clicker liner = gson.fromJson(in.readLine(), Clicker.class);
			while (liner != null) 
			{
				if (liner.getClickerName().equals(cName))
				{
					fos.write(new String(gson.toJson(clicker) + "\n").getBytes());
				}
				else
				{
					fos.write(new String(gson.toJson(liner) + "\n").getBytes());
				}
				liner = gson.fromJson(in.readLine(), Clicker.class);
			}
			fos.close();
			
			File file = new File(TEMP);
			File file2 = new File("haha");
			
			if (file.renameTo(file2)) {  
				
				Toast toast=Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT);  
			    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			    toast.show();
			   } 
			else {
				Toast toast=Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_SHORT);  
			    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
			    toast.show();  
			   }  
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
