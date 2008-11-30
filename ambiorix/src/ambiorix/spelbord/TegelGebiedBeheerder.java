package ambiorix.spelbord;

import ambiorix.spelbord.Tegel.RICHTING;
import ambiorix.util.Punt;
import ambiorix.util.PuntMap;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public class TegelGebiedBeheerder 
{
	private Tegel tegel = null;
	
	private PuntMap<Terrein> GebiedHelpersBOVEN;
	private PuntMap<Terrein> GebiedHelpersRECHTS;
	private PuntMap<Terrein> GebiedHelpersONDER;
	private PuntMap<Terrein> GebiedHelpersLINKS;	
	
	public TegelGebiedBeheerder(Tegel tegel) 
	{
		this.tegel = tegel;
		
		GebiedHelpersBOVEN = new PuntMap<Terrein>();
		GebiedHelpersRECHTS = new PuntMap<Terrein>();
		GebiedHelpersONDER = new PuntMap<Terrein>();
		GebiedHelpersLINKS = new PuntMap<Terrein>();
		
		// 1 nodig per vakje aan buitenkant van terrein.
		// 1 extra nodig voor elke hoek ( + 4 dus ) want die wijzen naar beide kanten
		// voor elke kant een aparte lijst bijhouden is het beste
		
		// voor elk punt in het terrein pointer bijhouden naar aangrenzend punt in de buurtegel
		
		int aantalKolommen = tegel.getTerreinBreedte();
		int aantalRijen = tegel.getTerreinHoogte();
		
		for( int i = 0; i < aantalKolommen; i++ )
		{
			// boven is rij 0, kolom i
			// deze linken naar die ONDER de buurtegel bovenaan
			GebiedHelpersBOVEN.put( new Punt(0,i), new Terrein(null, new Punt(aantalRijen - 1, i) ) );
			
			// onder is rij aantalRijen - 1, kolom i
			GebiedHelpersONDER.put(new Punt(aantalRijen - 1, i), new Terrein(null, new Punt(0, i) ) );
			
			// rechts is i, aantalKolommen - 1
			GebiedHelpersRECHTS.put(new Punt(i, aantalKolommen - 1), new Terrein(null, new Punt(i, 0) ) );			
			
			// links is i, 0
			GebiedHelpersLINKS.put(new Punt(i,0), new Terrein(null, new Punt(i, aantalKolommen - 1) ) );
		}
	}
	
		public PuntMap<Terrein> getGebiedHelpers(Tegel.RICHTING richting)
		{
			PuntMap<Terrein> verzameling = null;
			
			if( richting == Tegel.RICHTING.BOVEN)
				verzameling = GebiedHelpersBOVEN;
			else if( richting == Tegel.RICHTING.ONDER )
				verzameling = GebiedHelpersONDER;
			else if( richting == Tegel.RICHTING.RECHTS )
				verzameling = GebiedHelpersRECHTS;
			else if( richting == Tegel.RICHTING.LINKS )
				verzameling = GebiedHelpersLINKS;
			
			return verzameling;		
		}
	
	public boolean controleerOvereenkomstigeZijden(TegelGebiedBeheerder buur, RICHTING richting)
	{
		// Elk punt in deze zijde heeft een equivalent aan de andere kant op de buur GebiedHelper.
		// Dat equivalent zoeken we op op terreinType om zo de overeenkomsten te bepalen
		PuntMap<Terrein> huidigeZijde = getGebiedHelpers(richting);
		
		// alle punten van de zijde overlopen
		Set<Punt> punten = huidigeZijde.keySet();
		
		TerreinType huidigTerrein = null;
		TerreinType buurTerrein = null;
		Punt buurPunt = null;
		
		for( Punt huidigPunt: punten )
		{
			huidigTerrein = this.tegel.getTerreinType(huidigPunt);
			
			buurPunt = huidigeZijde.get(huidigPunt).getPositie();
			buurTerrein = buur.tegel.getTerreinType(buurPunt);
			
			if( huidigTerrein != buurTerrein )
				return false;
		}			
		
		return true;
	}
		
	public void setBuur(Tegel buur, RICHTING richting)
	{
		
		// moeten zelf niet verder in de boom van tegels zoeken, daar zorgt tegel wel voor

		PuntMap<Terrein> verzameling = getGebiedHelpers(richting);
		Set<Punt> punten = verzameling.keySet();
		
		for( Punt punt: punten )
		{
			verzameling.get(punt).setTegel(buur);
		}	
	}
	
	public Vector<Terrein> getGebied(Terrein start)
	{
		Vector<Terrein> gebied = new Vector<Terrein>();
		Vector<Tegel> gesloten = new Vector<Tegel>();
		
		//rondom het startterrein zoeken naar aangrenzende stukjes terrein van hetzelfde type
		berekenGebied(gebied, start, gesloten);
		
		return gebied;
	}
	
		private void berekenGebied( Vector<Terrein> gebied, Terrein start, Vector<Tegel> gesloten )
		{
			
			// eerst alles binnen zelfde tegel doen. Dan op aangrenzende tegels verdergaan.
			// binnen :
			// - beweeg zover het gaat naar de kant
			// - als we op de kant zitten moeten we op de tegel aan die kant verdergaan
			//	  , anders gewoon stoppen
			
			Tegel tegel = start.getTegel();
			
			// TODO : eventueel dit nog naar onderen brengen, 1 functieaanroep minder per keer
			if( !gesloten.contains(tegel) )
				gesloten.add(tegel);
			else
				return;
			
			TerreinType gebiedType = tegel.getTerreinType( start.getPositie() );
			
			Punt punt = null; //new Punt( start.getPositie() );
			boolean doorgaan = true;
			boolean doorgaanNaarVolgende = true;
			
			int x = 0;
			int y = 0;
			int xincrement = 0;
			int yincrement = 0;
			
			for( Tegel.RICHTING richting: Tegel.RICHTING.values() )
			{
				//System.out.println( "We gaan kijken in richting " + richting.toString() );
				
				doorgaan = true;
				doorgaanNaarVolgende = true;
				punt = new Punt( start.getPositie() );
				
				// richting van beweging bepalen
				if(richting == Tegel.RICHTING.LINKS)
				{
					xincrement = 0;
					yincrement = -1;
				}
				else if(richting == Tegel.RICHTING.RECHTS)
				{
					xincrement = 0;
					yincrement = 1;
				}
				else if(richting == Tegel.RICHTING.BOVEN)
				{
					xincrement = -1;
					yincrement = 0;
				}
				else if(richting == Tegel.RICHTING.ONDER)
				{
					xincrement = 1;
					yincrement = 0;
				}
				else
				{
					System.out.println("IETS SERIEUS MIS");
					return;
				}
				
				
				while(doorgaan)
				{
					// gebied is van 1 en hetzelfde type
					// als er een punt gevonden wordt dat niet van het type is = einde gebied daar
					if( tegel.getTerreinType(punt) == gebiedType )
					{
						// gevonden Terrein aan het gebied toevoegen
						gebied.add( new Terrein(tegel, new Punt(punt)) );
						
						// punt klaarzetten voor volgende iteratie
						punt.setX( punt.getX() + xincrement );
						punt.setY( punt.getY() + yincrement );
						
						x = punt.getX();
						y = punt.getY();
						
						if( (x < 0) || (x > tegel.getTerreinBreedte() - 1) )
						{
							doorgaan = false;
							doorgaanNaarVolgende = true;
						}
						if( (y < 0) || (y > tegel.getTerreinHoogte() - 1) )
						{
							doorgaan = false;
							doorgaanNaarVolgende = true;
						}
						
						//System.out.println( tegel.getID() + " - " + punt.getX() + "," + punt.getY() );
					}
					else
						doorgaan = false;
				}
				
				if(doorgaanNaarVolgende)
				{
					punt.setX( punt.getX() - xincrement );
					punt.setY( punt.getY() - yincrement );	
					
					Terrein buur = tegel.getGebiedBeheerder().getGebiedHelpers(richting).get(punt);
					
					if(buur.getTegel() != null)
					{
						//System.out.println( "volgende : " + buur.getTegel().getID() + " - " + punt.getX() + "," + punt.getY() );
						berekenGebied( gebied, buur, gesloten );		
					}
				}
			}
			
			
			// voorbeeld voor links
			/*while( (tijdelijk.getY() >= 0)  && doorgaan)
			{
				if( tegel.getTerreinType(tijdelijk) == gebiedType )
				{
					gebied.add( new Terrein(tegel, new Punt(tijdelijk)) );
					tijdelijk.setY( tijdelijk.getY() - 1 );
				}
				else
					doorgaan = false;
			}
			
			if( doorgaan )
			{
				tijdelijk.setY( tijdelijk.getY() + 1 ); // staat nu op -1 normaal gezien
				
				// nieuw startpunt berkenen op tegel ernaast
				Terrein buur = tegel.getGebiedBeheerder().getGebiedHelpers(Tegel.RICHTING.LINKS).get(tijdelijk);
				
				if(buur.getTegel() == null)
					return;
				
				berekenGebied( gebied, buur );
			}*/
		}
	
	public void print()
	{
		System.out.println("GebiedBeheerder::Print voor tegel " + tegel.getID());
		printRichting( Tegel.RICHTING.BOVEN );
		printRichting( Tegel.RICHTING.ONDER );
		printRichting( Tegel.RICHTING.RECHTS );
		printRichting( Tegel.RICHTING.LINKS );
	}
	
		private void printRichting( Tegel.RICHTING richting )
		{
			System.out.println("TegelGebiedBeheerder::printRichting " + richting.toString());
			
			PuntMap<Terrein> verzameling = getGebiedHelpers(richting);
			
			Set<Punt> keys = verzameling.keySet();
			
			for( Punt lokaalPunt: keys)
			{
				if(lokaalPunt != null)
				{
					System.out.print( lokaalPunt.getX() + "," + lokaalPunt.getY() + " -> " );
					
					Terrein helper = (Terrein) verzameling.get(lokaalPunt);
					if( helper != null)
					{
						System.out.print( helper.getPositie().getX() + "," + helper.getPositie().getY() + " = ");
						if(helper.getTegel() == null)
							System.out.println("LEEG");
						else
							System.out.println( helper.getTegel().getID() );
					}
					else
						System.out.println("ERROR");
				}
				else
					System.out.println("punt ERROR");
			}
		}
	
}
