package ambiorix.uitbreidingen;

import java.io.File;

import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.util.TypeVerzameling;

public class UitbreidingVerzameling extends TypeVerzameling<Uitbreiding> 
{	
	protected UitbreidingVerzameling()
	{
		
	}
	
	/*
	 * Gaat in het opgegeven pad zoeken naar uitbreidingen (neem aan dat er enkel uitbreidingenfolders in die map zit!)
	 */
	public void vulMetUitbreidingenUit(String pad)
	{
		// jammer genoeg kan File niet gewoon alle directories listen zonder de files...
		// thumbs.db en .DS_Store enzo moeten dus gefilterd worden...
		// ActionScript in Flex kan dit trouwens WEL gewoon, take THAT java
		
		File map = new File(pad);
		File[] uitbreidingenMappen = map.listFiles(); // ipv gewoon list()
		
		for( File uitbreidingMap : uitbreidingenMappen )
		{
			if( uitbreidingMap.isDirectory() )
			{
				Uitbreiding uitbreiding = new Uitbreiding(pad, uitbreidingMap.getName());
				this.registreerType(uitbreiding);
			}
		}
	}
	
	private static UitbreidingVerzameling instantie = null;
	
	public static UitbreidingVerzameling getInstantie()
	{
		if( instantie == null)
			instantie = new UitbreidingVerzameling();
		
		return instantie;
	}
}
