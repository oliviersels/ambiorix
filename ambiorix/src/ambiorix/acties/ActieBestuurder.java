package ambiorix.acties;

/**
 * ActieBestuurder zorgt voor het starten van de Acties en het achtereen
 * aanroepen van de verschillende Acties. Acties zelf beslissen de echte game
 * flow.
 * 
 * @author olivier
 * 
 */
public class ActieBestuurder implements Runnable {

	private Actie volgende;
	private Thread actieThread;

	public ActieBestuurder() {
		volgende = null;
		actieThread = null;
	}

	public void start(Actie start) {
		if (actieThread == null) {
			volgende = start;
			actieThread = new Thread(this);
			actieThread.start();
		}

		// TODO: Wachten op een notify
	}

	/**
	 * Aanroepen indien het spel eindigt (buiten de normale game flow) Dit is
	 * een noodrem: breekt de huidige actie onmiddelijk af!
	 */
	public void stop() {
		if (actieThread != null && actieThread.isAlive()) {
			actieThread.interrupt();
			actieThread = null;
		}
	}

	@Override
	public void run() {
		while (volgende != null) { // Dan is het gedaan
			volgende = volgende.start();
		}
	}
}
