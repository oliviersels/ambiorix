package ambiorix.uitbreidingen.implementaties;

import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelbord.terreintypes.TerreinType_Burcht;
import ambiorix.uitbreidingen.KlasseLader;
import ambiorix.uitbreidingen.UitbreidingImplementatie;

public class Uitbreiding_Basis extends UitbreidingImplementatie 
{
	public Uitbreiding_Basis()
	{
		uitbreidingPad = "uitbreidingen/Basis/";
	}
	
	public void bereidVoor()
	{
		System.out.println("DIT IS DE BASISUITBREIKlasseLader INGELADEN, JOCHEI");
		
		super.bereidVoor();
		
	}
}
