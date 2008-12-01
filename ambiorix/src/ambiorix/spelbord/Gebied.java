package ambiorix.spelbord;

import java.util.Vector;

public class Gebied 
{
	private TerreinType type;
	private Vector<Terrein> terreinStukken = new Vector<Terrein>();
	
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
	
	public void setTerreinStukken( Vector<Terrein> stukken )
	{
		this.terreinStukken = stukken;
	}
	
}
