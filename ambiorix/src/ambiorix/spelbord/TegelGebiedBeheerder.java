package ambiorix.spelbord;

import ambiorix.spelbord.Tegel.RICHTING;
import ambiorix.util.Punt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import java.util.Iterator;

public class TegelGebiedBeheerder 
{
	private Tegel tegel = null;

	private class GebiedHelper
	{
		public Tegel tegel;
		public Punt p;
	}
	
	private HashMap<Punt, GebiedHelper> GebiedHelpersBOVEN;
	private HashMap<Punt, GebiedHelper> GebiedHelpersRECHTS;
	private HashMap<Punt, GebiedHelper> GebiedHelpersONDER;
	private HashMap<Punt, GebiedHelper> GebiedHelpersLINKS;	
	
	public TegelGebiedBeheerder(Tegel tegel) 
	{
		this.tegel = tegel;
		
		GebiedHelpersBOVEN = new HashMap<Punt, GebiedHelper>();
		GebiedHelpersRECHTS = new HashMap<Punt, GebiedHelper>();
		GebiedHelpersONDER = new HashMap<Punt, GebiedHelper>();
		GebiedHelpersLINKS = new HashMap<Punt, GebiedHelper>();
		
		// 1 nodig per vakje aan buitenkant van terrein.
		// 1 extra nodig voor elke hoek ( + 4 dus ) want die wijzen naar beide kanten
		// voor elke kant een aparte lijst bijhouden is het beste
		
		int aantalKolommen = tegel.getTerrein()[0].length;
		int aantalRijen = tegel.getTerrein().length;
		
		for( int i = 0; i < aantalKolommen; i++ )
		{
			// boven is rij 0, kolom i
			GebiedHelpersBOVEN.put(new Punt(0,i), null);
			
			// onder is rij aantalRijen - 1, kolom i
			GebiedHelpersONDER.put(new Punt(aantalRijen - 1, i), null);
			
			// rechts is i, aantalKolommen - 1
			GebiedHelpersRECHTS.put(new Punt(i, aantalKolommen - 1), null);			
			
			// links is i, 0
			GebiedHelpersLINKS.put(new Punt(i,0), null);
		}
	}
	
		private HashMap<Punt, GebiedHelper> getGebiedHelpers(Tegel.RICHTING richting)
		{
			HashMap<Punt, GebiedHelper> verzameling = null;
			
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
	
	public void setBuur(Tegel buur, RICHTING richting)
	{
		
		// moeten zelf niet verder in de boom van tegels zoeken, daar zorgt tegel wel voor

		HashMap<Punt, GebiedHelper> verzameling = getGebiedHelpers(richting);
		Set<Punt> punten = verzameling.keySet();
		
		if( richting == Tegel.RICHTING.BOVEN)
		{
			/*for( Punt punt: punten)
			{
				verzameling.get(punt).
			}*/
		}
		else if( richting == Tegel.RICHTING.ONDER )
		{
			
		}
		else if( richting == Tegel.RICHTING.RECHTS )
		{
			
		}
		else if( richting == Tegel.RICHTING.LINKS )
		{
			
		}
		
	}
	
	public void print()
	{
		printRichting( Tegel.RICHTING.BOVEN );
		printRichting( Tegel.RICHTING.ONDER );
		printRichting( Tegel.RICHTING.RECHTS );
		printRichting( Tegel.RICHTING.LINKS );
	}
	
		private void printRichting( Tegel.RICHTING richting )
		{
			System.out.println("TegelGebiedBeheerder::printRichting " + richting.toString());
			
			HashMap<Punt, GebiedHelper> verzameling = getGebiedHelpers(richting);
			
			Set<Punt> keys = verzameling.keySet();
			
			for( Punt lokaalPunt: keys)
			{
				if(lokaalPunt != null)
				{
					System.out.print( lokaalPunt.getX() + "," + lokaalPunt.getY() + " -> " );
					
					GebiedHelper helper = (GebiedHelper) verzameling.get(lokaalPunt);
					if( helper != null)
						System.out.println( helper.p.getX() + "," + helper.p.getY() + " = " + helper.tegel.getID() );
					else
						System.out.println("LEEG");
				}
				else
					System.out.println("punt ERROR");
			}
		}
	
}
