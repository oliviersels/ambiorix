package ambiorix.ai;

import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Speler;
import ambiorix.uitbreidingen.UitbreidingVerzameling;
import ambiorix.util.Punt;

public class Positie {
	private BordPositie positie;
	private Vector<Score> scores;
	private Pion pion;
	private Terrein locatie;

	public Positie(BordPositie positie) {
		this.positie = positie;
		scores = new Vector<Score>();
	}

	// ---------------------------------------------------------------------------------

	public BordPositie getPositie() {
		return positie;
	}

	public Vector<Score> getScores() {
		return scores;
	}

	public Pion getPion() {
		return pion;
	}

	public Terrein getLocatie() {
		return locatie;
	}

	public Score getScore(int index) {
		if (index > 0 && index < scores.size()) {
			return scores.elementAt(index);
		}

		return null;
	}

	// ---------------------------------------------------------------------------------

	public void setScore(int index, Score score) {
		if (index > 0 && index < scores.size()) {
			scores.setElementAt(score, index);
		}
	}

	public void setScore(Speler speler, int score) {
		for (int i = 0; i < scores.size(); ++i) {
			if (speler.getNaam().toString().equals(
					scores.elementAt(i).getSpeler().getNaam().toString())) {
				scores.elementAt(i).setScore(score);
			}
		}
	}

	public void setScores(Vector<Score> s) {
		scores = new Vector<Score>();
		scores.addAll(s);
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}

	public void setLocatie(Terrein locatie) {
		this.locatie = locatie;
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Berekent de score van gebieden waar de geplaatste tegel deel van
	 * uitmaakt.
	 */
	public void berekenScores(Tegel t) {
		ScoreBerekenaar simpel = UitbreidingVerzameling.getInstantie().getScoreBerekenaar();
		Vector<Punt> beginpunten = t.getGebiedBeginPunten(); // bereken
		// startpunten
		// vd gebieden
		// op de tegel

		for (int i = 0; i < beginpunten.size(); ++i) // voor elk beginpunt
		{
			Terrein terrein = new Terrein(t, beginpunten.elementAt(i));
			Gebied gebied = t.getGebied(terrein); // bereken het gebied van dit
			// beginpunt

			Pion[] pionnen = gebied.getPionnen().toArray(new Pion[0]); // zet
			// set
			// om
			// naar
			// array
			// ->
			// spelers
			// kunnen
			// aanspreken
			int aantalSpelers = gebied.getPionnen().size();
			// gebied.getPionnen().size() );

			for (int j = 0; j < aantalSpelers; ++j) {
				boolean gevonden = false;

				for (int s = 0; s < scores.size() && gevonden == false; ++s) {
					if (scores.elementAt(s).getSpeler().getNaam().toString()
							.equals(pionnen[j].getSpeler().toString())) {
						// speler heeft al punten gehad door deze plaatsing ->
						// punten bijtellen
						scores.elementAt(s).setScore(
								scores.elementAt(s).getScore()
										+ simpel.berekenScore(gebied,
												pionnen[j].getSpeler()));
						gevonden = true;
					}
				}

				if (!gevonden) {
					// nieuwe speler die score toegewezen krijgt -> nieuwe score
					// invoegen
					voegScoreToe(pionnen[j].getSpeler(), simpel.berekenScore(
							gebied, pionnen[j].getSpeler())); // bereken score
					// voor elke
					// speler en
					// voeg die toe
				}
			}
		}
		sorteer(); // sorteer de lijst van groot naar klein
	}

	public void voegScoreToe(Speler speler, int score) {
		Score s = new Score(speler, score);
		scores.add(s);
	}

	/**
	 * sorteert de lijst van scores van hoog naar laag
	 */
	public void sorteer() {
		int min, index;
		Score s;

		for (int i = 0; i < scores.size(); ++i) {
			min = scores.elementAt(i).getScore();
			index = i;

			for (int j = i + 1; j < scores.size(); ++j) {
				if (scores.elementAt(j).getScore() < min) {
					min = scores.elementAt(j).getScore();
					index = j;
				}
			}

			s = new Score(scores.elementAt(index).getSpeler(), scores
					.elementAt(index).getScore());
			scores.remove(index);
			scores.add(0, s);
		}
	}

	public boolean bevatSpeler(Speler speler) {
		for (int i = 0; i < scores.size(); ++i) {
			if (scores.elementAt(i).getSpeler().toString().equals(
					speler.toString())) {
				return true;
			}
		}

		return false;
	}

	public int getScore(Speler speler) {
		for (int i = 0; i < scores.size(); ++i) {
			if (scores.elementAt(i).getSpeler().toString().equals(
					speler.toString())) {
				return scores.elementAt(i).getScore();
			}
		}

		return 0;
	}
}
