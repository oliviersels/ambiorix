package ambiorix.spelbord;

public class TegelTypeVerzameling extends TypeVerzameling<TegelType>
{
	protected TegelTypeVerzameling()
	{
		
	}
	
	private static TegelTypeVerzameling instantie = null;
	
	public static TegelTypeVerzameling getInstantie()
	{
		if( instantie == null)
			instantie = new TegelTypeVerzameling();
		
		return instantie;
	}		
}
