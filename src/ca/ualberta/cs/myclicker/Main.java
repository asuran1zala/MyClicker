package ca.ualberta.cs.myclicker;

import ca.ualberta.sc.myclicker.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button newClicker = (Button) findViewById(R.id.newClicker);
		
		newClicker.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Button b = new Button(v.getContext());
				b.setText("Some text on Button");
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	
	

}
