package ambiorix.spelbord;

import java.util.Set;
import java.util.Vector;
import java.util.HashMap;

public class Gebied 
{
	private TerreinType type;
	private Vector<Terrein> terreinStukken = new Vector<Terrein>();
	private Vector<BordPositie> openZijden = new Vector<BordPositie>();
	private HashMap<Pion,Terrein> pionnen = new HashMap<Pion,Terrein>();
	private Vector<Tegel> tegels = new  Vector<Tegel>();
	
	public Gebied()
	{
		
	}
	
	public void voegTerreinToe( Terrein h )
	{
		terreinStukken.add(h);
		
		if( !tegels.contains(h.getTegel()) )
			tegels.add(h.getTegel());
	}
	
	public TerreinType getType() 
	{
		return type;
	}

	public void setType(TerreinType type) 
	{
		this.type = type;
	}	
	
	// TODO : deze moet nog weg !!!
	public void setTerreinStukken( Vector<Terrein> stukken )
	{
		this.terreinStukken = stukken;
	}
	
	/*
	 * Geef true als het gebied helemaal afgesloten is aan alle kanten (geen open zijden meer)
	 */
	public boolean isVolledig()
	{
		return (openZijden.size() == 0);
	}
	
	public void voegOpenZijdeToe(BordPositie zijde)
	{
		// als hij er al inzit, niet nogmaals toevoegen
		if( getZijdeIndex(zijde) == -1 )
			openZijden.add(zijde);
	}
	
		private int getZijdeIndex(BordPositie positie)
		{
			BordPositie bp = null;
			for( int i = 0; i < openZijden.size(); i++)
			{
				bp = openZijden.get(i);
				if( (bp.getBuur() == positie.getBuur()) && (bp.getRichting() == positie.getRichting()) )
					return i;
			}
			
			return -1;
		}
	
	public void voegPionToe(Pion p, Terrein t)
	{
		if( !pionnen.containsKey(p) )
			pionnen.put(p,t);
	}
	
		/*private int getPionIndex(Pion p)
		{
			// elke pion heeft unieke ID, dus daar handig gebruik van maken
			Set<Pion> pionnenKeys = pionnen.keySet();
			
			Pion pion = null;
			for( int i = 0; i < ps.size(); i++)
			{
				pion = pionnenKeys.get(i);
				if( (pion.getID() == positie.getBuur()) && (bp.getRichting() == positie.getRichting()) )
					return i;
			}
		}*/
		
	


	public Vector<Terrein> getTerreinStukken() 
	{
		return terreinStukken;
	}
	
	public Vector<Tegel> getTegels()
	{
		return tegels;
	}
	
	// TODO : mss ook een functie getPionnen die Vector<Pion> teruggeeft ?
	public HashMap<Pion,Terrein> getPionnenEnPosities()
	{
		return pionnen;
	}
	
	public Set<Pion> getPionnen()
	{
		return pionnen.keySet();
	}
	
	public Vector<BordPositie> getOpenZijden()
	{
		return openZijden;
	}
	
	
	public void print()
	{
		// tereinstukken
		System.out.println(">> Terreinstukken : ");
		for( Terrein terrein: terreinStukken )
		{
			System.out.println(terrein.getTegel().getID() + "->" +  terrein.getPositie().toString() );
		}
		
		System.out.println(">> Tegels : ");
		for( Tegel tegel: tegels )
		{
			System.out.println(tegel.getID() );
		}
		
		System.out.println(">> Open Zijden : ");
		for( BordPositie positie: openZijden )
		{
			System.out.println(positie.getBuur().getID() + " langs " + positie.getRichting() );
		}
		
		System.out.println(">> Pionnen : ");
		Set<Pion> pionKeys = pionnen.keySet();
		for( Pion pion: pionKeys )
		{
			System.out.println(pion.getID() + " op " + pionnen.get(pion).toString() );
		}
		
		
	}
	
		
	
}
