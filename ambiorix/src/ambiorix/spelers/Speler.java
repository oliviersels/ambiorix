package ambiorix.spelers;

import java.awt.Color;
import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;

public abstract class Speler {
	public enum STATUS {
		ACTIEF,
		GESTOPT,
		WACHTEND
	}
	
	private Color kleur;
	private int score;
	private String naam;
	private Vector<Pion> pionnen;
	private STATUS status;
	
	public Speler() {
		kleur = null;
		naam = null;
		pionnen = new Vector<Pion>();
	}
	
	public abstract void doeIets(); // TODO: Uitsplitsen naar meerdere functie -> zie behoeften anderen
	
	public abstract void zetTegel(Tegel t, BordPositie p);
	
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
	 * Ik ben er niet zeker van of deze functie wel nodig is. Het zetten van
	 * een tegel impliceert immers dat de zet toegestaan is.
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
	
	/**
	 * Geeft een terrein terug (een gebied op een tegel)
	 * @return Antwoord bevat: <br/>
	 *   a) terreinen (0): Het terrein dat gekozen is.
	 */
	public abstract Antwoord selecteerTegelGebied();
	
	/**
	 * 
	 * @return
	 */
	
	public abstract Antwoord selecteerSpelerPion();

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

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}
}
