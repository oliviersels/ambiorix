package ambiorix.tests;

import ambiorix.Spel;
import ambiorix.acties.ActieBestuurder;
import ambiorix.acties.basisspel.Pre_GeefPionnen;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestActies {

	@Test
	public void TestBezig() {
		ActieBestuurder ab = new ActieBestuurder(null);
		
		ab.start(null);
		
		assertEquals(false, ab.isBezig());
	}
	
	@Test
	public void TestGeefPionnen() {
		Spel spel = new Spel();
		PionTypeVerzameling.getInstantie().registreerType(new PionType_Volgeling());			
		
		assertEquals(null, spel.getSpeltoolkit().getActieveSpeler());
		assertEquals(0, spel.getAantalSpelers());
		
		Speler speler1 = new MenselijkeSpeler();
		speler1.setNaam("Joske");
		spel.addSpeler(speler1);
		
		assertEquals(speler1, spel.getSpeltoolkit().getActieveSpeler());
		assertEquals(1, spel.getAantalSpelers());
		
		Speler speler2 = new MenselijkeSpeler();
		speler2.setNaam("Jefke");
		spel.addSpeler(speler2);
		
		assertEquals(speler1, spel.getSpeltoolkit().getActieveSpeler());
		assertEquals(2, spel.getAantalSpelers());
		
		ActieBestuurder ab = spel.getSpeltoolkit().getActiebestuurder();	
		
		spel.start(new Pre_GeefPionnen());
		
		assertEquals(7, speler1.getAantalPionnen());	
		assertEquals(7, speler2.getAantalPionnen());	
		
	}
}
