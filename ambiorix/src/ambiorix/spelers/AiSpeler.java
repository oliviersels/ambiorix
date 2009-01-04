package ambiorix.spelers;

import java.util.Vector;

import javax.swing.SwingUtilities;

import ambiorix.ai.Ai;
import ambiorix.ai.StandaardAi;
import ambiorix.gui.Uitvoer;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;


public class AiSpeler extends Speler
{
	private Ai aiElement;
	private Spelbord bord;
	private Vector<Antwoord> antwoorden;
	private Uitvoer gui;
	
	public AiSpeler(Spelbord b, Uitvoer uit)
	{
		bord = b;
		aiElement = null;
		antwoorden = new Vector<Antwoord>();
		gui = uit;
	}
	
	Antwoord huidigAntwoord = null;

	public Antwoord selecteerBordPositie() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	public Antwoord selecteerSpelerTegel() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	public Antwoord selecteerTegelGebied() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	public Antwoord selecteerSpelerPion() throws InterruptedException
	{
		return selecteerVolgendAntwoord();
	}
	
	//---------------------------------------------------------------------------------
	
	public void run()
	{
		aiElement = new StandaardAi();// is pionnen hier juist?
		aiElement.initialiseer(bord, tegels, pionnen, this);
		antwoorden = aiElement.berekenZet();
	}

	@Override
	public void zetPion(Pion p, Terrein t)
	{
		Uitvoer.ZetPion run = gui.new ZetPion(p, t);
		SwingUtilities.invokeLater(run);
		
		/* Korte pauze zodat het zichtbaar is */
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {}
	}

	@Override
	public void zetTegel(Tegel t, BordPositie p)
	{
		Uitvoer.ZetTegel run = gui.new ZetTegel(t, p);
		SwingUtilities.invokeLater(run);
		
		/* Korte pauze zodat het zichtbaar is */
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {}
	}

	@Override
	public void verwijderPion(Pion p)
	{
		Uitvoer.VerwijderPion run = gui.new VerwijderPion(p);
		SwingUtilities.invokeLater(run);
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
	
	public Antwoord selecteerVolgendAntwoord()
	{
		if( aiElement == null )
		{
			run();
		}
		else
		{
			antwoorden.remove(0);	// er is al een berekening geweest, dus eerste entry verwijderen
		}
		
		if ( !antwoorden.isEmpty() )
		{
			return antwoorden.elementAt(0);
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void setScore(int score) {
		super.setScore(score);
		
		Uitvoer.UpdateScore run = gui.new UpdateScore();
		SwingUtilities.invokeLater(run);
	}
	
	@Override
	public void addScore(int score) {
		super.addScore(score);
		
		Uitvoer.UpdateScore run = gui.new UpdateScore();
		SwingUtilities.invokeLater(run);
	}
	
	@Override
	public void deletePion(Pion pion) {
		super.deletePion(pion);
		
		Uitvoer.UpdateScore run = gui.new UpdateScore();
		SwingUtilities.invokeLater(run);
	}
	
	@Override
	public void addPion(Pion pion) {
		super.addPion(pion);
		
		Uitvoer.UpdateScore run = gui.new UpdateScore();
		SwingUtilities.invokeLater(run);
	}
}
