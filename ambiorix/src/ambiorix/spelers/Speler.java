package ambiorix.spelers;

import java.awt.Color;
import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;

public abstract class Speler {
	// FIXME wa is van ne enum?
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
	 * Geeft terug welke positie de speler kiest om een tegel te
	 * plaatsen. Dit geeft geen verandering weer in de GUI. Enkel dat de
	 * gebruiker een keuze gemaakt heeft.
	 * @return Antwoord bevat:<br/>
	 *   a) posities (0): De positie waar deze geplaatst wil worden.
	 */
	public abstract Antwoord selecteerBordPositie();
	/**
	 * Moet onmiddelijk na plaatsTegel() opgeroepen worden om door te geven
	 * dat de tegel hier geplaatst mag worden.
	 * @param toegestaan true als de tegel hier mag geplaatst worden
	 */
	public abstract void positieToestaan(boolean toegestaan, BordPositie b);
	
	/**
	 * Geeft terug welke tegel de gebruiker geselecteerd heeft (om op het
	 * bord te leggen.
	 * @return Antwoord bevat: <br/>
	 *   a) tegels (0): De tegel die gekozen is.
	 */
	public abstract Antwoord selecteerSpelerTegel();

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
