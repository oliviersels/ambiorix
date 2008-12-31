package ambiorix.uitbreidingen;

import java.io.File;

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
		// thumbs.db en .DS_Store enzo moeten dus gefilterd worden... om nog maar te zwijgen van .svn :)
		// ActionScript in Flex kan dit trouwens WEL gewoon, take THAT java
		//
		// Probeer dit eens:
		//		File dirs[] = map.listFiles(new FileFilter() {
		//			public boolean accept(File pathName) {
		//				return pathName.isDirectory();
		//			}
		//		});
		
		
		
		File map = new File(pad);
		String[] uitbreidingenMappen = map.list();
		
		for(String uitbreidingMap : uitbreidingenMappen)
		{
			File test = new File( pad + uitbreidingMap + "/info.xml" );
			if( test.exists() )
			{
				Uitbreiding uitbreiding = new Uitbreiding(pad, uitbreidingMap);
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
