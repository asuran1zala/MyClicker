package ca.ualberta.cs.myclicker;

import java.util.Date;

public class Clicker
{
	private String ClickerName;
	protected String Count = "0";
	private Date Create_Date;
	
	public String getClickerName()
	{
	
		return ClickerName;
	}
	
	public void setClickerName(String clickerName)
	{
	
		ClickerName = clickerName;
	}
	
	public String getCount()
	{
	
		return Count;
	}
	
	public void setCount(String count)
	{
	
		Count = count;
	}
	
	
	@Override
    public String toString() {
        return ClickerName + ", " + Count;
    }

	public Date getCreate_Date()
	{

		return Create_Date;
	}

	public void setCreate_Date(Date date)
	{

		Create_Date = date;
	}
}
