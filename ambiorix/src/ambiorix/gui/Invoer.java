package ambiorix.gui;

import ambiorix.spelers.Antwoord;

public class Invoer {
	public class PlaatsTegel implements Runnable, TegelKlikLuisteraar {
		InvoerLuisteraar invoerluisteraar;
		
		public PlaatsTegel(InvoerLuisteraar il) {
			invoerluisteraar = il;
		}
		
		public void stopLuisteren() {
			HoofdVenster.geefInstantie().geefTegelVeld().removeTegelKlikLuisteraar(this);
		}
		
		@Override
		public void run() {
			HoofdVenster.geefInstantie().geefTegelVeld().addTegelKlikLuisteraar(this);
		}

		@Override
		public void geklikt(TegelGebeurtenis tg) {
			Antwoord a = new Antwoord();
			a.getTegels().add(tg.tegel);
			invoerluisteraar.invoerGebeurtenis(a);
		}
	}
}
