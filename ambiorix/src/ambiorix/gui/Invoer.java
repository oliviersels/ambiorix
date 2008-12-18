package ambiorix.gui;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;

public class Invoer {
	HoofdVenster gui;
	
	public Invoer(HoofdVenster hv) {
		gui = hv;
	}
	
	public class SelecteerSpelerPion implements Runnable, PionLuisteraar {
		InvoerLuisteraar invoerLuisteraar;
		Speler speler;
		
		public SelecteerSpelerPion(InvoerLuisteraar il, Speler s) {
			invoerLuisteraar = il;
			speler = s;
		}
		
		public void opruimen() {
			gui.verwijderPionLuisteraar(this);
		}
		
		@Override
		public void run() {
			gui.voegPionLuisteraarToe(this);
			
		}

		@Override
		public void geklikt(PionGebeurtenis pg) {
			Antwoord a = new Antwoord();
			a.getPionnen().add((Pion) pg.pion);
			invoerLuisteraar.invoerGebeurtenis(a);
		}

	}

	public class SelecteerTegelGebied implements Runnable, TegelLuisteraar {
		InvoerLuisteraar invoerLuisteraar;
		
		public SelecteerTegelGebied(InvoerLuisteraar il) {
			invoerLuisteraar = il;
		}
		
		public void opruimen() {
			gui.geefTegelVeld().removeTegelKlikLuisteraar(this);
		}
		
		@Override
		public void run() {
			gui.geefTegelVeld().addTegelKlikLuisteraar(this);
			
		}
		@Override
		public void geklikt(TegelGebeurtenis tg) {
			Antwoord a = new Antwoord();
			a.getTegels().add((Tegel) tg.tegel);
			a.getTerreinen().add((Terrein) tg.terrein);
			invoerLuisteraar.invoerGebeurtenis(a);
		}

		@Override
		public void bewogen(TegelGebeurtenis tg) {
			// TODO Auto-generated method stub
			
		}
	}

	public class SelecteerSpelerTegel implements Runnable, TegelLuisteraar {
		InvoerLuisteraar invoerLuisteraar;
		Speler speler;
		
		public SelecteerSpelerTegel(InvoerLuisteraar il, Speler s) {
			invoerLuisteraar = il;
			speler = s;
		}
		
		public void opruimen() {
			gui.verwijderSelectieTegelLuisteraar(this);
		}
		
		@Override
		public void run() {
			gui.voegSelectieTegelLuisteraarToe(this);
			
		}

		@Override
		public void geklikt(TegelGebeurtenis tg) {
			Antwoord a = new Antwoord();
			a.getTegels().add((Tegel) tg.tegel);
			invoerLuisteraar.invoerGebeurtenis(a);
		}

		@Override
		public void bewogen(TegelGebeurtenis tg) {
			// TODO Auto-generated method stub
			
		}

	}

	public class SelecteerBordPositie implements Runnable, TegelGeestLuisteraar {
		InvoerLuisteraar invoerluisteraar;
		
		public SelecteerBordPositie(InvoerLuisteraar il) {
			invoerluisteraar = il;
		}
		
		public void opruimen() {
			gui.geefTegelVeld().removeTegelGeestLuisteraar(this);
		}
		
		@Override
		public void run() {
			gui.geefTegelVeld().addTegelGeestLuisteraar(this);
		}

		@Override
		public void geklikt(TegelGeestGebeurtenis gtg) {
			Antwoord a = new Antwoord();
			a.getPosities().add(new BordPositie((Tegel) gtg.tegel, gtg.richting));
			invoerluisteraar.invoerGebeurtenis(a);
			
		}
	}
}
