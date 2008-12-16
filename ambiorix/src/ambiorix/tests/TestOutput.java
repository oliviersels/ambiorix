package ambiorix.tests;

import org.junit.Before;
import org.junit.Test;

import ambiorix.systeem;
import ambiorix.gui.HoofdVenster;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.MenselijkeSpeler;

public class TestOutput {
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
	public void zetTegel() {
		Antwoord a;
		try {
			a = speler.selecteerBordPositie();
		} catch (InterruptedException e) {
			System.out.println("Spel afgelopen op een schone manier");
			return;
		}
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		speler.zetTegel(t, a.getPosities().get(0));
		try {
			speler.selecteerBordPositie();
		} catch (InterruptedException e) {
			System.out.println("Spel afgelopen op een schone manier");
			return;
		}
	}
}
