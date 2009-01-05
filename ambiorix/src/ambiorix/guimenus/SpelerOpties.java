package ambiorix.guimenus;

import java.awt.Color;
/**
 * Klasse om al de opties van een speler weer te geven.
 * @author Jens
 *
 */
public class SpelerOpties {
	public String naam;
	public Color kleur;
	public SpelerOptiesType type;

	public static enum SpelerOptiesType {
		GESLOTEN, HOT_SEAT, AI
	};
}
