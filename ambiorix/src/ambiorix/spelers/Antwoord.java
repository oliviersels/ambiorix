package ambiorix.spelers;

import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;

public class Antwoord {
	private Vector<Pion> pionnen;
	private Vector<Tegel> tegels;
	private Vector<Terrein> terreinen;
	private Vector<BordPositie> posities;
	
	public Antwoord() {
		pionnen = new Vector<Pion>();
		tegels = new Vector<Tegel>();
		terreinen = new Vector<Terrein>();
		posities = new Vector<BordPositie>();
	}

	public Vector<BordPositie> getPosities() {
		return posities;
	}

	public Vector<Pion> getPionnen() {
		return pionnen;
	}

	public Vector<Tegel> getTegels() {
		return tegels;
	}

	public Vector<Terrein> getTerreinen() {
		return terreinen;
	}
}
