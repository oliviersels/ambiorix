package ambiorix.spelers;

import java.awt.Color;
import java.util.Vector;

import ambiorix.spelbord.Pion;

public abstract class Speler {
	public static int ACTIEF = 1;
	public static int GESTOPT = -1;
	public static int WACHTEND = 0;
	
	private Color kleur;
	private int score;
	private String naam;
	private Vector<Pion> pionnen;
	private int status;
	
	public Speler() {
		kleur = null;
		naam = null;
		pionnen = new Vector<Pion>();
	}
	
	public abstract void doeIets(); // TODO: Uitsplitsen naar meerdere functie -> zie behoeften anderen
	
	public abstract Object vraagIets(); // TODO: Uitsplitsen naar meerdere functies -> zie behoeften anderen
	
	/**
	 * Deze functie geeft terug welke positie de speler kiest om een tegel te
	 * plaatsen. Het is cruciaal dat de aanroeper hierna bevestigTegel() aanroept
	 * om de positie te bevestigen.
	 * @return Antwoord bevat:</br>
	 *   a) Tegels (1): De tegel die de gebruiker wilt plaatsen.</br>
	 *   b) Tegels (2): De positie waar deze geplaatst wil worden.
	 */
	public abstract Antwoord plaatsTegel();
	public abstract void bevestigTegel(boolean toegestaan);

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
	
	public void addPion(Pion pion) {
		pionnen.add(pion);
	}
	
	public void deletePion(Pion pion) {
		pionnen.remove(pion);
	}
	
	public int getAantalPionnen() {
		return pionnen.size();
	}
	
	/**
	 * Geeft terug hoeveel pionnen de speler heeft van het type pion. Maakt gebruik van equals!
	 * @param pion
	 * @return het aantal pionnen.
	 */
	public int geefAantalPionnen(Pion pion) {
		int aantal = 0;
		
		for(Pion p : pionnen) {
			if(p.equals(pion))
				aantal++;
		}
		
		return aantal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
