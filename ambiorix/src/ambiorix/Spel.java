package ambiorix;

import java.util.Vector;

import ambiorix.acties.ActieBestuurder;
import ambiorix.acties.specifiek.GeefTegel;
import ambiorix.acties.specifiek.StartSpel;
import ambiorix.gui.Uitvoer;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelers.Speler;


public class Spel {
	
	private SpelToolkit speltoolkit;
	private Vector<Speler> spelers;
	private Spelbord spelbord;
	private ActieBestuurder actieBestuurder;
	
	public Spel(Uitvoer gui) {
		spelers = new Vector<Speler>();
		spelbord = new Spelbord();
		actieBestuurder = new ActieBestuurder(); // TODO moet via reflectie
		speltoolkit = new SpelToolkit(spelers, spelbord, gui);
	}
	
	// Spel starten: uitbreidingen bepalen de startactie
	public void start() {
		StartSpel startActie = new StartSpel(speltoolkit, null);
		actieBestuurder.start(startActie);
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
	 * Geeft de actieve speler. Enkel gebruiken voor het spel begonnen is.
	 * @return de active speler of null indien er geen actief is.
	 */
	public Speler getActieveSpeler() {
		return speltoolkit.getActieveSpeler();
	}
}
