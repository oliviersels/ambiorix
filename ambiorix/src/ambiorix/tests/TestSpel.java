package ambiorix.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ambiorix.Spel;
import ambiorix.systeem;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;

public class TestSpel {
	@Test
	public void testActies() {
		// TODO: Nog echte tests toevoegen
		
		systeem.prepareForTests();
		
		Spel spel = new Spel();
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
			System.out.println("wacht 20 seconden");
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spel.stop();
	}
}
