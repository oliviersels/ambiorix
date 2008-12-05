package ambiorix.spelers;

import javax.swing.SwingUtilities;

import ambiorix.gui.Invoer;
import ambiorix.gui.InvoerLuisteraar;
import ambiorix.gui.Uitvoer;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;

public class MenselijkeSpeler extends Speler implements InvoerLuisteraar {
	Antwoord huidigAntwoord = null;
	
	@Override
	public void doeIets() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object vraagIets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Antwoord selecteerBordPositie() {
		/* We zullen aan de GUI moeten vragen waar de tegel geplaatst zal worden */
		
		Invoer i = new Invoer();
		Invoer.SelecteerBordPositie run = i.new SelecteerBordPositie(this);
		SwingUtilities.invokeLater(run);
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Dit betekent dat het spel gaat stoppen: NIET ZOMAAR System.exit doen!
			e.printStackTrace();
			System.exit(1);
		}
		
		run.stopLuisteren();
		
		return huidigAntwoord;
	}

	@Override
	public synchronized void invoerGebeurtenis(Antwoord a) {
		// Ik handel het vanaf hier terug af.
		huidigAntwoord = a;
		notifyAll();
	}

	@Override
	public void positieToestaan(boolean toegestaan, BordPositie b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Antwoord selecteerSpelerTegel() {
		// TODO Moet via Invoer gaan
		Tegel t = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		Antwoord stub = new Antwoord();
		stub.getTegels().add(t);
		return stub;
	}

	@Override
	public synchronized Antwoord selecteerTegelGebied() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Antwoord selecteerSpelerPion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void zetTegel(Tegel t, BordPositie p) {
		Uitvoer u = new Uitvoer();
		Uitvoer.ZetTegel run = u.new ZetTegel(t, p);
		SwingUtilities.invokeLater(run);
	}
}
