package ambiorix.gui;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;

public class Invoer {
	public class SelecteerSpelerPion implements Runnable {
		InvoerLuisteraar invoerLuisteraar;
		Speler speler;
		
		public SelecteerSpelerPion(InvoerLuisteraar il, Speler s) {
			invoerLuisteraar = il;
			speler = s;
		}
		
		public void opruimen() {
			// TODO Opruimen
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

	}

	public class SelecteerTegelGebied implements Runnable {
		InvoerLuisteraar invoerLuisteraar;
		
		public SelecteerTegelGebied(InvoerLuisteraar il) {
			invoerLuisteraar = il;
		}
		
		public void opruimen() {
			// TODO Opruimen
		}
		
			
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}

	public class SelecteerSpelerTegel implements Runnable {
		InvoerLuisteraar invoerLuisteraar;
		Speler speler;
		
		public SelecteerSpelerTegel(InvoerLuisteraar il, Speler s) {
			invoerLuisteraar = il;
			speler = s;
		}
		
		public void opruimen() {
			// TODO opruimen
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

	}

	public class SelecteerBordPositie implements Runnable, TegelGeestLuisteraar {
		InvoerLuisteraar invoerluisteraar;
		
		public SelecteerBordPositie(InvoerLuisteraar il) {
			invoerluisteraar = il;
		}
		
		public void opruimen() {
			HoofdVenster.geefInstantie().geefTegelVeld().removeTegelGeestLuisteraar(this);
		}
		
		@Override
		public void run() {
			HoofdVenster.geefInstantie().geefTegelVeld().addTegelGeestLuisteraar(this);
		}

		@Override
		public void geklikt(TegelGeestGebeurtenis gtg) {
			Antwoord a = new Antwoord();
			a.getPosities().add(new BordPositie(gtg.tegel, gtg.richting));
			invoerluisteraar.invoerGebeurtenis(a);
			
		}
	}
	// TODO Poison afhalen van alice cooper
}
