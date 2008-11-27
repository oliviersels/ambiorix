package ambiorix.spelers;

import java.util.Vector;

import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TerreinType;

public class Antwoord {
	private Vector<Pion> pionnen;
	private Vector<Tegel> tegels;
	private Vector<TerreinType> terreinen; // FIXME moet dit geen Terrein zijn ipv TerreinType?
	
	public Antwoord() {
		pionnen = new Vector<Pion>();
		tegels = new Vector<Tegel>();
		terreinen = new Vector<TerreinType>();
	}

	public Vector<Pion> getPionnen() {
		return pionnen;
	}

	public Vector<Tegel> getTegels() {
		return tegels;
	}

	public Vector<TerreinType> getTerreinen() {
		return terreinen;
	}
}
