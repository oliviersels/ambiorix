package ambiorix.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ambiorix.systeem;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelGebiedBeheerder;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.TerreinType;
import ambiorix.util.Punt;

public class TestTegel {

	@Test
	public void testSetBuur() 
	{
		systeem.prepareForTests();
		
		Tegel t = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK") );
		
		Tegel buur = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		
		t.setBuur(buur, Tegel.RICHTING.RECHTS);
		
		assertEquals("buren geupdate : ", t.getBuur(Tegel.RICHTING.RECHTS), buur);
	}
	
	@Test
	public void testKanBuurAccepteren()
	{
		systeem.prepareForTests();
		
		// testen met 2 tegels onder elkaar (basic)
		Tegel t = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		
		Tegel buur = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		
		assertEquals("Foute buren : ",t.kanBuurAccepteren(buur, Tegel.RICHTING.RECHTS), false);
		
		// testen na rotatie
		// ------------------
		t.setRotatie(90);
		assertEquals("Juiste buren : ",t.kanBuurAccepteren(buur, Tegel.RICHTING.RECHTS), true);
		
		// testen met 9 tegels
		//  weg      weg90     weg
		// klooster  weg180  weg270
		// weg270    weg90   weg
		
		Tegel linksboven = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		Tegel middenboven = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		middenboven.setRotatie(90);
		Tegel rechtsboven = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		
		Tegel linksmidden = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK") );
		Tegel middenmidden = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		middenmidden.setRotatie(180);
		Tegel rechtsmidden = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		rechtsmidden.setRotatie(270);
		
		Tegel linksonder = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		linksonder.setRotatie(270);
		Tegel middenonder = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		middenonder.setRotatie(90);
		Tegel rechtsonder = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		
		
		assertEquals("Linksboven + middenboven : ",linksboven.kanBuurAccepteren(middenboven, Tegel.RICHTING.RECHTS), true );
		linksboven.setBuur(middenboven, Tegel.RICHTING.RECHTS);
		
		assertEquals("Middenboven + rechtsboven : ",middenboven.kanBuurAccepteren(rechtsboven, Tegel.RICHTING.RECHTS), true );
		middenboven.setBuur(rechtsboven, Tegel.RICHTING.RECHTS);	
		
		assertEquals("Rechtsboven + rechtsmidden : ",rechtsboven.kanBuurAccepteren(rechtsmidden, Tegel.RICHTING.ONDER), true );
		rechtsboven.setBuur(rechtsmidden, Tegel.RICHTING.ONDER);		
		
		assertEquals("Rechtsmidden + rechtsonder : ",rechtsmidden.kanBuurAccepteren(rechtsonder, Tegel.RICHTING.ONDER), true );
		rechtsmidden.setBuur(rechtsonder, Tegel.RICHTING.ONDER);		
		
		assertEquals("Rechtsonder + middenonder : ",rechtsonder.kanBuurAccepteren(middenonder, Tegel.RICHTING.LINKS), true );
		rechtsonder.setBuur(middenonder, Tegel.RICHTING.LINKS);			
		
		assertEquals("middenonder + linksonder : ",middenonder.kanBuurAccepteren(linksonder, Tegel.RICHTING.LINKS), true );
		middenonder.setBuur(linksonder, Tegel.RICHTING.LINKS);				
		
		assertEquals("linksonder + linksmidden : ",linksonder.kanBuurAccepteren(linksmidden, Tegel.RICHTING.BOVEN), true );
		linksonder.setBuur(linksmidden, Tegel.RICHTING.BOVEN);			
	
		// structuur is opgezet, nu kunnen we de middenste testen
		assertEquals("Test goed", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), true);
		assertEquals("Test goed", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), true);
		assertEquals("Test goed", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), true);
		assertEquals("Test goed", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), true);
		
		middenmidden.setRotatie( 0 );
		assertEquals("Test goed", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), false);
		assertEquals("Test goed", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), false);
		assertEquals("Test goed", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), false);
		assertEquals("Test goed", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), false);
		
		middenmidden.setRotatie( 90 );
		assertEquals("Test goed", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), false);
		assertEquals("Test goed", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), false);
		assertEquals("Test goed", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), false);
		assertEquals("Test goed", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), false);
		
		middenmidden.setRotatie( 270 );
		assertEquals("Test goed", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), false);
		assertEquals("Test goed", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), false);
		assertEquals("Test goed", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), false);
		assertEquals("Test goed", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), false);

		
	}
	

	@Test
	public void testPlaatsPion() 
	{
		systeem.prepareForTests();
		Tegel t = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK") );	
		
		Pion p = new Pion(0, PionTypeVerzameling.getInstantie().getType("PionType_Volgeling") );
		t.PlaatsPion(new Punt(1,2), p);
		
		assertEquals( "pion correct gezet", t.getPion( new Punt(1,2) ), p );
		
		// meerdere pionnen op 1 tegel
		Pion p2 = new Pion(0, PionTypeVerzameling.getInstantie().getType("PionType_Volgeling") );
		t.PlaatsPion(new Punt(1,1), p2);

		assertEquals( "pion correct gezet", t.getPion( new Punt(1,1) ), p2 );		
	}

	@Test
	public void testSetRotatie() 
	{
		// - terrein moet aangepast zijn
		// - tegelGebiedBeheerder moet aangepast zijn
		
		systeem.prepareForTests();
		
		Tegel t = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK") );		
		TerreinType[][] origineelTerrein = t.getTerrein();
		TegelGebiedBeheerder origineleBeheerder = t.getGebiedBeheerder();
		
		t.setRotatie(90);
		assertNotSame("terreinkopie verschilt : ", t.getTerrein(), origineelTerrein);
		assertNotSame("tegelbeheerder verschilt :  ", t.getGebiedBeheerder(), origineleBeheerder);
		

		// - mag niet lukken als er al buren gezet zijn !
		t = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK") );
		int origineleRotatie = t.getRotatie();
		
		Tegel t2 = new Tegel( TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK") );
		
		t.setBuur(t2, Tegel.RICHTING.BOVEN);
		
		t.setRotatie(180);
		assertEquals("rotatie is nog steeds zelfde : ", origineleRotatie, t.getRotatie());
	}

}
