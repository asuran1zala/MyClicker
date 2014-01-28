package ca.ualberta.cs.myclicker;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class ClickerSerialiser implements JsonSerializer<Clicker>
{

	@Override
	public JsonElement serialize(final Clicker clicker, Type type,
			JsonSerializationContext context)
	{
		JsonObject result = new JsonObject();
		
		result.add("Name: ", new JsonPrimitive(clicker.getClickerName()));
		result.add("Count: ", new JsonPrimitive(clicker.getCount()));

		// TODO Auto-generated method stub
		return result;
	}

}
