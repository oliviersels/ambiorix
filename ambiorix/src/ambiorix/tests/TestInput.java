package ambiorix.tests;

import ambiorix.spelers.Antwoord;
import ambiorix.spelers.MenselijkeSpeler;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestInput {
	private MenselijkeSpeler speler;
	
	@Before
	public void setUp() throws Exception {
		speler = new MenselijkeSpeler();
	}
	
	@Test
	public void selecteerBordPositie() {
		Antwoord a = speler.selecteerBordPositie();
		assertEquals(a.getPosities().size(), 1);
		assertNotNull(a.getPosities().get(0));
		assertEquals(a.getPionnen().size(), 0);
		assertEquals(a.getTegels().size(), 0);
		assertEquals(a.getTerreinen().size(), 0);
	}
	@Test
	public void selecteerSpelerTegel() {
		Antwoord a = speler.selecteerSpelerTegel();
		assertEquals(a.getTegels().size(), 1);
		assertNotNull(a.getTegels().get(0));
		assertEquals(a.getPionnen().size(), 0);
		assertEquals(a.getPosities().size(), 0);
		assertEquals(a.getTerreinen().size(), 0);
	}
}
