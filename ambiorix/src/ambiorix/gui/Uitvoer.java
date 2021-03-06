package ambiorix.gui;

import javax.swing.JOptionPane;

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
			gui.voegPionToe(terrein.getTegel(), pion, terrein.getPositie());
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
			gui.voegTegelToeAanSpeler(tegel, speler);
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
			gui.voegPionToeAanSpeler(pion, speler);
			gui.updateScores();
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
			gui.verwijderTegelVanSpeler(tegel, speler);
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
			gui.verwijderPionVanSpeler(pion, speler);
			gui.updateScores();
		}
	}

	public class ZetActieveSpeler implements Runnable {
		Speler speler;

		public ZetActieveSpeler(Speler s) {
			speler = s;
		}

		@Override
		public void run() {
			gui.zetActieveSpeler(speler);
		}

	}

	public class UpdateScore implements Runnable {
		public UpdateScore() {
		}

		@Override
		public void run() {
			gui.updateScores();
		}

	}

	public class VerwijderPion implements Runnable {
		Pion pion;

		public VerwijderPion(Pion p) {
			pion = p;
		}

		@Override
		public void run() {
			gui.verwijderPionVanSpelbord(pion);
		}
	}

	public class VerwijderTegel implements Runnable {
		Tegel tegel;

		public VerwijderTegel(Tegel t) {
			tegel = t;
		}

		@Override
		public void run() {
			gui.verwijderTegel(tegel);
		}
	}

	public class EindeSpel implements Runnable {

		public EindeSpel() {
		}

		@Override
		public void run() {
			JOptionPane.showMessageDialog(gui, "Het spel is gedaan!",
					"Gedaan!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
