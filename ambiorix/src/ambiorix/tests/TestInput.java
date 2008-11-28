package ambiorix.tests;

import ambiorix.systeem;
import ambiorix.gui.HoofdVenster;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.MenselijkeSpeler;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestInput {
	private MenselijkeSpeler speler;
	
	@Before
	public void setUp() throws Exception {
		systeem.prepareForTests();
		
		speler = new MenselijkeSpeler();
		HoofdVenster hv = HoofdVenster.geefInstantie();
		
		hv.voegTegelToe(2, 2, new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK")));
		hv.voegTegelToe(3, 2, new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK")));
	}
	
	@Test
	public void selecteerBordPositie() {
		Antwoord a = speler.selecteerBordPositie();
		assertNotNull(a);
		assertEquals(a.getPosities().size(), 1);
		assertNotNull(a.getPosities().get(0));
		assertEquals(a.getPionnen().size(), 0);
		assertEquals(a.getTegels().size(), 0);
		assertEquals(a.getTerreinen().size(), 0);
	}
	@Test
	public void selecteerSpelerTegel() {
		Antwoord a = speler.selecteerSpelerTegel();
		assertNotNull(a);
		assertEquals(a.getTegels().size(), 1);
		assertNotNull(a.getTegels().get(0));
		assertEquals(a.getPionnen().size(), 0);
		assertEquals(a.getPosities().size(), 0);
		assertEquals(a.getTerreinen().size(), 0);
	}
	
	@Test
	public void selecteerTegelGebied() {
		Antwoord a = speler.selecteerTegelGebied();
		assertNotNull(a);
		assertEquals(a.getTerreinen().size(), 1);
		assertNotNull(a.getTerreinen().get(0));
		assertEquals(a.getPionnen().size(), 0);
		assertEquals(a.getPosities().size(), 0);
		assertEquals(a.getTegels().size(), 0);
	}
	
	@Test
	public void selecteerSpelerPion() {
		Antwoord a = speler.selecteerSpelerPion();
		assertNotNull(a);
		assertEquals(a.getPionnen().size(), 1);
		assertNotNull(a.getPionnen().get(0));
		assertEquals(a.getTerreinen().size(), 0);
		assertEquals(a.getPosities().size(), 0);
		assertEquals(a.getTegels().size(), 0);
	}
}
