package ambiorix;

import java.util.Vector;

import ambiorix.acties.Actie;
import ambiorix.spelers.Speler;


public class Spel {
	
	private SpelToolkit speltoolkit;
	private Vector<Speler> spelers;
	// Uitbreidingen moeten ook nog bijgehouden worden (zij bepalen de acties)
	
	public Spel() {
		spelers = new Vector<Speler>();
		speltoolkit = new SpelToolkit(spelers);
	}

	// TEMP
	@Deprecated
	public SpelToolkit getSpeltoolkit() {
		return speltoolkit;
	}	
	
	// spel starten
	@Deprecated
	public void start(Actie start) {
		System.out.println("---- start ---- : "+getAantalSpelers());
		if(getAantalSpelers() > 1)
			speltoolkit.start(start);
	}
	
	// Spel starten: uitbreidingen bepalen de startactie
	public void start() {
		// TODO stub
	}
	
	// spelers
	public Vector<Speler> getSpelers() {
		return spelers;
	}

	public boolean addSpeler(Speler speler) {
		return spelers.add(speler);
	}

	public int getAantalSpelers() {
		return spelers.size();
	}
}
