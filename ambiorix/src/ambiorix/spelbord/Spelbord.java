package ambiorix.spelbord;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import ambiorix.util.Punt;
import ambiorix.util.PuntMap;


/*
 * FLOW of CONTROL voor tegel plaatsen !
 * - getVolgendeTegel() wordt aan speler gegeven
 * - de GUI weet naast welke TEGEL de speler de nieuwe wil leggen, dus gaat rechtstreeks via TEGEL deze plaatsen (niet via spelbord)
 */

public class Spelbord 
{
	// houdt per tegeltype bij hoeveel er nog van in de "pool" zitten
	private HashMap<String,Integer> overgeblevenTegels = new HashMap<String,Integer>();
	// som van alle tegels die nog over zijn. Als deze op 0 staat is de pool leeg, en is het spel gedaan
	private int overgeblevenTegelAantal = 0;
	
	
	private PuntMap<Tegel> tegelCoordinaten = new PuntMap<Tegel>();
	
	
	private Tegel beginTegel = null;
	private int volgendeTegelID = 1;
	
	private Tegel laatstGeplaatsteTegel = null;
	
	private HashMap<Pion,Terrein> pionnen = new HashMap<Pion, Terrein>();
	
	public Spelbord()
	{
		/*this.beginTegel = beginTegel;
		this.beginTegel.setID(0);
		
		tegelCoordinaten.put(new Punt(0,0), beginTegel);*/
	}
	
	public void setBegintegel( Tegel beginTegel )
	{
		if(this.beginTegel != null)
		{
			// TODO : throw exception
			System.out.println( "Spelbord::setBeginTegel : Begintegel is al gezet!!!" );
			return;
		}
		
		this.beginTegel = beginTegel;
		this.beginTegel.setID(0);
		
		tegelCoordinaten.put(new Punt(0,0), beginTegel);		
	}
	
	private void assertBegintegel()
	{
		if(this.beginTegel == null)
		{
			// TODO : throw exception
			System.out.println( "Spelbord::getVolgendeTegel : Begintegel is nog niet gezet!!!" );
			return;
		}		
	}
	
	/*
	 * Geeft een pointer naar de volgende tegel, numerieke ID en TegelType juist ingesteld.
	 * Geeft null als er geen tegels meer over zijn (spel is gedaan)
	 */
	public Tegel getVolgendeTegel()
	{		
		assertBegintegel();
		
		TegelType type = getRandomTegelType();
		if(type == null) // geen tegels meer in de pool
			return null;
		
		Tegel tegel = new Tegel(type);
		tegel.setID( getVolgendeTegelID() );
		
		return tegel;
		
	}
	
		private int getVolgendeTegelID()
		{
			volgendeTegelID++;
			return (volgendeTegelID - 1);
		}
		
		private TegelType getRandomTegelType()
		{
			if(overgeblevenTegelAantal == 0)
				return null;
				
			Random generator = new Random();
			
			String gevondenType = null;
			
			while(gevondenType == null)
			{
				// we zoeken een random tegelType
				Set<String> tegelTypes = overgeblevenTegels.keySet();
				
				int randType = generator.nextInt( tegelTypes.size() );
				String tegelType = (String) tegelTypes.toArray()[ randType ];
				
				if( overgeblevenTegels.get(tegelType) > 0)
					gevondenType = tegelType;
			}
			
			overgeblevenTegelAantal--;
			return TegelTypeVerzameling.getInstantie().getType(gevondenType);
			
		}
	
	public void setTegelAantal(String tegelType, int hoeveelheid)
	{
		if( overgeblevenTegels.containsKey(tegelType) )
		{
			// zit er al in, we gaan het aantal veranderen.
			// eerst het aantal dat al in de teller stak aanpassen !!!
			overgeblevenTegelAantal -= overgeblevenTegels.get(tegelType);
		}
		
		overgeblevenTegels.put(tegelType, hoeveelheid);
		overgeblevenTegelAantal += hoeveelheid;
	}
	
	public int getTegelAantal(String tegelType)
	{
		return overgeblevenTegels.get(tegelType);
	}
	
	/*
	 * Deze functie controleert of tegel plaatsbaar is naast buur, in de opgegeven richting.
	 * Ze gaat echter nog verder en controleert ook meteen de andere mogelijke buren van de nieuwe tegel
	 * of hij daar wel aan kan grenzen.
	 * 
	 * 1x aanroepen van deze functie is dus genoeg.
	 */
	public boolean controleerPlaatsbaarheid( Tegel tegel, BordPositie positie )
	{	
		Tegel buur = positie.getBuur();
		Tegel.RICHTING richting = positie.getRichting();
		
		Punt buurCoordinaat = getTegelCoordinaat(buur);
		Punt nieuweCoordinaat = getAangrenzendeCoordinaat( buurCoordinaat, richting );		

		// staat al een tegel daar, zeker niet plaatsbaar dus
		if( tegelCoordinaten.get(nieuweCoordinaat) != null )
			return false;
		
		// alle buren afgaan en kijken of tegel kan gezet worden
		for( Tegel.RICHTING r : Tegel.RICHTING.values() )
		{
			buurCoordinaat = getAangrenzendeCoordinaat(nieuweCoordinaat, r);
			buur = tegelCoordinaten.get(buurCoordinaat);
			
			if(buur != null)
				if( !buur.kanBuurAccepteren(tegel, r.getTegenovergestelde()) )
					return false;
		}		
		
		return true;
	}
	
	/*
	 * Deze functie controleer of een pion plaatsbaar is op terrein door het gebied rondom terrein te berekenen
	 * en te zien of er nog geen andere pionnen aanwezig zijn.
	 */
	public boolean controleerPlaatsbaarheid( Pion pion, Terrein terrein )
	{
		// TODO : berekening gebied aanpassen zodat hij stopt op 1 pion gevonden !
		boolean output = true;
		
		Gebied g = getGebied(terrein);
		
		output = (g.getPionnen().keySet().size() == 0);
		
		return output;
	}
	
	/*
	 * Geeft de laatste succesvol geplaatste tegel terug.
	 * 
	 */
	public Tegel getLaatstGeplaatsteTegel()
	{
		return laatstGeplaatsteTegel;
	}
	
	/*
	 * Deze functie gaat controleren of een tegel ERGENS op het bord kan geplaatst worden.
	 * Als dit zo is, is output.size() != 0
	 * 
	 * Hij gaat alle tegels afgaan, in de 4 richtingen kijken of er al een buur staat.
	 * Is dit niet zo controleert hij de plaatsbaarheid van de tegel. Geeft dit true terug, wordt de BordPositie toegevoegd
	 * aan de output.
	 * 
	 * ALs stopDirect == false zal hij een lijst teruggeven van alle mogelijke posities waar de tegel kan gelegd worden.
	 *    = trager => maar handig voor de AI
	 * Als stopDirect == true zal hij enkel de eerste die hij vind teruggeven en dan stoppen.
	 *    = snel => om een voorgestelde tegel te checken
	 * 
	 */
	public Vector<BordPositie> controleerGlobalePlaatsbaarheid(Tegel tegel, boolean stopDirect)
	{
		// tegelCoordinaten geeft ons pointers naar elke tegel die geplaatst is.
		// deze tegels allemaal afgaan en aan elke zijde checken of de nieuwe tegel plaatsbaar is.
		
		Collection<Tegel> tegels = tegelCoordinaten.values();
		Vector<BordPositie> output = new Vector<BordPositie>();
		BordPositie pos = null;
		
		for(Tegel t: tegels)
		{
			for(Tegel.RICHTING richting: Tegel.RICHTING.values() )
			{
				if( t.getBuur(richting) == null )
				{
					pos = new BordPositie(t,richting);
					if( controleerPlaatsbaarheid(tegel, pos ) )
					{
						output.add(pos);
						
						if( stopDirect )
							return output;
					}
					// else kan de tegel hier niet gezet worden, dus overslaan
				}
				// else is er al een buur daar, kan zeker niet
			}
		}
		
		return output;
	}
	
	/*
	 * Deze functie verwijdert de tegel van het spelbord, en past ook de buren aan.
	 * 
	 */
	public void verwijderTegel(Tegel tegel)
	{
		// ook verwijderen uit coordinatentabel !!!
		Punt positie = tegelCoordinaten.getKey(tegel);
		if(positie == null)
		{
			// TODO exception
			System.out.println("Spelbord::verwijderTegel : Kan tegel niet vinden op het spelbord !");
			return;
		}
		
		
		Punt buurPositie = null;
		Tegel buur = null;
		
		// de buren updaten zodat ze geen verwijzingen meer hebben naar de verwijderde tegel
		for( Tegel.RICHTING richting : Tegel.RICHTING.values() )
		{
			buurPositie = getAangrenzendeCoordinaat(positie, richting);
			
			if(buurPositie != null)
			{
				buur = tegelCoordinaten.get(buurPositie);
				
				if(buur != null)
				{
					buur.verwijderBuur(richting.getTegenovergestelde());
				}
				else
				{
					// TODO : Exception
					System.out.println("Spelbord::verwijderTegel : Kan tegel op buurpositie niet vinden !");
					return;
				}
			}
			// else is er daar geen buur, dus moeten we ook niks laten weten
		}
	}
	
	/*
	 * Deze functie plaatst een pion op het gegeven terrein
	 * 
	 */
	public void plaatsPion(Pion pion, Terrein terrein)
	{
		// TODO : indices check ?
		terrein.getTegel().plaatsPion(terrein.getPositie(), pion);
		pionnen.put(pion, terrein);
	}
	
	/*
	 * verwijdert een pion van de opgegeven positie, als daar eentje staat tenminste.
	 * 
	 * OPM : dit verwijdert niet het pion-object, slechts de verwijzing ernaar op het spelbord.
	 * 
	 */
	public void verwijderPion(Terrein positie)
	{
		pionnen.remove(positie.getTegel().getPion(positie.getPositie()));
		positie.getTegel().verwijderPion(positie.getPositie());
	}
	
	/*
	 * Zoekt waar de pion staat en verwijdert deze dan van het spelbord.
	 * Deze functie is traag => Gebruikt verwijderPion(Terrein positie)!!!
	 * 
	 * OPM : zoekt maar tot hij de pion 1x gevonden heeft. Als meermaals dezelfde pion op het spelbord staat
	 * , zal enkel de eerste verwijzing ongedaan worden gemaakt !
	 */
	public void verwijderPion(Pion pion)
	{
		Terrein positie = pionnen.get(pion);
		if(positie != null)
			verwijderPion(positie);
	}
	
	/*
	 * Deze functie gaat tegel toevoegen aan het spelbord, naast de gespecifieerde buur.
	 * De richting geeft aan hoe de nieuwe tegel ligt tov de buur.
	 * richting == BOVEN betekent bijv. dat de nieuwe tegel BOVEN de buur moet geplaatst worden.
	 * 
	 * De functie zal ervoor zorgen dat de correcte tegels onderling deze tegel als buur gaan instellen.
	 * De functie moet dus maar 1x aangeroepen worden per nieuwe tegel.
	 * 
	 * De functie gaat niet zelf controleren of de plaatsing wel geldig is. Gebruik hiervoor Spelbord::controleerPlaatsbaarheid
	 */
	public void plaatsTegel( Tegel tegel, BordPositie positie)
	{
		Tegel buur = positie.getBuur();
		Tegel.RICHTING richting = positie.getRichting();
		
		// eerst coordinaat van de nieuwe tegel zoeken, op basis van de buur
		Punt buurCoordinaat = getTegelCoordinaat(buur);
		Punt nieuweCoordinaat = getAangrenzendeCoordinaat( buurCoordinaat, richting );
		
		// TODO : wat als daar al een tegel staat ???
		tegelCoordinaten.put(nieuweCoordinaat, tegel);
		
		
		// als tegel geen ID heeft (bijv. bij toevoegen vanuit file etc.)
		// gaan we hem een ID moeten toekennen
		if( tegel.getID() == -1)
			tegel.setID( getVolgendeTegelID() );
		
		
		laatstGeplaatsteTegel = tegel;
		
		// nu we de coordinaat kennen, de buren errond laten weten dat de nieuwe tegel gezet is
		for( Tegel.RICHTING r : Tegel.RICHTING.values() )
		{
			buurCoordinaat = getAangrenzendeCoordinaat(nieuweCoordinaat, r);
			buur = tegelCoordinaten.get(buurCoordinaat);
			
			if( buur != null )
			{
				tegel.setBuur(buur, r);
				buur.setBuur(tegel, r.getTegenovergestelde() );
			}
			else
			{
				// TODO : remove
				//System.out.println(" spelbord::PlaatsTegel : geen buur gevonden op " + buurCoordinaat);
			}
		}
	}
		// zoekt coordinaat in lijst van geplaatste tegels.
		// ENKEL gebruiken voor geplaatste tegels dus !
		private Punt getTegelCoordinaat(Tegel tegel)
		{
			// eerst controleren of de tegel wel een coordinaat heeft
			if( !tegelCoordinaten.containsValue(tegel))
			{
				// TODO : exception
				System.out.println("Spelbord::getTegelCoordinaat : tegel heeft nog geen coordinaat");
				return null;
			}
			
			Set<Punt> punten = tegelCoordinaten.keySet();
			
			for(Punt punt: punten)
			{
				if( ((Tegel) tegelCoordinaten.get(punt)) ==  tegel)
					return punt;
			}

			System.out.println("Spelbord::getTegelCoordinaat : tegel heeft nog geen coordinaat 2");
			return null;
		}
		
		// Werkt als een matrix, rijen en kolommen
		private Punt getAangrenzendeCoordinaat( Punt coordinaat, Tegel.RICHTING richting )
		{
			if( richting == Tegel.RICHTING.BOVEN )
				return new Punt( coordinaat.getX() - 1, coordinaat.getY() ); 
			
			if( richting == Tegel.RICHTING.ONDER )
				return new Punt( coordinaat.getX() + 1, coordinaat.getY() ); 
			
			if( richting == Tegel.RICHTING.RECHTS )
				return new Punt( coordinaat.getX(), coordinaat.getY() + 1 ); 
			
			if( richting == Tegel.RICHTING.LINKS )
				return new Punt( coordinaat.getX(), coordinaat.getY() - 1 ); 
				
			
			return null;
		}
	
	/*
	 * Gaat recursief rondom start zoeken voor terreinstukken van hetzelfde type.
	 * 
	 * TODO : gebied ook nog laten bijhouden waar het nog niet "af is" (Tegel + zijde) ?
	 * 
	 */
	public Gebied getGebied(Terrein start)
	{
		Gebied gebied = new Gebied();
		
		gebied.setType(start.getTegel().getTerreinType(start.getPositie()) );
		
		// vanaf startpunt gaan we "recursief" alle Terreintjes zoeken
		gebied = start.getTegel().getGebied(start);
		
		return gebied;
	}
	
	public String toXML()
	{
		String output = "<spelbord>";
		
		output += "<beginTegel>" + this.beginTegel.getID() + "</beginTegel>";
		
		// niet echt nodig, meer ter controle
		output += "<volgendeTegelID>" + this.volgendeTegelID + "</volgendeTegelID>";
		
		output += "<overgeblevenTegels>";
		
			// de tegelpool moeten we ook opslaan natuurlijk
			Set<String> tegelTypes = this.overgeblevenTegels.keySet();
			for( String tegelType : tegelTypes )
			{
				output += "<reeks>";
				
				output += "<type>"   + tegelType 								+ "</type>";
				output += "<aantal>" + this.overgeblevenTegels.get(tegelType) 	+ "</aantal>";
				
				output += "</reeks>";
			}
		
		output += "</overgeblevenTegels>";
			
		output += "<tegels>";
			
			Collection<Tegel> tegels = tegelCoordinaten.values();
			for( Tegel tegel : tegels )
			{
				output += tegel.toXML();
			}
		
		output += "</tegels>";
		
		
		output += "</spelbord>";
		
		return output;
	}
	
	public void fromXML(String input)
	{
		try
		{
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        factory.setValidating(true);
	        factory.setNamespaceAware(false);
	
	
	
	        DocumentBuilder builder = factory.newDocumentBuilder();
	
	        Document doc = builder.parse(input);

		}catch (Exception e) {e.printStackTrace();}
		
	}
	
}
