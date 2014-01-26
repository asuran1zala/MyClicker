package ca.ualberta.cs.myclicker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import ca.ualberta.sc.myclicker.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
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

public class Main extends Activity
{
	
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView clickerList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button newClicker = (Button) findViewById(R.id.newClicker);
		bodyText = (EditText) findViewById(R.id.cName);
		clickerList = (ListView) findViewById(R.id.clickers);
		
		newClicker.setOnClickListener(new OnClickListener()
		{
			@SuppressLint("NewApi")
			public void onClick(View v) 
			{
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				if (text.length() > 0)
				{
					saveInFile(text, new Date(System.currentTimeMillis()));
					String[] cNames = loadFromFile();
					ArrayAdapter<String> newadapter = new ArrayAdapter<String>(Main.this,
							R.layout.list_item, cNames);
					clickerList.setAdapter(newadapter);
					newadapter.notifyDataSetChanged();
				}
				else
				{
					Toast toast=Toast.makeText(getApplicationContext(), "Clicker Name Empty", Toast.LENGTH_SHORT);  
				     toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
				     toast.show();
				}
				
			}
		});
		
		clickerList.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	              int position, long id)
			{
				String product = ((TextView) view).getText().toString();
				Intent i = new Intent(getApplicationContext(), Clicker.class);
				i.putExtra("product", product);
				startActivity(i);
			}
		});
		
	}
	

	private String[] loadFromFile() {
		ArrayList<String> cNames = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
			String line = in.readLine();
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
	
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(text + "\n")
					.getBytes());
			
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
		String[] cNames = loadFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, cNames);
		clickerList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	
	

}
