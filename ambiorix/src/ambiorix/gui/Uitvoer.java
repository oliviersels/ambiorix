package ambiorix.gui;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;

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
			hv.voegTegelToe(tegel, positie);
		}
	}
	
	public class ZetPion implements Runnable {
		Pion pion;
		Terrein terrein;
		
		public ZetPion(Pion p, Terrein t) {
			terrein = t;
			pion = p;
		}

		@Override
		public void run() {
			HoofdVenster hv = HoofdVenster.geefInstantie();
			hv.voegPionToe(terrein.getTegel(), pion, terrein.getPositie()); // TODO: terrein doorgeven ipv tegel en positie
		}
	}

}
