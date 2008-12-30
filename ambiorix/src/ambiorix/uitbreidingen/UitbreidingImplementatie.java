package ambiorix.uitbreidingen;

import java.io.File;

import ambiorix.spelbord.PionType;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.TegelType;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.util.Type;

public class UitbreidingImplementatie extends Type implements UitbreidingInterface
{
	protected String uitbreidingPad = null;
	
	// deze functie gaat alle noodzakelijke classes inlezen en voorbereiden.
	// zorg dat uitbreidingPad zeker gezet is vooraleer dit aan te roepen !
	public void bereidVoor()
	{
		try
		{
			
		// TODO : ik weet dat dit generieker kan in 1 functie, maar for the time begin...
			
		// TERREINTYPES
		File terreinTypesMap = new File( uitbreidingPad + "ambiorix/spelbord/terreintypes/" );
		
		String[] terreinTypesNamen = terreinTypesMap.list();
		KlasseLader<TerreinType> terreinLader = new KlasseLader<TerreinType>(uitbreidingPad);
		
		for( String terreinTypeNaam : terreinTypesNamen )
		{
			if( terreinTypeNaam.contains(".class") ) // anders geen geldige file
			{
				TerreinType terreinType = terreinLader.LaadKlasse("ambiorix.spelbord.terreintypes." + terreinTypeNaam ).newInstance();
				TerreinTypeVerzameling.getInstantie().registreerType(terreinType);
			}
		}
		
		// TEGELTYPES
		File tegelTypesMap = new File( uitbreidingPad + "ambiorix/spelbord/tegeltypes/" );
		
		String[] tegelTypesNamen = tegelTypesMap.list();
		KlasseLader<TegelType> tegelLader = new KlasseLader<TegelType>(uitbreidingPad);
		
		for( String tegelTypeNaam : tegelTypesNamen )
		{
			if( tegelTypeNaam.contains(".class") ) // anders geen geldige file
			{
				TegelType tegelType = tegelLader.LaadKlasse("ambiorix.spelbord.tegeltypes." + tegelTypeNaam ).newInstance();
				TegelTypeVerzameling.getInstantie().registreerType(tegelType);
			}
		}
		
		// PIONTYPES
		File pionTypesMap = new File( uitbreidingPad + "ambiorix/spelbord/piontypes/" );
		
		String[] pionTypesNamen = pionTypesMap.list();
		KlasseLader<PionType> pionLader = new KlasseLader<PionType>(uitbreidingPad);
		
		for( String pionTypeNaam : pionTypesNamen )
		{
			if( pionTypeNaam.contains(".class") ) // anders geen geldige file
			{
				PionType pionType = pionLader.LaadKlasse("ambiorix.spelbord.piontypes." + pionTypeNaam ).newInstance();
				PionTypeVerzameling.getInstantie().registreerType(pionType);
			}
		}
		
		// ACTIES
		// TODO : olivier, gewoon exact zelfde als hierboven, maar dan met ActieVerzameling en ambiorix.acties.acties of zoiets...
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
