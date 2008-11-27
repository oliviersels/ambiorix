package ambiorix.spelers;

import javax.swing.SwingUtilities;

import ambiorix.gui.Invoer;
import ambiorix.gui.InvoerLuisteraar;

public class GewoneSpeler extends Speler implements InvoerLuisteraar {
	Antwoord huidigAntwoord = null;
	
	@Override
	public void doeIets() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object vraagIets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bevestigTegel(boolean toegestaan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Antwoord plaatsTegel() {
		/* We zullen aan de GUI moeten vragen waar de tegel geplaatst zal worden */
		
		Invoer i = new Invoer();
		Invoer.PlaatsTegel run = i.new PlaatsTegel(this);
		SwingUtilities.invokeLater(run);
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		run.stopLuisteren();
		
		return huidigAntwoord;
	}

	@Override
	public synchronized void invoerGebeurtenis(Antwoord a) {
		// Ik handel het vanaf hier terug af.
		huidigAntwoord = a;
		notifyAll();
	}
}
