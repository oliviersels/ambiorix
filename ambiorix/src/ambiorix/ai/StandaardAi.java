package ambiorix.ai;

import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;
import ambiorix.util.Punt;

public class StandaardAi extends Ai
{
	private Speler speler;
	
	public StandaardAi(Spelbord b, Vector<Tegel> tegels, Vector<Pion> pionnen, Speler speler)
	{
		super(b, tegels, pionnen);
		this.speler = speler;
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
		Positie huidigMaximum = null;
		int huidigMaximumWaarde = 0, tijdelijkTotaal;
		Vector<Antwoord> antwoorden = new Vector<Antwoord>();
		
		positieLijst = maakPosities(bord);
		//System.out.println( positieLijst.size());
		
		//System.out.println( positieLijst.elementAt(0).getPositie().toString());
		//System.out.println( positieLijst.elementAt(0).getPion().toString());
		//System.out.println( positieLijst.elementAt(0).getLocatie().toString());
		//System.out.println( positieLijst.elementAt(0).getScores().elementAt(0).getScore());
		
		
		for( int i = 0; i < positieLijst.size(); ++i )
		{
			positieLijst.elementAt(i).printPositie();
			
			tijdelijkTotaal = 0;
			if( positieLijst.elementAt(i).bevatSpeler( speler ) )// als punten van de speler beinvloed worden
			{
				Vector<Punt> beginpunten = tegels.elementAt(0).getGebiedBeginPunten(); // bereken beginpunten vd gebieden
				tijdelijkTotaal = positieLijst.elementAt(i).getScore( speler );
				
				for( int j = 0; j < beginpunten.size(); ++j )
				{
					Terrein terrein = new Terrein( tegels.elementAt(0), beginpunten.elementAt(j) );
					Gebied gebied = tegels.elementAt(0).getGebied( terrein );	// bereken gebieden
					
					if( gebied.isEigenaar( speler ) )
					{
						if( gebied.getPionnen().size() == 1 ) // speler is enige bezitter
						{
							if( gebied.isVolledig() )
							{
								// vermenigvuldig met hoge factor
								tijdelijkTotaal += 10 * positieLijst.elementAt(i).getScore( speler );
							}
							else
							{
								// vermenigvuldigen met ( gebied.getOpenZijden().size() * lage factor )
								tijdelijkTotaal += 2 * ( 5 * gebied.getOpenZijden().size() + positieLijst.elementAt(i).getScore( speler ) );
							}
						}
						else // speler is een vd bezitters
						{
							if( gebied.isVolledig() )
							{
								// vermenigvuldig met hoge factor
								tijdelijkTotaal += 5 * positieLijst.elementAt(i).getScore( speler );
							}
							else
							{
								// vermenigvuldigen met ( gebied.getOpenZijden().size() * lage factor )
								tijdelijkTotaal += 2 * ( 2 * gebied.getOpenZijden().size() + positieLijst.elementAt(i).getScore( speler ) );
							}
						}
					}
					else // speler is geen bezitter
					{
						if( gebied.isVolledig() )
						{
							// vermenigvuldig met lage factor
							tijdelijkTotaal -= 10 * positieLijst.elementAt(i).getScore( speler );
						}
						else
						{
							// vermenigvuldigen met ( gebied.getOpenZijden().size() * hoge factor )
							tijdelijkTotaal += 2 * ( 5 * gebied.getOpenZijden().size() + positieLijst.elementAt(i).getScore( speler ) );
						}
					}
				}
				
				positieLijst.elementAt(i).setScore(speler, tijdelijkTotaal);
			}
			
			if( positieLijst.elementAt(i).getScore(speler) > huidigMaximumWaarde )
			{
				huidigMaximum = positieLijst.elementAt(i);
				huidigMaximumWaarde = positieLijst.elementAt(i).getScore(speler);
			}
		}
		
		// -- antwoorden aanmaken
		Antwoord a1 = new Antwoord();
		a1.getTegels().add( tegels.elementAt(0) );
		antwoorden.add( a1 );
		//------
		Antwoord a2 = new Antwoord();
		//huidigMaximum.getPositie();
		a2.getPosities().add( huidigMaximum.getPositie() );
		antwoorden.add( a2 );
		//------
		if( huidigMaximum.getPion() != null )
		{
			Antwoord a3 = new Antwoord();
			a3.getPionnen().add( huidigMaximum.getPion() );
			antwoorden.add( a3 );
		}
		//------
		if( huidigMaximum.getLocatie() != null )
		{
			Antwoord a4 = new Antwoord();
			a4.getTerreinen().add( huidigMaximum.getLocatie() );
			antwoorden.add( a4 );
		}
		
		return antwoorden;
	}

	@Override
	public Vector<Positie> maakPosities(Spelbord bord)
	{
		Vector<BordPositie> BPLijst = bord.controleerGlobalePlaatsbaarheid( tegels.elementAt(0), false );
		Vector<Positie> Plijst = new Vector<Positie>();
		
		for( int i = 0; i < BPLijst.size(); ++i )
		{
			Positie p = new Positie( BPLijst.elementAt(i) );
			
			// als we nog pionnen over hebben
			if( pionnen.size() > 0 )
			{
				Vector<Punt> beginpunten = tegels.elementAt(0).getGebiedBeginPunten();		// bereken startpunten vd gebieden op de tegel
				
				for( int j = 0; j < beginpunten.size(); ++j )								// voor elk beginpunt
				{
					Terrein terrein = new Terrein( tegels.elementAt(0), beginpunten.elementAt(j) );
					
					if( bord.controleerPlaatsbaarheid( pionnen.elementAt(0), terrein) )		// als gebied leeg is
					{
						tegels.elementAt(0).plaatsPion( beginpunten.elementAt(j), pionnen.elementAt(0) );	// plaats pion VOORLOPIG op de tegel
						//----
						p.setPion( pionnen.elementAt(0) );									// zet pion IN POSITIE
						p.setLocatie( terrein );											// zet terrein waarop pion geplaatst wordt IN POSITIE
						p.berekenScores( tegels.elementAt(0) );								// bereken score van deze Positie
						Plijst.add( p );													// voeg Positie toe aan lijst
						//----
						tegels.elementAt(0).verwijderPion( beginpunten.elementAt(j) );		// pion opnieuw verwijderen van spelbord
					}
				}
			}
			
			// lege tegel zetten
			p.setPion(null);
			p.setLocatie(null);
			p.berekenScores( tegels.elementAt(0) );
			Plijst.add( p );
		}
		
		return Plijst;
	}
}