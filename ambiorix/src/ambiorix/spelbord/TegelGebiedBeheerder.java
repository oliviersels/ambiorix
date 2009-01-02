package ambiorix.spelbord;

import ambiorix.spelbord.TegelBasis.RICHTING;
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
			
			if( huidigTerrein == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Wildcard") )
				continue;
			
			if( (huidigTerrein != buurTerrein) && (buurTerrein != TerreinTypeVerzameling.getInstantie().getType("TerreinType_Wildcard")) )
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
	
	public void verwijderBuur(RICHTING richting)
	{
		PuntMap<Terrein> verzameling = getGebiedHelpers(richting);
		Set<Punt> punten = verzameling.keySet();
		
		for(Punt p: punten)
		{
			verzameling.get(p).setTegel(null);
		}
	}
	
	public Gebied getBeperktGebied(Terrein start)
	{
		Gebied gebied = new Gebied();
		gebied.setType(start.getType());
		
		Vector<String> gesloten = new Vector<String>();
		
		//rondom het startterrein zoeken naar aangrenzende stukjes terrein van hetzelfde type
		berekenGebied(gebied, start, gesloten, true);
		
		return gebied;		
	}
	
	public Gebied getGebied(Terrein start)
	{
		Gebied gebied = new Gebied();
		gebied.setType(start.getType());
		
		Vector<String> gesloten = new Vector<String>();
		
		//rondom het startterrein zoeken naar aangrenzende stukjes terrein van hetzelfde type
		berekenGebied(gebied, start, gesloten, false);
		
		return gebied;
	}
	
		/*
		 * Helpfunctie die recursief het gebied gaat berekenen.
		 */
		// TODO : gesloten (de check op dubbele terreinen) naar gebied brengen, niet in functie houden
		private void berekenGebied( Gebied gebied, Terrein start, Vector<String> gesloten, boolean beperkTotTegel )
		{
			// we gaan ervanuit dat START nog niet in het gebied zit
			// - eerst kijken of hij erbij moet
			//  - JA : toevoegen 
			//       : in 4 richtingen rondom kijken en algoritme terug aanroepen
			//  - NEE: stop
			
			Tegel tegel = start.getTegel();
			Pion pion = null;
			TerreinType gebiedType = gebied.getType();
			
			//System.out.print("Kandidaat : " + start.getTegel().getID() + " - " + start.getPositie().getX() + "," + start.getPositie().getY() );
			//System.out.println(" -> " + tegel.getTerreinType(start.getPositie()).getID() + " en " + gebiedType.getID() );
			
			// TODO : deze check naar beneden, aannemen dat start geldig is (en eventueel al in gebied zit)
			if( tegel.getTerreinType(start.getPositie()) == gebiedType )
			{
				// gevonden Terrein aan het gebied toevoegen
				Terrein nieuw = new Terrein( tegel, new Punt(start.getPositie()) );
				
				// anders moeten we niet meer toevoegen!!!
				if( !gesloten.contains(nieuw.toString()) )
				{
					gebied.voegTerreinToe( nieuw );
					gesloten.add( nieuw.toString() );
					
					// kijken of er een pion opstaat. If so : toevoegen aan gebied
					if( (pion = tegel.getPion(start.getPositie())) != null )
						gebied.voegPionToe(pion, new Terrein( tegel, new Punt(start.getPositie()) ) );
					
					//System.out.println("Toegevoegd : " + nieuw.getTegel().getID() + " - " + nieuw.getPositie().getX() + "," + nieuw.getPositie().getY() );
					
					// variabelen voor rondom in 4 richtingen kijken
					Punt punt = null;
					boolean doorgaanNaarVolgende = true;
					
					int x = 0;
					int y = 0;
					int xincrement = 0;
					int yincrement = 0;
					
					
					// in de 4 richtingen rondom start kijken voor nieuwe stukjes in het gebied
					for( Tegel.RICHTING richting: Tegel.RICHTING.values() )
					{
						//System.out.println( "We gaan kijken in richting " + richting.toString() );
						
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
						
						punt.setX( punt.getX() + xincrement );
						punt.setY( punt.getY() + yincrement );
						
						x = punt.getX();
						y = punt.getY();
						
						
						// nu is er in die richting
						// - ofwel een nieuw vakje op dezelfde tegel
						// - ofwel een nieuw vakje op een andere tegel
						// - ofwel geen vakje meer		
						
						//System.out.println("Kijk naar : " + tegel.getID() + " - " + punt.getX() + "," + punt.getY() );
						
						// op volgende tegel, boven en onder
						if( (x < 0) || (x > tegel.getTerreinHoogte() - 1) )
						{
							//doorgaan = false;
							doorgaanNaarVolgende = true;
						}
						// op volgende tegel, links en rechts
						else if( (y < 0) || (y > tegel.getTerreinBreedte() - 1) )
						{
							//doorgaan = false;
							doorgaanNaarVolgende = true;
						}
						// op zelfde tegel
						else
						{
							nieuw = new Terrein( tegel, new Punt(punt) );
							doorgaanNaarVolgende = false;
						}	
						
						// slechts de starttegel bekijken, niet verdergaan naar aangrenzende
						if( beperkTotTegel )
							doorgaanNaarVolgende = false;
						
						// als op een andere tegel
						// => zoek het juiste punt op de buur
						if(doorgaanNaarVolgende)
						{
							//correctie van vorige increment (die zit dan al te ver);
							punt.setX( punt.getX() - xincrement );
							punt.setY( punt.getY() - yincrement );	
							
							nieuw = tegel.getGebiedBeheerder().getGebiedHelpers(richting).get(punt);
							
							// nieuw bestaat normaal gezien altijd, gezien de structuur van de lijsten
							// checken of er ook echt een tegel aan gekoppeld is!
							if(nieuw.getTegel() == null)
							{
								//System.out.println("Punt niet gevonden op buur");
								nieuw = null;
								
								gebied.voegOpenZijdeToe(new BordPositie(tegel,richting));
							}
						}
						
						
						if( nieuw != null ) // anders moeten we niet meer verdergaan
						{
							if( !gesloten.contains(nieuw.toString()) )
								berekenGebied(gebied, nieuw, gesloten, beperkTotTegel);
						}
						
					}// einde richtingen iteratie
					
				}// einde : check of start in gesloten zit
				else
				{
					// start zat er al in, betekent dat we gewoon mogen stoppen
				}
			}
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
