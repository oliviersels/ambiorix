package ambiorix.ai;

import java.util.Vector;

import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;

public abstract class Ai {
	protected Spelbord bord;
	protected Vector<Positie> positieLijst;
	protected Vector<Tegel> tegels;
	protected Vector<Pion> pionnen;
	protected Speler speler;

	public Ai() {

	}

	public void initialiseer(Spelbord b, Vector<Tegel> tegels,
			Vector<Pion> pionnen, Speler speler) {
		bord = b;
		this.tegels = tegels;
		this.pionnen = pionnen;
		positieLijst = new Vector<Positie>();
		this.speler = speler;
	}

	// ---------------------------------------------------------------------------------

	public abstract Vector<Antwoord> berekenZet();

	public abstract void reset();
}
