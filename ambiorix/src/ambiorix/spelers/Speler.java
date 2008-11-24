package ambiorix.spelers;

import java.awt.Color;
import java.util.HashMap;

public abstract class Speler {
	public static int ACTIEF = 1;
	public static int GESTOPT = -1;
	public static int WACHTEND = 0;
	
	private Color kleur;
	private int score;
	private String naam;
	private HashMap<String, Integer> pionnen; // TODO: String key vervangen door PionType
	private int status;
	
	public Speler() {
		
	}
	
	public abstract void doeIets(); // TODO: Uitsplitsen naar meerdere functie -> zie behoeften anderen
	
	public abstract Object vraagIets(); // TODO: Uitsplitsen naar meerdere functies -> zie behoeften anderen

	public Color getKleur() {
		return kleur;
	}

	public void setKleur(Color kleur) {
		this.kleur = kleur;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int extra) {
		score += extra;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public void addPion(String pion, int aantal) { // TODO: String pion veranderen in PionType pion
		int huidigAantal;
		try {
			huidigAantal = geefAantalPionnen(pion);
			pionnen.put(pion, new Integer(huidigAantal + aantal));
		}
		catch (PionTypeException e) {
			pionnen.put(pion, aantal);
		}
	}
	
	public void deletePion(String pionType) throws PionTypeException { // TODO: String pion veranderen naar PionType
		if(pionnen.containsKey(pionType))
			pionnen.remove(pionType);
		else
			PionTypeException.throwNow(pionType);
	}
	
	public int geefAantalPionnen(String pionType) throws PionTypeException { // TODO: String pion veranderen naar PionType
		Integer resultaat;
		resultaat = pionnen.get(pionType);
		if(resultaat == null) {
			PionTypeException.throwNow(pionType);
			return 0; // Hier komen we nooit maar de compiler is niet slim genoeg om dat te merken en klaagt anders
		}
		else
			return resultaat.intValue();
	}
}
