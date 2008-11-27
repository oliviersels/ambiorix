package ambiorix.spelbord;

import ambiorix.util.Punt;

public class Terrein 
{
	private Tegel tegel = null;
	private Punt positie = null;
	
	public Terrein( Tegel tegel, Punt positie)
	{
		this.tegel = tegel;
		this.positie = positie;
	}
	
	public TerreinType getType()
	{
		return tegel.getTerrein()[ positie.getX() ][ positie.getY() ];
	}

	public Tegel getTegel() 
	{
		return tegel;
	}

	public void setTegel(Tegel tegel) 
	{
		this.tegel = tegel;
	}

	public Punt getPositie() 
	{
		return positie;
	}

	public void setPositie(Punt positie) 
	{
		this.positie = positie;
	}
}
