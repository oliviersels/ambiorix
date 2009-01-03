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

	public Antwoord selecteerBordPositie() throws InterruptedException
	{
		if( aiElement == null )
		{
			run();
		}
		else
		{
			antwoorden.remove(0);	// er is al een berekening geweest, dus eerste entry verwijderen
		}
		
		Antwoord nieuw = antwoorden.elementAt(0);
		
		return nieuw;
	}
	
	public void antwoordBordPositieSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	public Antwoord selecteerSpelerTegel() throws InterruptedException
	{
		if( aiElement == null )
		{
			run();
		}
		else
		{
			antwoorden.remove(0);	// er is al een berekening geweest, dus eerste entry verwijderen
		}
		
		Antwoord nieuw = antwoorden.elementAt(0);
		
		return nieuw;
	}
	
	public void antwoordSpelerTegelSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	public Antwoord selecteerTegelGebied() throws InterruptedException
	{
		if( aiElement == null )
		{
			run();
		}
		else
		{
			antwoorden.remove(0);	// er is al een berekening geweest, dus eerste entry verwijderen
		}
		
		Antwoord nieuw = antwoorden.elementAt(0);
		
		return nieuw;
	}
	
	public void antwoordTegelGebiedSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	public Antwoord selecteerSpelerPion() throws InterruptedException
	{
		if( aiElement == null )
		{
			run();
		}
		else
		{
			antwoorden.remove(0);	// er is al een berekening geweest, dus eerste entry verwijderen
		}
		
		Antwoord nieuw = antwoorden.elementAt(0);
		
		return nieuw;
	}
	
	public void antwoordSpelerPionSelectie(Antwoord a)
	{
		// niet van belang voor de AI
	}
	
	//---------------------------------------------------------------------------------
	
	public void run()
	{
		aiElement = new StandaardAi(bord, tegels, pionnen, this);// is pionnen hier juist?
		antwoorden = aiElement.berekenZet();
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
	public void verwijderPion(Pion p) {
		// FIXME Auto-generated method stub
		
	}
	
	@Override
	public void zetActief(boolean actief)
	{
		super.zetActief(actief);
		
		if(actief == false)
		{
			aiElement = null;
		}
	}

}
