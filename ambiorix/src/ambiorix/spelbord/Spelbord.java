package ambiorix.spelbord;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import ambiorix.util.Punt;


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
	
	
	private HashMap<Punt, Tegel> tegelCoordinaten = new HashMap<Punt, Tegel>();
	
	
	private Tegel beginTegel = null;
	private int volgendeTegelID = 1;
	
	public Spelbord(Tegel beginTegel)
	{
		this.beginTegel = beginTegel;
		this.beginTegel.setID(0);
		
		tegelCoordinaten.put(new Punt(0,0), beginTegel);
	}
	
	public void setBeginTegel( Tegel beginTegel )
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
	
	/*
	 * Geeft een pointer naar de volgende tegel, numerieke ID en TegelType juist ingesteld.
	 * Geeft null als er geen tegels meer over zijn (spel is gedaan)
	 */
	public Tegel getVolgendeTegel()
	{		
		if(this.beginTegel == null)
		{
			// TODO : throw exception
			System.out.println( "Spelbord::getVolgendeTegel : Begintegel is nog niet gezet!!!" );
			return;
		}
		
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
	
	
	
}
