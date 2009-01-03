package ambiorix.spelbord.scoreberekenaars;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.Terrein;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelbord.TegelBasis.RICHTING;
import ambiorix.spelers.Speler;

public class SimpelScoreBerekenaar implements ScoreBerekenaar 
{
	public boolean eindeSpel;

	public SimpelScoreBerekenaar()
	{
		
	}
	
	@Override	
	public void zetEindeSpel(boolean eindeSpel)
	{
		this.eindeSpel = eindeSpel;
	}
	
	@Override	
	public boolean isEindeSpel()
	{
		return eindeSpel;
	}
	
	@Override
	public int berekenScore(Gebied gebied, Speler speler) 
	{
		// KORTE OPMERKING :
		// KLOOSTER-gebieden zijn ALTIJD volledig als er een tegel met een klooster is geplaatst.
		// De berekening isVolledig kijkt immers NIET of er 9 tegels rondliggen!!! (ander concept)
		// hier dus rekening mee houden !
		
		if( !eindeSpel )
		{
			// tijdens het spel krijg je enkel punten voor VOLLEDIGE gebieden
			if( !gebied.isVolledig() )
				return 0;
			
			// na het spel krijg je ook punten voor onafgewerkte gebieden
		}
				
		// als er geen pionnen op staan, kunnen we ook geen score hebben
		if( gebied.getPionnen().size() == 0 )
			return 0;
				
		int spelerHeeftMeestePionnen = spelerHeeftMeestePionnen(gebied,speler);
		if( spelerHeeftMeestePionnen == -1 )
			return 0;
		
		
		if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg") )
		{
			// aantal tegels waaruit de weg bestaat is meteen ook de score
			// bij eindeSpel is dit nog altijd zo, dus geen aanpassingen nodig
			return gebied.getTegels().size();
		}
		else if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Burcht") )
		{
			// kasteel is (tegels * 2) + (schilden * 2) als volledig
			// als niet volledig bij eindeSpel : * 1
			
			int vermenigvuldiger = 2;
			
			if( eindeSpel )
			{
				if( !gebied.isVolledig() ) // anders blijft de score *2 natuurlijk
					vermenigvuldiger = 1;
			}
			
			Vector<Tegel> tegels = gebied.getTegels();
			int result = vermenigvuldiger * tegels.size();
			
			for( Tegel tegel : tegels )
			{
				// enkel die heeft voorlopig schildjes mogelijk
				// als er nog andere zouden bijkomen moeten we daar hier gewoon ook op checken
				if( tegel.getType() == TegelTypeVerzameling.getInstantie().getType("TegelType_BBBBB_MetSchild") )
				{
					result += vermenigvuldiger;
				}
			}
			
			return result;
		}
		else if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Klooster") )
		{
			// klooster volledig omringd is 9 punten
			// anders 1 punt per tegel errond
			// dat dan nog eens + 1
			// => gebied is altijd afgemaakt natuurlijk, we moeten de tegel gaan controleren op zijn buren !
			
			Tegel tegel = gebied.getTegels().get(0);
			
			int aantalBuren = 1;
			
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
			
			if( !eindeSpel )
			{
				// enkel punten als er 8 tegels rond liggen
				// checken op 9 want aantalBuren begint op 1 (dan is het automatisch gelijk aan het aantal punten)
				if( aantalBuren != 9 )
					return 0;
			}
			
			return aantalBuren;
		}
		else if( gebied.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras") )
		{
			// OPM : we komen hier enkel bij eindeSpel !
			
			// punten per AFGEWERKT KASTEEL dat grenst aan gras...
			// mogelijk algoritme : alle tegels in gebied afgaan en kijken welke een stuk kasteel op zich hebben.
			// daar braaf de kastelen berekenen, en zo weten we welke volledig zijn.
			
			Vector<Tegel> tegels = gebied.getTegels();
			Vector<Gebied> gevondenSteden = new Vector<Gebied>();
			
			/*
			 * De boeren verzorgen enkel afgewerkte steden langs
				hun weiland. Voor elke afgewerkte stad krijgt de speler met de meeste boeren
				in dat weiland 4 punten.
			*/
			
			int result = 0;
			for( Tegel tegel : tegels )
			{
				//TODO : REKENING HOUDEN MET MEERDERE STEDEN-STUKKEN OP 1 TEGEL !!!!
				// hoewel dit maar HEEEEL zelden zal voorkomen, dus laten we dit momenteel EVENTJES buiten beschouwing
				
				Terrein burchtStart = tegel.getTerreinVanType( TerreinTypeVerzameling.getInstantie().getType("TerreinType_Burcht") );
				if( burchtStart != null ) // anders geen Burcht op deze tegel
				{
					// eersrt kijken of het Terrein nog geen stukje is van een reeds gevonden burcht
					for( Gebied burchtGebied : gevondenSteden )
					{
						for( Tegel burchtTegel : burchtGebied.getTegels() )
						{
							if( burchtTegel == burchtStart.getTegel() )
								continue;
						}
					}
					
					Gebied burcht = tegel.getGebied(burchtStart);
					gevondenSteden.add(burcht);
					
					if( burcht.isVolledig() )
						result += 4;
				}	
			}
			
			return result;
		}
		else
			return 0;
	}
	
	/*
	 * Functie geeft -1 terug als de speler niet het meeste pionnen heeft, 0 als er 2 of meer spelers gelijk aantal hebben
	 * en 1 als de speler het meeste pionnen heeft.
	 */
	private int spelerHeeftMeestePionnen(Gebied gebied, Speler speler)
	{
		HashMap<Speler, Integer> pionnenPerSpeler = getPionnenPerSpeler(gebied);
		int besteAantal = 0;
		
		int spelerAantal = -1;
		
		if( pionnenPerSpeler.get(speler) != null )
			spelerAantal = pionnenPerSpeler.get(speler);
		
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
