package ambiorix.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ambiorix.Spel;
import ambiorix.Systeem;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;

public class TestSpel {
	@Test
	public void testActies() {
		// TODO: Nog echte tests toevoegen
		
		Systeem.prepareForTests();
		
		Systeem.getInstantie().startGUI();
		
		/**Spel spel = new Spel();
		Speler s1 = new MenselijkeSpeler();
		s1.setNaam("Jan");
		spel.addSpeler(s1);
		Speler s2 = new MenselijkeSpeler();
		s1.setNaam("Piet");
		spel.addSpeler(s2);
		assertEquals(spel.getAantalSpelers(), 2);
		assertNotNull(spel.getActieveSpeler());
		assertEquals(spel.getActieveSpeler(), s1);
		spel.start();
		try {
			System.out.println("wacht 180 seconden");
			Thread.sleep(180000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spel.stop();*/
	}
	
	private synchronized void sterf() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
