package ambiorix.spelbord;

public class TerreinTypeVerzameling extends TypeVerzameling<TerreinType>
{
	protected TerreinTypeVerzameling()
	{
		
	}
	
	private static TerreinTypeVerzameling instantie = null;
	
	public static TerreinTypeVerzameling getInstantie()
	{
		if( instantie == null)
			instantie = new TerreinTypeVerzameling();
		
		return instantie;
	}	
}
