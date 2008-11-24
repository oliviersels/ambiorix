package ambiorix.tests;
// Javadoc is te vinden op http://junit.org/junit/javadoc/4.5/
import static org.junit.Assert.*;

import org.junit.Test;
import ambiorix.spelers.GewoneSpeler;

public class VoorbeeldTest {

	@Test
	public void testDoeIets() {
		GewoneSpeler s = new GewoneSpeler();
		s.setNaam("Test");
		assertEquals("Speler Naam", s.getNaam(), "Test");
	}

	@Test
	public void testVraagIets() {
		GewoneSpeler s = new GewoneSpeler();
		s.setScore(15);
		assertEquals("Speler Score", s.getScore(), 15);
	}

}
