package ambiorix.spelbord.scoreberekenaars;

import java.util.HashMap;
import java.util.Set;

import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelbord.TegelBasis.RICHTING;
import ambiorix.spelers.Speler;

public class SimpelScoreBerekenaar implements ScoreBerekenaar 
{
	public SimpelScoreBerekenaar()
	{
		
	}
	
	@Override
	public int berekenScore(Gebied gebied, Speler speler) 
	{ 
		// voor weiden moet het gebied niet volledig zijn
		if( (!gebied.isVolledig()) && (gebied.getType() != TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras")) )
			return 0;
		
		// als er geen pionnen op staan, kunnen we ook geen score hebben
		if( gebied.getPionnen().size() == 0 )
			return 0;
		
		int spelerHeeftMeestePionnen = spelerHeeftMeestePionnen(gebied,speler);
		if( spelerHeeftMeestePionnen == -1 )
			return 0;
		
		
		if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg") )
		{
			// aantal tegels waaruit de weg bestaat is meteen ook de score
			return gebied.getTegels().size();
		}
		else if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Burcht") )
		{
			// kasteel is (tegels * 2) + (schilden * 2) // TODO : wat met schilden ???
			return ( 2 * gebied.getTegels().size() );	
		}
		else if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Klooster") )
		{
			// klooster volledig omringd is 9 punten
			// anders 1 punt per tegel errond
			// => gebied is altijd afgemaakt natuurlijk, we moeten de tegel gaan controleren op zijn buren
			
			Tegel tegel = gebied.getTegels().get(0);
			
			int aantalBuren = 0;
			
			Tegel bovenBuur = 	tegel.getBuur(RICHTING.BOVEN);
			Tegel onderBuur = 	tegel.getBuur(RICHTING.ONDER);
			Tegel linkerBuur = 	tegel.getBuur(RICHTING.LINKS);
			Tegel rechterBuur =	tegel.getBuur(RICHTING.RECHTS);
			
			if( bovenBuur != null )
				aantalBuren++;
			if( onderBuur != null )
				aantalBuren++;
			
			if( linkerBuur != null )
			{
				aantalBuren++;
				if( linkerBuur.getBuur(RICHTING.BOVEN) != null )
					aantalBuren++;
				if( linkerBuur.getBuur(RICHTING.ONDER) != null )
					aantalBuren++;
			}
			else
			{
				// kijken of we aan de tegel linksboven kunnen komen via boven
				if( bovenBuur != null )
					if( bovenBuur.getBuur(RICHTING.LINKS) != null )
						aantalBuren++;
				
				// kijken of we aan de tegel linksonder kunnen komen via onder
				if( onderBuur != null )
					if( onderBuur.getBuur(RICHTING.LINKS) != null )
						aantalBuren++;
			}
			
			if( rechterBuur != null )
			{
				aantalBuren++;
				if( rechterBuur.getBuur(RICHTING.BOVEN) != null )
					aantalBuren++;
				if( rechterBuur.getBuur(RICHTING.ONDER) != null )
					aantalBuren++;
			}
			else
			{
				// kijken of we aan de tegel rechtsboven kunnen komen via boven
				if( bovenBuur != null )
					if( bovenBuur.getBuur(RICHTING.RECHTS) != null )
						aantalBuren++;
				
				// kijken of we aan de tegel rechtsonder kunnen komen via onder
				if( onderBuur != null )
					if( onderBuur.getBuur(RICHTING.RECHTS) != null )
						aantalBuren++;
			}
			
			return aantalBuren;
			
		}
		else // gras
		{
			// punten per AFGEWERKT KASTEEL dat grenst aan gras...
			//  TODO : berekenen van graspunten
			// mogelijk algoritme : alle tegels in gebied afgaan en kijken welke een stuk kasteel op zich hebben.
			// daar braaf de kastelen berekenen, en zo weten we welke volledig zijn.
			
			return gebied.getTegels().size();
		}
	}
	
	/*
	 * Functie geeft -1 terug als de speler niet het meeste pionnen heeft, 0 als er 2 of meer spelers gelijk aantal hebben
	 * en 1 als de speler het meeste pionnen heeft.
	 */
	private int spelerHeeftMeestePionnen(Gebied gebied, Speler speler)
	{
		HashMap<Speler, Integer> pionnenPerSpeler = getPionnenPerSpeler(gebied);
		int besteAantal = 0;
		
		int spelerAantal = pionnenPerSpeler.get(speler);
		
		Set<Speler> spelers = pionnenPerSpeler.keySet();
		for( Speler spelerIt : spelers )
		{
			if( pionnenPerSpeler.get(spelerIt) > besteAantal )
			{
				besteAantal = pionnenPerSpeler.get(spelerIt);
			}
		}
		
		if( besteAantal == spelerAantal  )
			return 0;
		if( besteAantal > spelerAantal )
			return -1;
		else // speler heeft wel beste
			return 1;
	}
	
	private HashMap<Speler, Integer> getPionnenPerSpeler(Gebied gebied)
	{
		HashMap<Speler, Integer> output = new HashMap<Speler, Integer>();
		
		Set<Pion> pionnen = gebied.getPionnen();
		for( Pion pion : pionnen )
		{
			if( output.get( pion.getSpeler() ) == null )
				output.put(pion.getSpeler(), 1);
			else
				output.put(pion.getSpeler(), output.get(pion.getSpeler()) + 1);
		}
		
		return output;
	}

}
