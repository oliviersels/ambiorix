package ambiorix.spelbord;

import ambiorix.spelbord.Tegel.RICHTING;
import ambiorix.util.Punt;

import java.util.HashMap;
import java.util.Set;

public class TegelGebiedBeheerder 
{
	private Tegel tegel = null;
	
	private HashMap<Punt, Terrein> GebiedHelpersBOVEN;
	private HashMap<Punt, Terrein> GebiedHelpersRECHTS;
	private HashMap<Punt, Terrein> GebiedHelpersONDER;
	private HashMap<Punt, Terrein> GebiedHelpersLINKS;	
	
	public TegelGebiedBeheerder(Tegel tegel) 
	{
		this.tegel = tegel;
		
		GebiedHelpersBOVEN = new HashMap<Punt, Terrein>();
		GebiedHelpersRECHTS = new HashMap<Punt, Terrein>();
		GebiedHelpersONDER = new HashMap<Punt, Terrein>();
		GebiedHelpersLINKS = new HashMap<Punt, Terrein>();
		
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
	
		private HashMap<Punt, Terrein> getGebiedHelpers(Tegel.RICHTING richting)
		{
			HashMap<Punt, Terrein> verzameling = null;
			
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
		HashMap<Punt, Terrein> huidigeZijde = getGebiedHelpers(richting);
		
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

		HashMap<Punt, Terrein> verzameling = getGebiedHelpers(richting);
		Set<Punt> punten = verzameling.keySet();
		
		for( Punt punt: punten )
		{
			verzameling.get(punt).setTegel(buur);
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
			
			HashMap<Punt, Terrein> verzameling = getGebiedHelpers(richting);
			
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
