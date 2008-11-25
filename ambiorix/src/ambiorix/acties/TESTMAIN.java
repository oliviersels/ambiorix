package ambiorix.acties;

import ambiorix.acties.specifiek.GeefTegel;

public class TESTMAIN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ActieBestuurder ab = new ActieBestuurder();
			ab.start(new GeefTegel());
			
			DRAAD d = null;
			d = new DRAAD(ab, 1);
			d.join();
			d = new DRAAD(ab, 2);
			d.join();
			d = new DRAAD(ab, 3);
			d.join();
			System.out.println("---- DONE ----");
		} catch (InterruptedException e) {}
	}

}
