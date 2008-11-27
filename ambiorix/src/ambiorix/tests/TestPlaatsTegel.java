package ambiorix.tests;

import ambiorix.spelers.GewoneSpeler;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestPlaatsTegel {
	@Test
	public void testPlaatsTegel() {
		GewoneSpeler speler = new GewoneSpeler();
		
		assertNotNull(speler.plaatsTegel().getTegels().get(0));
		assertNotNull(speler.plaatsTegel().getPosities().get(0));
	}
}
