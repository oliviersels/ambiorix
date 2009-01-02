package ambiorix.ai;

import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Antwoord;
import ambiorix.util.Punt;

public class StandaardAi extends Ai {

	public StandaardAi(Spelbord b, Vector<Tegel> tegels, Vector<Pion> pionnen)
	{
		super(b, tegels, pionnen);
	}

	@Override
	public double berekenKans(BordPositie positie)
	{
		
		return 0;
	}

	@Override
	public void berekenScore(BordPositie positie)
	{
		
	}

	@Override
	public Vector<Antwoord> berekenZet()
	{
		
		return null;
	}

	@Override
	public Vector<Positie> MaakPosities(Spelbord bord)
	{
		Vector<BordPositie> BPLijst = bord.controleerGlobalePlaatsbaarheid( tegels.elementAt(0), false );
		Vector<Positie> Plijst = new Vector<Positie>();
		
		for( int i = 0; i < BPLijst.size(); ++i )
		{
			Positie p = new Positie( BPLijst.elementAt(i) );
			Vector<Punt> beginpunten = tegels.elementAt(0).getGebiedBeginPunten();		// bereken startpunten vd gebieden op de tegel
			
			for( int j = 0; j < beginpunten.size(); ++j )								// voor elk beginpunt
			{
				Terrein terrein = new Terrein( tegels.elementAt(0), beginpunten.elementAt(j) );
				Gebied gebied = tegels.elementAt(0).getGebied( terrein );				// bereken het gebied van dit beginpunt
				
				if( gebied.getPionnen().size() == 0 && pionnen.size() > 0 )				// als gebied leeg en nog pionnen over
				{
					p.setPion( pionnen.elementAt(0) );									// zet pion in gebied
					Plijst.add( p );													// voeg Positie toe aan lijst
					p.berekenScores( tegels.elementAt(0) );								// bereken score van deze Positie
				}
				
				p.setPion(null);														// Pion terug op null zetten
			}
			
			Plijst.add( p );															// voeg Positie toe aan lijst zonder pion
			p.berekenScores( tegels.elementAt(0) );										// bereken score van deze Positie
		}
		
		//return null;
		return Plijst;
	}

}
