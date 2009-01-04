package ambiorix.spelbord.scoreberekenaars;

import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelers.Speler;

public class LavaScoreBerekenaar extends SimpelScoreBerekenaar {
	@Override
	public int berekenScore(Gebied gebied, Speler speler) {
		// enkel iets speciaal voor wegen
		if (gebied.getType() != TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Weg"))
			return super.berekenScore(gebied, speler);

		// als het spel nog niet gedaan is moet de weg volledig zijn voor punten
		// te kunnen krijgen
		if (!eindeSpel && (!gebied.isVolledig()))
			return 0;

		int result = 0;

		result = gebied.getTegels().size();

		// we gaan de score verdubbelen ALS er een tegel met lava onderdeel is
		// van onze mooie weg
		// zoek dus eerst of er een tegel met lava in het gebied zit :)
		for (Tegel tegel : gebied.getTegels()) {
			if (tegel.getType() == TegelTypeVerzameling.getInstantie().getType(
					"TegelType_LavaMetWeg"))
				result *= 2;
		}

		return result;
	}
}
