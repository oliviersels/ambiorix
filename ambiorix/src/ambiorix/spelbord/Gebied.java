package ambiorix.spelbord;

import java.util.Vector;

public class Gebied 
{
	private TerreinType type;
	private Vector<Terrein> terreinStukken;
	
	public Gebied()
	{
		
	}
	
	public void voegToe( Terrein h )
	{
		terreinStukken.add(h);
	}
	
	
	public TerreinType getType() 
	{
		return type;
	}

	public void setType(TerreinType type) 
	{
		this.type = type;
	}

	public Vector<Terrein> getTerreinStukken() 
	{
		return terreinStukken;
	}	
	
}
