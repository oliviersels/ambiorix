package ambiorix.tests;

import org.junit.Test;

import ambiorix.Spel;
import ambiorix.systeem;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;

public class TestActies {
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
		spel.start();
		try {
			System.out.println("test");
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spel.stop();
	}
}
