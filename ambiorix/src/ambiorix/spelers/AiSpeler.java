package ambiorix.spelers;

import java.util.Vector;

import javax.swing.SwingUtilities;

import ambiorix.ai.Ai;
import ambiorix.ai.Positie;
import ambiorix.ai.StandaardAi;
import ambiorix.gui.Invoer;
import ambiorix.gui.Uitvoer;
import ambiorix.gui.Invoer.SelecteerBordPositie;
import ambiorix.gui.Invoer.SelecteerSpelerTegel;
import ambiorix.gui.Uitvoer.NeemSpelerTegelAf;
import ambiorix.gui.Uitvoer.SpelerPionGeven;
import ambiorix.gui.Uitvoer.SpelerTegelGeven;
import ambiorix.gui.Uitvoer.ZetTegel;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Speler.STATUS;


public class AiSpeler extends Speler
{
	private Ai aiElement;
	private Spelbord bord;
	private Vector<Antwoord> antwoorden;
	
	public AiSpeler(Spelbord b)
	{
		bord = b;
		aiElement = null;
		antwoorden = new Vector<Antwoord>();
	}
	
	Antwoord huidigAntwoord = null;
	
	@Override
	public Antwoord selecteerBordPositie() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	@Override
	public void antwoordBordPositieSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	@Override
	public Antwoord selecteerSpelerTegel() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	@Override
	public void antwoordSpelerTegelSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	@Override
	public Antwoord selecteerTegelGebied() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	@Override
	public void antwoordTegelGebiedSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	@Override
	public Antwoord selecteerSpelerPion() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	public void antwoordSpelerPionSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	@Override
	public void zetActief(boolean actief)
	{
		super.zetActief(actief);
		
		if(actief == false)
		{
			// Beurt stopt; aiElement terug op null zetten zodat we in de volgende beurt opnieuw berekeningen kunnen laten uitvoeren
			aiElement = null;
		}
	}

	@Override
	public void zetPion(Pion p, Terrein t)
	{
		// niet van belang voor de AI
	}

	@Override
	public void zetTegel(Tegel t, BordPositie p)
	{
		// niet van belang voor de AI
	}

	@Override
	public void verwijderPion(Pion p)
	{
		
	}
	
//---------------------------------------------------------------------------------
	
	public void run()
	{
		aiElement = new StandaardAi(bord, tegels, pionnen, this);// is pionnen hier juist?
		antwoorden = aiElement.berekenZet();
	}
	
	public Antwoord selecteerVolgendAntwoord() throws InterruptedException
	{
		if( aiElement == null )
		{
			// berekeningen uitvoeren
			run();
		}
		else
		{
			// er is al een berekening geweest
			//		-> er is al een Antwoord verstuurd
			//		-> eerste element (hetgene dat reedsverstuurd is) uit de lijst verwijderen
			antwoorden.remove(0);
		}
		
		if( !antwoorden.isEmpty() )
		{
			// volgende Antwoord meegeven
			return antwoorden.elementAt(0);
		}
		else
		{
			return null;
		}
	}
}
