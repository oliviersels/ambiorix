package ambiorix;

import java.util.Vector;

import ambiorix.acties.Actie;
import ambiorix.spelers.Speler;


public class Spel {
	
	private Vector<Speler> spelers;
	private Speler actieveSpeler = null;
	private SpelToolkit speltoolkit;
	
	
	public Spel() {
		speltoolkit = new SpelToolkit(this);
		spelers = new Vector<Speler>();
	}

	// TEMP
	public SpelToolkit getSpeltoolkit() {
		return speltoolkit;
	}	
	
	// spel starten
	public void start(Actie start) {
		System.out.println("---- start ---- : "+getAantalSpelers());
		if(getAantalSpelers() > 1)
			speltoolkit.start(start);
	}
	
	// spelers
	public Vector<Speler> getSpelers() {
		return spelers;
	}

	public boolean addSpeler(Speler speler) {
		if(actieveSpeler==null)
			actieveSpeler = speler;
		return spelers.add(speler);
	}

	public int getAantalSpelers() {
		return spelers.size();
	}

	public Speler getActieveSpeler() {
		return actieveSpeler;
	}

	public void setActieveSpeler(Speler actieveSpeler) {
		this.actieveSpeler = actieveSpeler;
	}

}
