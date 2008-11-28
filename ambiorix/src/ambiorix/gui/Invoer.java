package ambiorix.gui;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Tegel;
import ambiorix.spelers.Antwoord;

public class Invoer {
	public class PlaatsTegel implements Runnable, TegelGeestLuisteraar {
		InvoerLuisteraar invoerluisteraar;
		
		public PlaatsTegel(InvoerLuisteraar il) {
			invoerluisteraar = il;
		}
		
		public void stopLuisteren() {
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
			System.out.println(gtg.tegel + " " + gtg.richting.toString());
			invoerluisteraar.invoerGebeurtenis(a);
			
		}
	}
	// TODO Poison afhalen van alice cooper
}
