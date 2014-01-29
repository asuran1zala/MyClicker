package ca.ualberta.cs.myclicker;


public class Clicker
{
	private String ClickerName;
	protected String Count;
	
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
	
	
	/**@Override
    public String toString() {
        return ClickerName + " - " + Count;
    }**/
}
