package ambiorix.spelbord;

import ambiorix.util.Punt;

/*
 * Interface bedoeld voor Tegel.
 * Specifieert enkel read-only functies, zodat we polymorfisme kunnen gebruiken om er zeker van te zijn
 * dat niet zomaar alle classes aan Tegel kunnen.
 */
public interface TegelBasis {
	public enum RICHTING {
		BOVEN, RECHTS, ONDER, LINKS;

		public RICHTING getTegenovergestelde() {
			if (this == RICHTING.BOVEN)
				return RICHTING.ONDER;
			if (this == RICHTING.ONDER)
				return RICHTING.BOVEN;
			if (this == RICHTING.RECHTS)
				return RICHTING.LINKS;
			if (this == RICHTING.LINKS)
				return RICHTING.RECHTS;

			// anders doet compiler moeilijk. Komt hier nooit.
			return null;
		}

		public static RICHTING fromString(String input) {
			if (input == RICHTING.BOVEN.name())
				return RICHTING.BOVEN;
			if (input == RICHTING.ONDER.name())
				return RICHTING.ONDER;
			if (input == RICHTING.RECHTS.name())
				return RICHTING.RECHTS;
			if (input == RICHTING.LINKS.name())
				return RICHTING.LINKS;

			return null;
		}
	}

	public int getID();

	public TegelType getType();

	public int getRotatie();

	public void setRotatie(int rotatie);

	public boolean isDraaibaar();

	public TerreinType getTerreinType(Punt locatie);

	public int getTerreinBreedte();

	public int getTerreinHoogte();

	public TerreinBasis getTerrein(Punt locatie);

	public boolean kanBuurAccepteren(Tegel buur, RICHTING richting);
}
