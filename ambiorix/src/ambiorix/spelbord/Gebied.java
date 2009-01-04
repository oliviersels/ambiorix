package ambiorix.spelbord;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import ambiorix.spelers.Speler;

public class Gebied {
	private TerreinType type;
	private Vector<Terrein> terreinStukken = new Vector<Terrein>();
	private Vector<BordPositie> openZijden = new Vector<BordPositie>();
	private HashMap<Pion, Terrein> pionnen = new HashMap<Pion, Terrein>();
	private Vector<Tegel> tegels = new Vector<Tegel>();

	public Gebied() {

	}

	public void voegTerreinToe(Terrein h) {
		terreinStukken.add(h);

		if (!tegels.contains(h.getTegel()))
			tegels.add(h.getTegel());
	}

	public TerreinType getType() {
		return type;
	}

	public void setType(TerreinType type) {
		this.type = type;
	}

	/*
	 * Geef true als het gebied helemaal afgesloten is aan alle kanten (geen
	 * open zijden meer)
	 */
	public boolean isVolledig() {
		return (openZijden.size() == 0);
	}

	public void voegOpenZijdeToe(BordPositie zijde) {
		// als hij er al inzit, niet nogmaals toevoegen
		if (getZijdeIndex(zijde) == -1)
			openZijden.add(zijde);
	}

	private int getZijdeIndex(BordPositie positie) {
		BordPositie bp = null;
		for (int i = 0; i < openZijden.size(); i++) {
			bp = openZijden.get(i);
			if ((bp.getBuur() == positie.getBuur())
					&& (bp.getRichting() == positie.getRichting()))
				return i;
		}

		return -1;
	}

	public void voegPionToe(Pion p, Terrein t) {
		if (!pionnen.containsKey(p))
			pionnen.put(p, t);
	}

	/*
	 * private int getPionIndex(Pion p) { // elke pion heeft unieke ID, dus daar
	 * handig gebruik van maken Set<Pion> pionnenKeys = pionnen.keySet();
	 * 
	 * Pion pion = null; for( int i = 0; i < ps.size(); i++) { pion =
	 * pionnenKeys.get(i); if( (pion.getID() == positie.getBuur()) &&
	 * (bp.getRichting() == positie.getRichting()) ) return i; } }
	 */

	/*
	 * Rekenintensieve functie. Gebruik enkel als het gebied niet te groot is !
	 */
	public boolean bevatTerrein(Terrein terrein) {
		for (Terrein test : terreinStukken) {
			if (terrein.getTegel() == test.getTegel()) {
				if (terrein.getPositie().toString().equals(
						test.getPositie().toString()))
					return true;
			}
		}

		return false;
	}

	public Vector<Terrein> getTerreinStukken() {
		return terreinStukken;
	}

	public Vector<Tegel> getTegels() {
		return tegels;
	}

	public HashMap<Pion, Terrein> getPionnenEnPosities() {
		return pionnen;
	}

	public Set<Pion> getPionnen() {
		return pionnen.keySet();
	}

	public Vector<BordPositie> getOpenZijden() {
		return openZijden;
	}

	/**
	 * Geeft terug of een Speler een of meerdere pionnen in het gebied heeft
	 */
	public boolean isGevestigdIn(Speler speler) {
		Pion[] pionnenLijst = getPionnen().toArray(new Pion[0]); // zet set om
		// naar
		// array ->
		// spelers
		// kunnen
		// aanspreken
		int aantalSpelers = getPionnen().size();

		for (int j = 0; j < aantalSpelers; ++j) {
			if (pionnenLijst[j].getSpeler().getNaam().equals(speler.getNaam())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Geeft terug of een Speler eigenaar is van het gebied
	 */
	public boolean isEigenaar(Speler speler) {
		Pion[] pionnenLijst = getPionnen().toArray(new Pion[0]); // zet set om
		// naar
		// array ->
		// spelers
		// kunnen
		// aanspreken
		int aantalSpelers = getPionnen().size(); // aantal elementen in de array
		int aantalPionnen = speler.getAantalPionnen(); // aantal pionnen van de
		// speler in het gebied

		for (int j = 0; j < aantalSpelers; ++j) {
			if (pionnenLijst[j].getSpeler().getAantalPionnen() > aantalPionnen) {
				return false;
			}
		}

		return true;
	}

	public boolean isEnigeEigenaar(Speler speler) {
		HashMap<Speler, Integer> pionnenPerSpeler = getPionnenPerSpeler();
		int besteAantal = 0;

		int spelerAantal = -1;

		if (pionnenPerSpeler.get(speler) != null)
			spelerAantal = pionnenPerSpeler.get(speler);

		Set<Speler> spelers = pionnenPerSpeler.keySet();
		for (Speler spelerIt : spelers) {
			if (pionnenPerSpeler.get(spelerIt) > besteAantal) {
				besteAantal = pionnenPerSpeler.get(spelerIt);
			}
		}

		/*
		 * if( besteAantal == spelerAantal ) return 0; if( besteAantal >
		 * spelerAantal ) return -1; else // speler heeft wel beste return 1;
		 */

		if (besteAantal < spelerAantal) {
			return true;
		}

		return false;
	}

	private HashMap<Speler, Integer> getPionnenPerSpeler() {
		HashMap<Speler, Integer> output = new HashMap<Speler, Integer>();

		Set<Pion> pionLijst = getPionnen();
		for (Pion pion : pionLijst) {
			if (output.get(pion.getSpeler()) == null)
				output.put(pion.getSpeler(), 1);
			else
				output.put(pion.getSpeler(), output.get(pion.getSpeler()) + 1);
		}

		return output;
	}
}
