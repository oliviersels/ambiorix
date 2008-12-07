package ambiorix.gui;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Tegel;

public class Uitvoer {

	public class ZetTegel implements Runnable {
		Tegel tegel;
		BordPositie positie;
		
		public ZetTegel(Tegel t, BordPositie p) {
			tegel = t;
			positie = p;
		}

		@Override
		public void run() {
			HoofdVenster hv = HoofdVenster.geefInstantie();
			hv.voegTegelToe(tegel, positie); // FIXME Wachten op Robin tot BordPositie coordinaatgebaseerd is. En Wachten op Jens, want voegTegelToe moet een bordpositie aannemen ipv coordinaten.
		}
	}

}
