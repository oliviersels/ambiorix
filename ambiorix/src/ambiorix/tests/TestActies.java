package ambiorix.tests;

import org.junit.Test;

import ambiorix.Spel;
import ambiorix.SpelToolkit;
import ambiorix.acties.specifiek.GeefTegel;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;

public class TestActies {
	@Test
	public void testActies() {
		// TODO: Nog echte tests toevoegen
		
		Spel spel = new Spel();
		PionTypeVerzameling.getInstantie().registreerType(new PionType_Volgeling());			
		Speler s1 = new MenselijkeSpeler();
		s1.setNaam("Jan");
		spel.addSpeler(s1);
		Speler s2 = new MenselijkeSpeler();
		s1.setNaam("Piet");
		spel.addSpeler(s2);
		SpelToolkit kit = new SpelToolkit(spel.getSpelers());
		
		GeefTegel a1 = new GeefTegel(kit, null);
		kit.start(a1);
		try {
			System.out.println("test");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kit.stop();
	}
}
