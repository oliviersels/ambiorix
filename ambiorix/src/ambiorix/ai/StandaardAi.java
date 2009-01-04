package ambiorix.ai;

import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;
import ambiorix.util.Punt;

public class StandaardAi extends Ai {
	private Speler speler;

	public StandaardAi() {

	}

	@Override
	public void initialiseer(Spelbord b, Vector<Tegel> tegels,
			Vector<Pion> pionnen, Speler speler) {
		bord = b;
		this.tegels = tegels;
		this.pionnen = pionnen;
		positieLijst = new Vector<Positie>();
		this.speler = speler;
	}

	@Override
	public Vector<Antwoord> berekenZet() {
		Positie huidigMaximum = null;
		int huidigMaximumWaarde = -1, tijdelijkTotaal;
		Vector<Antwoord> antwoorden = new Vector<Antwoord>();

		positieLijst = maakPosities(bord);

		for (int i = 0; i < positieLijst.size(); ++i) {

			tijdelijkTotaal = 0;
			if (positieLijst.elementAt(i).bevatSpeler(speler)) {
				Vector<Punt> beginpunten = tegels.elementAt(0)
						.getGebiedBeginPunten(); // bereken beginpunten vd
													// gebieden
				tijdelijkTotaal = positieLijst.elementAt(i).getScore(speler);

				for (int j = 0; j < beginpunten.size(); ++j) {
					Terrein terrein = new Terrein(tegels.elementAt(0),
							beginpunten.elementAt(j));
					Gebied gebied = tegels.elementAt(0).getGebied(terrein); // bereken
																			// gebieden

					if (gebied.isEigenaar(speler)) {
						if (gebied.getPionnen().size() == 1) {
							if (gebied.isEnigeEigenaar(speler)) {
								// vermenigvuldig met hoge factor
								tijdelijkTotaal += 20 * positieLijst.elementAt(
										i).getScore(speler);
							} else {
								// vermenigvuldigen met (
								// gebied.getOpenZijden().size() * lage factor )
								tijdelijkTotaal += 2 * (5 * gebied
										.getOpenZijden().size() + positieLijst
										.elementAt(i).getScore(speler));
							}
						} else // speler is een vd bezitters
						{
							if (gebied.isVolledig()) {
								// vermenigvuldig met hoge factor
								tijdelijkTotaal += 5 * positieLijst
										.elementAt(i).getScore(speler);
							} else {
								// vermenigvuldigen met (
								// gebied.getOpenZijden().size() * lage factor )
								tijdelijkTotaal += 2 * (2 * gebied
										.getOpenZijden().size() + positieLijst
										.elementAt(i).getScore(speler));
							}
						}
					} else {
						if (gebied.isVolledig()) {
							// vermenigvuldig met lage factor
							tijdelijkTotaal -= 10 * positieLijst.elementAt(i)
									.getScore(speler);
						} else {
							// vermenigvuldigen met (
							// gebied.getOpenZijden().size() * hoge factor )
							tijdelijkTotaal += 2 * (5 * gebied.getOpenZijden()
									.size() + positieLijst.elementAt(i)
									.getScore(speler));
						}
					}
				}

				positieLijst.elementAt(i).setScore(speler, tijdelijkTotaal);
			}

			if (positieLijst.elementAt(i).getScore(speler) > huidigMaximumWaarde) {
				huidigMaximum = positieLijst.elementAt(i);
				huidigMaximumWaarde = positieLijst.elementAt(i)
						.getScore(speler);
			}
		}

		// -- antwoorden aanmaken
		Antwoord a1 = new Antwoord();
		a1.getTegels().add(tegels.elementAt(0));
		antwoorden.add(a1);
		// ------
		Antwoord a2 = new Antwoord();
		a2.getPosities().add(huidigMaximum.getPositie());
		antwoorden.add(a2);
		// ------
		if (huidigMaximum.getPion() != null) {
			Antwoord a3 = new Antwoord();
			a3.getPionnen().add(huidigMaximum.getPion());
			antwoorden.add(a3);
		}
		// ------
		if (huidigMaximum.getLocatie() != null) {
			Antwoord a4 = new Antwoord();
			a4.getTerreinen().add(huidigMaximum.getLocatie());
			antwoorden.add(a4);
		}

		return antwoorden;
	}

	public Vector<Positie> maakPosities(Spelbord bord) {
		Vector<BordPositie> BPLijst = bord.controleerGlobalePlaatsbaarheid(
				tegels.elementAt(0), false);
		Vector<Positie> Plijst = new Vector<Positie>();

		for (int i = 0; i < BPLijst.size(); ++i) {
			// als we nog pionnen over hebben
			if (pionnen.size() > 0) {
				Vector<Punt> beginpunten = tegels.elementAt(0)
						.getGebiedBeginPunten(); // bereken startpunten vd
				// gebieden op de tegel

				for (int j = 0; j < beginpunten.size(); ++j) {
					Terrein terrein = new Terrein(tegels.elementAt(0),
							beginpunten.elementAt(j));
					Positie p = new Positie(BPLijst.elementAt(i));

					if (bord.controleerPlaatsbaarheid(pionnen.elementAt(0),
							terrein)) {
						tegels.elementAt(0).plaatsPion(
								beginpunten.elementAt(j), pionnen.elementAt(0));
						p.setPion(pionnen.elementAt(0)); // zet pion IN POSITIE
						p.setLocatie(terrein); // zet terrein waarop pion
						// geplaatst wordt IN POSITIE
						p.berekenScores(tegels.elementAt(0)); // bereken score
						// van deze
						// Positie
						Plijst.add(p); // voeg Positie toe aan lijst
						// ----
						tegels.elementAt(0).verwijderPion(
								beginpunten.elementAt(j)); // pion opnieuw
															// verwijderen van
															// spelbord
					}
				}
			}

			// lege tegel zetten
			Positie p = new Positie(BPLijst.elementAt(i));
			p.setPion(null);
			p.setLocatie(null);
			p.berekenScores(tegels.elementAt(0));

			Plijst.add(p);
		}

		return Plijst;
	}

	@Override
	public void reset() {
		positieLijst = new Vector<Positie>();
	}
}
