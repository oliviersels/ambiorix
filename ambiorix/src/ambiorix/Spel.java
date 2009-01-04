package ambiorix;

import java.util.Vector;

import ambiorix.acties.AbstractActie;
import ambiorix.acties.Actie;
import ambiorix.acties.ActieBestuurder;
import ambiorix.acties.ActieVerzameling;
import ambiorix.acties.specifiek.GeefTegel;
import ambiorix.acties.specifiek.StartSpel;
import ambiorix.gui.Uitvoer;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelers.Speler;
import ambiorix.uitbreidingen.UitbreidingVerzameling;


public class Spel {
	
	private SpelToolkit speltoolkit;
	private Vector<Speler> spelers;
	private Spelbord spelbord;
	private ActieBestuurder actieBestuurder;
	private ScoreBerekenaar scoreBerekenaar;
	
	public Spel(Uitvoer gui) {
		spelers = new Vector<Speler>();
		spelbord = new Spelbord();
		actieBestuurder = new ActieBestuurder();
		scoreBerekenaar = UitbreidingVerzameling.getInstantie().getScoreBerekenaar();
		speltoolkit = new SpelToolkit(spelers, spelbord, gui, scoreBerekenaar);
	}
	
	// Spel starten: uitbreidingen bepalen de startactie
	public void start() {
		actieBestuurder.start(new StartSpel(speltoolkit, null));
		/*
		try {
			String startString = UitbreidingVerzameling.getInstantie().getEersteActie();
			Object[] param = {speltoolkit, null};
			Class<?>[] paramKlassen = {SpelToolkit.class, AbstractActie.class};
			Actie startActie =  ActieVerzameling.getInstantie().getNewInstantie(startString, param, paramKlassen);
			
			actieBestuurder.start(startActie);
		} catch (Exception e) {
			System.err.println("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();
		}*/
	}
	
	public void stop() {
		actieBestuurder.stop();
	}
	
	// spelers

	public boolean addSpeler(Speler speler) {
		if(getAantalSpelers() < 5)
			return spelers.add(speler);
		return false;
	}

	public int getAantalSpelers() {
		return spelers.size();
	}
	
	/**
	 * Geeft het spelbord. Wordt gebruikt in de AI, gelieve NIET te verwijderen!
	 * @return het huidige spelbord waarop we spelen.
	 */
	public Spelbord getSpelbord()
	{
		return spelbord;
	}
	
	/**
	 * Geeft de actieve speler. Enkel gebruiken voor het spel begonnen is.
	 * @return de active speler of null indien er geen actief is.
	 */
	public Speler getActieveSpeler() {
		return speltoolkit.getActieveSpeler();
	}
}
