package ambiorix.uitbreidingen;

import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelbord.PionType;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.TegelType;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.util.Type;

public class UitbreidingImplementatie extends Type implements UitbreidingInterface
{
	protected String uitbreidingPad = null;
	protected Vector<String> andereUitbreidingen = null;
	
	// deze functie gaat alle noodzakelijke classes inlezen en voorbereiden.
	// zorg dat uitbreidingPad zeker gezet is vooraleer dit aan te roepen !
	public void bereidVoor(Vector<String> andereUitbreidingen)
	{
		this.andereUitbreidingen = andereUitbreidingen;
		
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
		
		File actiesMap = new File( uitbreidingPad + "ambiorix/acties/specifiek/" );
		
		String[] actieNamen = actiesMap.list();
		KlasseLader<AbstractActie> actieLader = new KlasseLader<AbstractActie>(uitbreidingPad);
		
		for( String actieNaam : actieNamen )
		{
			if( actieNaam.contains(".class") ) // anders geen geldige file
			{
				AbstractActie actie = actieLader.LaadKlasse("ambiorix.acties.specifiek." + actieNaam ).newInstance();
				ActieVerzameling.getInstantie().registreerType(actie);
			}
		}
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public String getEersteActie() 
	{
		// deze moet best wel ingesteld worden !!
		return null;
	}

	@Override
	public ScoreBerekenaar getScoreBerekenaar() 
	{
		// standaard is er maar 1 scoreberekenaar per uitbreiding
		// dus de eerste class in de juiste folder is standaard de juiste.
		
		File berekenaarsMap = new File( uitbreidingPad + "ambiorix/spelbord/scoreberekenaars/" );
		
		File[] berekenaarsNamen = berekenaarsMap.listFiles( new FileFilter() {
			public boolean accept(File pathName) {
				return pathName.getName().contains(".class");
			}
			});
		
		if( berekenaarsNamen.length == 0 )
			return null;

		ScoreBerekenaar berekenaar = null;
		
		try
		{
			KlasseLader<ScoreBerekenaar> berekenaarLader = new KlasseLader<ScoreBerekenaar>(uitbreidingPad);
			berekenaar = berekenaarLader.LaadKlasse("ambiorix.spelbord.scoreberekenaars." + berekenaarsNamen[0].getName() ).newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return berekenaar;
	}
}
