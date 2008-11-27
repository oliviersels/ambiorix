package ambiorix.spelers;

public class GewoneSpeler extends Speler implements InvoerLuisteraar {

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
		
		/* JensRunnable jr = new JensRunnable(this);
		 * new Thread(jr).start();
		 */
		
		return null;
	}

	@Override
	public void InvoerGebeurtenis(Antwoord a) {
		// Ik handel het vanaf hier terug af.
		
	}
}
