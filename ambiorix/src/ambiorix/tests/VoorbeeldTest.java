package ambiorix.tests;
// Javadoc is te vinden op http://junit.org/junit/javadoc/4.5/
import static org.junit.Assert.*;

import org.junit.Test;
import ambiorix.spelers.MenselijkeSpeler;

public class VoorbeeldTest {

	@Test
	public void testDoeIets() {
		MenselijkeSpeler s = new MenselijkeSpeler();
		s.setNaam("Test");
		assertEquals("Speler Naam", s.getNaam(), "Test");
	}

	@Test
	public void testVraagIets() {
		MenselijkeSpeler s = new MenselijkeSpeler();
		s.setScore(15);
		assertEquals("Speler Score", s.getScore(), 15);
	}

}
