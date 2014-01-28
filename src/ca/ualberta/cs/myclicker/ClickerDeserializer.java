package ca.ualberta.cs.myclicker;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


public class ClickerDeserializer implements JsonDeserializer<Clicker>
{

	@Override
	public Clicker deserialize(final JsonElement json, final Type typeOfT, final 
			JsonDeserializationContext context) throws JsonParseException
	{
		final JsonObject jsonObject = json.getAsJsonObject();
		final JsonElement jsonClickerName = jsonObject.get("Clicker Name");
		final String ClickerName = jsonClickerName.getAsString();
		
		final String count = jsonObject.get("Count").getAsString();
		
		
		
		final Clicker clicker = new Clicker();
		clicker.setClickerName(ClickerName);
		clicker.setCount(count);
		
		
		// TODO Auto-generated method stub
		return clicker;
	}

}
