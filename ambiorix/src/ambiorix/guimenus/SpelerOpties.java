package ambiorix.guimenus;

import java.awt.Color;

public class SpelerOpties {
	public String naam;
	public Color kleur;
	public SpelerOptiesType type;

	public static enum SpelerOptiesType {
		GESLOTEN, HOT_SEAT, AI
	};
}
