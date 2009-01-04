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
	
	public StandaardAi()
	{
		
	}
	
	public void initialiseer(Spelbord b, Vector<Tegel> tegels, Vector<Pion> pionnen, Speler speler)
	{
		bord = b;
		this.tegels = tegels;
		this.pionnen = pionnen;
		positieLijst = new Vector<Positie>();
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
		int huidigMaximumWaarde = -1, tijdelijkTotaal;
		Vector<Antwoord> antwoorden = new Vector<Antwoord>();
		
		//System.out.println("te plaatsen tegel: " + tegels.elementAt(0).getType().getID());
		
		positieLijst = maakPosities(bord);
		//telScores(positieLijst);
		//printScores( positieLijst );
		//printPositieLijst( positieLijst );
		//System.out.println("grootte positieLijst: " + positieLijst.size());
		
		for( int i = 0; i < positieLijst.size(); ++i )
		{
			//System.out.println("for-lus positieLijst op positie: " + (i+1) );
			positieLijst.elementAt(i).printPositie();
			
			tijdelijkTotaal = 0;
			if( positieLijst.elementAt(i).bevatSpeler( speler ) )// als punten van de speler beinvloed worden
			{
				//System.out.println( "speler wordt beinvloed" );
				Vector<Punt> beginpunten = tegels.elementAt(0).getGebiedBeginPunten(); // bereken beginpunten vd gebieden
				tijdelijkTotaal = positieLijst.elementAt(i).getScore( speler );
				//System.out.println("tijdelijkTotaal bij begin positie " + (i + 1) + ": " + tijdelijkTotaal);
				
				for( int j = 0; j < beginpunten.size(); ++j )
				{
					Terrein terrein = new Terrein( tegels.elementAt(0), beginpunten.elementAt(j) );
					Gebied gebied = tegels.elementAt(0).getGebied( terrein );	// bereken gebieden
					
					if( gebied.isEigenaar( speler ) )
					{
						if( gebied.getPionnen().size() == 1 ) // speler is enige bezitter
						{
							if( gebied.isEnigeEigenaar( speler ) )
							{
								// vermenigvuldig met hoge factor
								tijdelijkTotaal += 20 * positieLijst.elementAt(i).getScore( speler );
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
				
				//System.out.println("i+1 VOOR toekenning " + positieLijst.elementAt(i+1).getScore(speler) );
				positieLijst.elementAt(i).setScore(speler, tijdelijkTotaal);
				//System.out.println("i+1 NA toekenning " + positieLijst.elementAt(i+1).getScore(speler) );
			}
			
			if( positieLijst.elementAt(i).getScore(speler) > huidigMaximumWaarde )
			{
				System.out.println("maximumwaarde aangepast" );
				huidigMaximum = positieLijst.elementAt(i);
				huidigMaximumWaarde = positieLijst.elementAt(i).getScore(speler);
			}
		}
		
		System.out.println("ooooooooooooooooooooooooooo\n");
		huidigMaximum.printPositie();
		System.out.println("ooooooooooooooooooooooooooo\n");
		
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
		
		//System.out.println("*=========================\n");
		//printAntwoorden(antwoorden);
		//System.out.println("=========================*\n");
		
		return antwoorden;
	}

	@Override
	public Vector<Positie> maakPosities(Spelbord bord)
	{
		Vector<BordPositie> BPLijst = bord.controleerGlobalePlaatsbaarheid( tegels.elementAt(0), false );
		Vector<Positie> Plijst = new Vector<Positie>();
		
		for( int i = 0; i < BPLijst.size(); ++i )
		{
			// als we nog pionnen over hebben
			if( pionnen.size() > 0 )
			{
				Vector<Punt> beginpunten = tegels.elementAt(0).getGebiedBeginPunten();		// bereken startpunten vd gebieden op de tegel
				
				for( int j = 0; j < beginpunten.size(); ++j )								// voor elk beginpunt
				{
					Terrein terrein = new Terrein( tegels.elementAt(0), beginpunten.elementAt(j) );
					Positie p = new Positie( BPLijst.elementAt(i) );
					
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
			Positie p = new Positie( BPLijst.elementAt(i) );
			p.setPion(null);
			p.setLocatie(null);
			p.berekenScores( tegels.elementAt(0) );
			
			if( tegels.elementAt(0).getType().getID().equals( "TegelType_LavaMetBurchten" ) || tegels.elementAt(0).getType().getID().equals( "TegelType_BBBBB" ) )
			{
				//p.berekenScores2( tegels.elementAt(0) );
			}
			else
			{
				//p.berekenScores( tegels.elementAt(0) );
			}
			Plijst.add( p );
		}
		
		return Plijst;
	}
	
	public void printAntwoorden (Vector<Antwoord> antwoorden)
	{
		for( int i = 0; i < antwoorden.size(); ++i )
		{
			if( i == 0 )
			{
				System.out.println( "--- Tegels ---" );
				for( int j = 0; j < antwoorden.elementAt(i).getTegels().size(); ++j )
				{
					System.out.println( antwoorden.elementAt(i).getTegels().elementAt(j).toString() );
				}
			}
			else if( i == 1 )
			{
				System.out.println( "--- Posities ---" );
				if( antwoorden.elementAt(i).getPosities().size() > 0 )
				{
					System.out.println( " true " );
				}
			}
			else if( i == 2 )
			{
				System.out.println( "--- Pionnen ---" );
				if( antwoorden.elementAt(i).getPionnen().size() > 0 )
				{
					System.out.println( " true " );
				}
			}
			else if( i == 3 )
			{
				System.out.println( "--- Terreinen ---" );
				for( int j = 0; j < antwoorden.elementAt(i).getTerreinen().size(); ++j )
				{
					System.out.println( antwoorden.elementAt(i).getTerreinen().elementAt(j).toString() );
				}
			}
		}
	}
	
	public void telScores(Vector<Positie> lijst)
	{
		for( int j = 0; j < lijst.size(); ++j )
		{
			System.out.println( "element " + (j + 1) + ": " + lijst.elementAt(j).getScores().size() );
		}
	}
	
	public void printScores(Vector<Positie> lijst)
	{
		for( int i = 0; i < lijst.size(); ++i )
		{
			System.out.println( " -- NEW -- " );
			System.out.println( "element " + (i + 1) + ": " );
			
			for( int j = 0; j < lijst.elementAt(i).getScores().size(); ++j )
			{
				System.out.println( " - " + lijst.elementAt(i).getScores().elementAt(j).getScore() );
			}
			//System.out.println( "---- " );
		}
	}
	
	public void printPositieLijst(Vector<Positie> lijst)
	{
		
		for( int i = 0; i < lijst.size(); ++i )
		{
			System.out.println( "printPositieLijst op positie " + i );
			
			lijst.elementAt(i).printPositie();
		}
	}

	@Override
	public void reset() {
		positieLijst = new Vector<Positie>();		
	}
}
