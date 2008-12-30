package ambiorix.spelbord;

import ambiorix.util.Type;

public class TerreinType extends Type
{
	protected boolean toegankelijk = false;
	
	public TerreinType(String ID, boolean toegankelijk)
	{
		this.ID = ID;
		this.toegankelijk = toegankelijk;
	}
	
	public boolean isToegankelijk()
	{
		return toegankelijk;
	}

}
