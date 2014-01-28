package ca.ualberta.cs.myclicker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
{
	
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView clickerList;
	private final GsonBuilder gsonBuilder = new GsonBuilder();
	private final Gson gson = gsonBuilder.create();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button newClicker = (Button) findViewById(R.id.newClicker);
		bodyText = (EditText) findViewById(R.id.cName);
		clickerList = (ListView) findViewById(R.id.clickers);
		
		gsonBuilder.registerTypeAdapter(Clicker.class, new ClickerSerialiser());
		gsonBuilder.registerTypeAdapter(Clicker.class, new ClickerSerialiser());
		
		newClicker.setOnClickListener(new OnClickListener()
		{
			@SuppressLint("NewApi")
			public void onClick(View v) 
			{
				setResult(RESULT_OK);
				String name = bodyText.getText().toString();
				if (name.trim().length() == 0)
				{
					Toast toast=Toast.makeText(getApplicationContext(), "Clicker Name Empty", Toast.LENGTH_SHORT);  
				    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
				    toast.show();
				}
				else if (name.trim().length() > 10)
				{
					Toast toast=Toast.makeText(getApplicationContext(), "Limit size of 10 characters", Toast.LENGTH_SHORT);  
				    toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
				    toast.show();
				}
				else
				{
					final Clicker clicker = new Clicker();
					clicker.setClickerName(name);
					clicker.setCount("0");
					
					saveInFile(clicker, new Date(System.currentTimeMillis()));
					String[] cNames = loadsFromFile();
					ArrayAdapter<String> newadapter = new ArrayAdapter<String>(MainActivity.this,
							R.layout.list_item, cNames);
					clickerList.setAdapter(newadapter);
					newadapter.notifyDataSetChanged();
				}
				
			}
		});
		
		clickerList.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id)
			{
				String cName = ((TextView) view).getText().toString();
				Intent i = new Intent(getApplicationContext(), ClickerActivity.class);
				i.putExtra("cName", cName);
				startActivity(i);
			}
		});
		
	}
	

	private String[] loadsFromFile() {
		ArrayList<String> cNames = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			String line = in.readLine();
			gson.toJson(line);
			while (line != null) {
				cNames.add(line);
				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cNames.toArray(new String[cNames.size()]);
	}
	
	void saveInFile(Clicker clicker, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(gson.toJson(clicker).toString().getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		String[] cNames = loadsFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, cNames);
		clickerList.setAdapter(adapter);
	}
	
}


