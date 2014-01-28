package ca.ualberta.cs.myclicker;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class CSerialiser implements JsonSerializer<Clicker>
{
	@Override
    public JsonElement serialize(final Clicker clicker, final Type typeOfSrc, final JsonSerializationContext context)
	{

		final JsonObject clickerJO = new JsonObject();
		clickerJO.addProperty("Clicker Name", clicker.getClickerName());
		clickerJO.addProperty("Count", clicker.getCount());
		
		return clickerJO;
	}
}
