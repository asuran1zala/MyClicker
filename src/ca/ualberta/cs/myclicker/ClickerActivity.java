package ca.ualberta.cs.myclicker;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import ca.ualberta.cs.myclicker.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ClickerActivity extends Activity
{
	private static final String FILENAME = "file.sav";	
	private static String CLICKERFILE = "";
	private TextView number;
	private int count;
	private String cName = "";
	private final Gson gson = new Gson();
	private Clicker clicker = new Clicker();
	private File file;
	private String[] clickerArray;
	
	
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
        CLICKERFILE = cName + ".sav";
        
        file = getBaseContext().getFileStreamPath(CLICKERFILE);
        if(file.exists())
        {
        	loadFromFile(CLICKERFILE);
        }
        else
        {
        	loadFromFile(FILENAME);
        }
        number.setText(String.valueOf(clicker.getCount()));
        
        
        
        
        
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
        		clicker.setCount(text);
        		saveInFile(text, new Date(System.currentTimeMillis()));
        		
        	}
        });
        Button minusbutton = (Button) findViewById(R.id.zero);
        minusbutton.setOnClickListener(new OnClickListener() 
        {

			@Override
        	public void onClick(View v) 
        	{
				setResult(RESULT_OK);
				String text = number.getText().toString();
				count = 0;
				
        		number.setText(String.valueOf(count));
        		text = number.getText().toString();
        		clicker.setCount(text);
        		saveInFile(text, new Date(System.currentTimeMillis()));
        		
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
                		new DialogInterface.OnClickListener() 
                		{
                			public void onClick(DialogInterface dialog, int id) 
                			{
                				file.delete();
                				deleteClicker();
                				Intent i = new Intent(getApplicationContext(), MainActivity.class);
                				startActivity(i);
                				dialog.cancel();
                			}
                		});
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() 
                		{
                    		public void onClick(DialogInterface dialog, int id) 
                    		{
                    			dialog.cancel();
                    		}
                		});

                AlertDialog alert11 = builder1.create();
                alert11.show();
        	}
        });
        
	}

	
	
	private void loadFromFile(String file) {
		try {
			List<String> cArray = new ArrayList<String>();
			FileInputStream fis = openFileInput(file);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			Clicker line = gson.fromJson(in.readLine(), Clicker.class);
			while (line != null) 
			{
				if (line.getClickerName().equals(cName))
				{
					clicker = line;
				}
				else if (!line.getClickerName().equals(cName))
				{
					cArray.add(gson.toJson(line).toString());
				}
				
				line = gson.fromJson(in.readLine(), Clicker.class);
			}
			clickerArray = cArray.toArray(new String[cArray.size()]);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveInFile(String text, Date date) {
		try {
			
			FileOutputStream fos = openFileOutput(CLICKERFILE,
					Context.MODE_PRIVATE);
			
			fos.write(new String(gson.toJson(clicker)).getBytes());
			fos.close();

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void deleteClicker() {
		try {
			
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			for (int i = 0; i < clickerArray.length; i++)
			{
				fos.write(new String(clickerArray[i] + "\n").getBytes());
			}
			
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
