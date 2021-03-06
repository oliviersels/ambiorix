package ambiorix.spelers;

import java.awt.Color;
import java.util.Vector;

import ambiorix.acties.UndoException;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;

public abstract class Speler {
	public enum STATUS {
		ACTIEF, GESTOPT, WACHTEND
	}

	private Color kleur;
	private int score;
	private String naam;
	protected Vector<Pion> pionnen;
	protected Vector<Tegel> tegels;
	private STATUS status;

	public Speler() {
		kleur = null;
		naam = null;
		pionnen = new Vector<Pion>();
		tegels = new Vector<Tegel>();
	}

	// public abstract Antwoord vraagIets();
	// public abstract void geefAntwoord(Antwoord a);
	// public abstract void doeIets();

	/**
	 * Geeft terug welke positie de speler kiest om een tegel te plaatsen. Dit
	 * geeft geen verandering weer in de GUI. Enkel dat de gebruiker een keuze
	 * gemaakt heeft.
	 * 
	 * @return Antwoord bevat:<br/>
	 *         a) posities (0): De positie waar deze geplaatst wil worden.
	 * @throws UndoException
	 */
	public abstract Antwoord selecteerBordPositie()
			throws InterruptedException, UndoException;

	/**
	 * Geeft terug welke tegel de gebruiker geselecteerd heeft (om op het bord
	 * te leggen.
	 * 
	 * @return Antwoord bevat: <br/>
	 *         a) tegels (0): De tegel die gekozen is.
	 * @throws UndoException
	 */
	public abstract Antwoord selecteerSpelerTegel()
			throws InterruptedException, UndoException;

	/**
	 * Geeft een terrein terug (een gebied op een tegel)
	 * 
	 * @return Antwoord bevat: <br/>
	 *         a) terreinen (0): Het terrein dat gekozen is.
	 * @throws UndoException
	 */
	public abstract Antwoord selecteerTegelGebied()
			throws InterruptedException, UndoException;

	/**
	 * Geeft een pion terug van een speler
	 * 
	 * @return Antwoord bevat:<br/>
	 *         a) pionnen (0): De pion die gekozen werd.
	 * @throws UndoException
	 */
	public abstract Antwoord selecteerSpelerPion() throws InterruptedException,
			UndoException;

	public abstract void zetTegel(Tegel t, BordPositie p);

	public abstract void zetPion(Pion p, Terrein t);

	public abstract void verwijderPion(Pion p);

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

	public void addTegel(Tegel tegel) {
		tegels.add(tegel);
	}

	public void deleteTegel(Tegel tegel) {
		tegels.remove(tegel);
	}

	public int getAantalTegels() {
		return tegels.size();
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
	 * Geeft terug hoeveel pionnen de speler heeft van het type pion. Maakt
	 * gebruik van equals!
	 * 
	 * @param pion
	 * @return het aantal pionnen.
	 */
	public int geefAantalPionnen(Pion pion) {
		int aantal = 0;

		for (Pion p : pionnen) {
			if (p.equals(pion))
				aantal++;
		}

		return aantal;
	}

	public boolean isActief() {
		return status == STATUS.ACTIEF;
	}

	public void zetActief(boolean actief) {
		if (actief)
			status = STATUS.ACTIEF;
		else
			status = STATUS.WACHTEND;
	}
}
