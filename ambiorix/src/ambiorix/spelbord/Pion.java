package ambiorix.spelbord;

import ambiorix.spelers.Speler;

public class Pion implements PionBasis
{
	private int ID = -1;
	private PionType type = null;
	
	private Speler speler = null;
	
	public Pion(int ID, PionType type)
	{
		this.ID = ID;
		this.type = type;
	}

	public int getID() 
	{
		return ID;
	}

	public PionType getType() 
	{
		return type;
	}

	public Speler getSpeler() 
	{
		return speler;
	}

	public void setSpeler(Speler speler) 
	{
		this.speler = speler;
	}
}
