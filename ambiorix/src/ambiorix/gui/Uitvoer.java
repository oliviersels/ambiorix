package ambiorix.gui;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Speler;

public class Uitvoer {
	HoofdVenster gui;
	
	public Uitvoer(HoofdVenster hv) {
		gui = hv;
	}

	public class ZetTegel implements Runnable {
		Tegel tegel;
		BordPositie positie;
		
		public ZetTegel(Tegel t, BordPositie p) {
			tegel = t;
			positie = p;
		}

		@Override
		public void run() {
			gui.voegTegelToe(tegel, positie);
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
			gui.voegPionToe(terrein.getTegel(), pion, terrein.getPositie()); // TODO: terrein doorgeven ipv tegel en positie
		}
	}
	
	public class SpelerTegelGeven implements Runnable {
		Speler speler;
		Tegel tegel;
		
		public SpelerTegelGeven(Speler s, Tegel t) {
			speler = s;
			tegel = t;
		}

		@Override
		public void run() {
			gui.voegSelectieTegelToe(tegel);
		}
	}
	
	public class SpelerPionGeven implements Runnable {
		Speler speler;
		Pion pion;
		
		public SpelerPionGeven(Speler s, Pion p) {
			speler = s;
			pion = p;
		}

		@Override
		public void run() {
			gui.voegPionToe(pion);
			
		}
	}
	
	public class NeemSpelerTegelAf implements Runnable {
		Speler speler;
		Tegel tegel;
		
		public NeemSpelerTegelAf(Speler s, Tegel t) {
			speler = s;
			tegel = t;
		}

		@Override
		public void run() {
			gui.verwijderSelectieTegel(tegel);
		}
	}
	
	public class NeemSpelerPionAf implements Runnable {
		Speler speler;
		Pion pion;
		
		public NeemSpelerPionAf(Speler s, Pion p) {
			speler = s;
			pion = p;
		}

		@Override
		public void run() {
			gui.verwijderPion(pion);
		}
	}
}
