package ambiorix.tests;

import ambiorix.spelers.MenselijkeSpeler;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestPlaatsTegel {
	@Test
	public void testPlaatsTegel() {
		MenselijkeSpeler speler = new MenselijkeSpeler();
		
		assertNotNull(speler.plaatsTegel().getTegels().get(0));
		assertNotNull(speler.plaatsTegel().getPosities().get(0));
	}
}
