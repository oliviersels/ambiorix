package ambiorix.spelers;

import javax.swing.SwingUtilities;

import ambiorix.Spel;
import ambiorix.gui.Invoer;
import ambiorix.gui.InvoerLuisteraar;
import ambiorix.gui.Uitvoer;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;

public class MenselijkeSpeler extends Speler implements InvoerLuisteraar {
	Invoer invoer;
	Uitvoer uitvoer;
	
	public MenselijkeSpeler(Invoer in, Uitvoer uit) {
		super();
		
		invoer = in;
		uitvoer = uit;
	}

	Antwoord huidigAntwoord = null;

	@Override
	public synchronized void invoerGebeurtenis(Antwoord a) {
		// Ik handel het vanaf hier terug af.
		huidigAntwoord = a;
		notifyAll();
	}
	
	@Override
	public synchronized Antwoord selecteerBordPositie() throws InterruptedException {
		/* We zullen aan de GUI moeten vragen waar de tegel geplaatst zal worden */
		
		Invoer.SelecteerBordPositie run = invoer.new SelecteerBordPositie(this);
		SwingUtilities.invokeLater(run);
		

		wait();
		
		run.opruimen();
		
		return huidigAntwoord;
	}

	@Override
	public synchronized Antwoord selecteerSpelerTegel() throws InterruptedException {
		/* We zullen aan de GUI moeten vragen welke tegel de speler wilt plaatsen */
		
		Invoer.SelecteerSpelerTegel run = invoer.new SelecteerSpelerTegel(this, this);
		SwingUtilities.invokeLater(run);
		
		wait();

		run.opruimen();
		
		return huidigAntwoord;
	}

	@Override
	public synchronized Antwoord selecteerTegelGebied() throws InterruptedException {
		Invoer.SelecteerTegelGebied run = invoer.new SelecteerTegelGebied(this);
		SwingUtilities.invokeLater(run);

		wait();
		
		run.opruimen();
		
		return huidigAntwoord;
	}

	@Override
	public synchronized Antwoord selecteerSpelerPion() throws InterruptedException {
		Invoer.SelecteerSpelerPion run = invoer.new SelecteerSpelerPion(this, this);
		SwingUtilities.invokeLater(run);

		wait();
		
		run.opruimen();
		
		return huidigAntwoord;
	}

	@Override
	public void zetTegel(Tegel t, BordPositie p) {
		Uitvoer.ZetTegel run = uitvoer.new ZetTegel(t, p);
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void zetPion(Pion p, Terrein t) {
		Uitvoer.ZetPion run = uitvoer.new ZetPion(p, t);
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void antwoordBordPositieSelectie(Antwoord a) {}

	@Override
	public void antwoordSpelerTegelSelectie(Antwoord a) {}

	@Override
	public void antwoordSpelerPionSelectie(Antwoord a) {}

	@Override
	public void antwoordTegelGebiedSelectie(Antwoord a) {}

	@Override
	public void addPion(Pion pion) {
		super.addPion(pion);
		
		Uitvoer.SpelerPionGeven run = uitvoer.new SpelerPionGeven(this, pion);
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void addTegel(Tegel tegel) {
		super.addTegel(tegel);
		
		Uitvoer.SpelerTegelGeven run = uitvoer.new SpelerTegelGeven(this, tegel);
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void deletePion(Pion pion) {
		super.deletePion(pion);
		
		Uitvoer.NeemSpelerPionAf run = uitvoer.new NeemSpelerPionAf(this, pion);
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void deleteTegel(Tegel tegel) {
		super.deleteTegel(tegel);
		
		Uitvoer.NeemSpelerTegelAf run = uitvoer.new NeemSpelerTegelAf(this, tegel);
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void setScore(int score) {
		super.setScore(score);
		
		Uitvoer.UpdateScore run = uitvoer.new UpdateScore();
		SwingUtilities.invokeLater(run);
	}
	
	@Override
	public void addScore(int score) {
		super.addScore(score);
		
		Uitvoer.UpdateScore run = uitvoer.new UpdateScore();
		SwingUtilities.invokeLater(run);
	}

	@Override
	public void verwijderPion(Pion p) {
		Uitvoer.VerwijderPion run = uitvoer.new VerwijderPion(p);
		SwingUtilities.invokeLater(run);
	}
}
