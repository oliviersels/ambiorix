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
		
		try
		{
			KlasseLader<TerreinType> terreinLader = new KlasseLader<TerreinType>(uitbreidingPad);
			TerreinType lava = terreinLader.LaadKlasse("ambiorix.spelbord.terreintypes.TerreinType_Lava").newInstance();
			TerreinTypeVerzameling.getInstantie().registreerType(lava);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
