package ambiorix.uitbreidingen;

import java.io.File;
import java.io.FileFilter;
import java.util.Vector;

import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.ai.Ai;
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
		if( terreinTypesNamen != null )
		{
			KlasseLader<TerreinType> terreinLader = new KlasseLader<TerreinType>(uitbreidingPad);
			
			for( String terreinTypeNaam : terreinTypesNamen )
			{
				if( terreinTypeNaam.contains(".class") ) // anders geen geldige file
				{
					TerreinType terreinType = terreinLader.LaadKlasse("ambiorix.spelbord.terreintypes." + getClassNaam(terreinTypeNaam) ).newInstance();
					TerreinTypeVerzameling.getInstantie().registreerType(terreinType);
				}
			}
		}
		
		// TEGELTYPES
		File tegelTypesMap = new File( uitbreidingPad + "ambiorix/spelbord/tegeltypes/" );
		
		String[] tegelTypesNamen = tegelTypesMap.list();
		if( tegelTypesNamen != null )
		{
			KlasseLader<TegelType> tegelLader = new KlasseLader<TegelType>(uitbreidingPad);
			
			for( String tegelTypeNaam : tegelTypesNamen )
			{
				if( tegelTypeNaam.contains(".class") ) // anders geen geldige file
				{
					TegelType tegelType = tegelLader.LaadKlasse("ambiorix.spelbord.tegeltypes." + getClassNaam(tegelTypeNaam) ).newInstance();
					TegelTypeVerzameling.getInstantie().registreerType(tegelType);
				}
			}
		}
		
		// PIONTYPES
		File pionTypesMap = new File( uitbreidingPad + "ambiorix/spelbord/piontypes/" );
		
		String[] pionTypesNamen = pionTypesMap.list();
		if( pionTypesNamen != null )
		{
			KlasseLader<PionType> pionLader = new KlasseLader<PionType>(uitbreidingPad);
			
			for( String pionTypeNaam : pionTypesNamen )
			{
				if( pionTypeNaam.contains(".class") ) // anders geen geldige file
				{
					PionType pionType = pionLader.LaadKlasse("ambiorix.spelbord.piontypes." + getClassNaam(pionTypeNaam) ).newInstance();
					PionTypeVerzameling.getInstantie().registreerType(pionType);
				}
			}
		}
		
		// ACTIES
		
		File actiesMap = new File( uitbreidingPad + "ambiorix/acties/specifiek/" );
		
		String[] actieNamen = actiesMap.list();
		if( actieNamen != null )
		{
			KlasseLader<AbstractActie> actieLader = new KlasseLader<AbstractActie>(uitbreidingPad);
			
			for( String actieNaam : actieNamen )
			{
				if( actieNaam.contains(".class") ) // anders geen geldige file
				{
					Class<AbstractActie> actie = actieLader.LaadKlasse("ambiorix.acties.specifiek." + getClassNaam(actieNaam) );
					ActieVerzameling.getInstantie().registreerType(actie);
				}
			}
		}
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
		// .class achter de filenames uithalen
		private String getClassNaam(String input)
		{
			String output = input.replace(".class", "");
			return output;
		}

	@Override
	public String getEersteActie() 
	{
		// deze moet best wel ingesteld worden !!
		return null;
	}
	
	@Override
	public Ai getAi()
	{
		// standaard is er maar 1 Ai per uitbreiding
		// dus de eerste class in de juiste folder is standaard de juiste.
		
		File aiMap = new File( uitbreidingPad + "ambiorix/ai/specifiek/" );
		
		File[] aiNamen = aiMap.listFiles( new FileFilter() {
			public boolean accept(File pathName) {
				return pathName.getName().contains(".class");
			}
			});
		
		if( aiNamen == null )
			return null;

		Ai ai = null;
		
		try
		{
			KlasseLader<Ai> aiLader = new KlasseLader<Ai>(uitbreidingPad);
			ai = aiLader.LaadKlasse("ambiorix.ai.specifiek." + aiNamen[0].getName() ).newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ai;		
		
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
		
		if( berekenaarsNamen == null )
			return null;

		ScoreBerekenaar berekenaar = null;
		
		try
		{
			KlasseLader<ScoreBerekenaar> berekenaarLader = new KlasseLader<ScoreBerekenaar>(uitbreidingPad);
			berekenaar = berekenaarLader.LaadKlasse("ambiorix.spelbord.scoreberekenaars." + getClassNaam(berekenaarsNamen[0].getName()) ).newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return berekenaar;
	}
}
