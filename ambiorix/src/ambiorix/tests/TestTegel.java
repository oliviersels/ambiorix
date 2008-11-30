package ambiorix.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ambiorix.systeem;
import ambiorix.spelbord.BordPositie;
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
		
		// testen dit via het spelbord
		Spelbord bord = new Spelbord();
		bord.setBegintegel(t);
		bord.setTegelAantal("TegelType_GGGGK", 5);
		

		Tegel buur = bord.getVolgendeTegel();
		
		assertEquals( "kan geplaatst worden : ", bord.controleerPlaatsbaarheid(buur, new BordPositie(t, Tegel.RICHTING.RECHTS) ), true );
		bord.plaatsTegel(buur, new BordPositie(t, Tegel.RICHTING.RECHTS) );
		
		//t.print();
		//buur.print();

		assertEquals("buren geupdate : ", t.getBuur(Tegel.RICHTING.RECHTS), buur);
		assertEquals("buren geupdate : ", buur.getBuur(Tegel.RICHTING.LINKS), t);
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
		
		Spelbord bord = new Spelbord();
		
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
		
		bord.setBegintegel(linksboven);
		
		middenboven.setID(1);
		rechtsboven.setID(2);
		rechtsmidden.setID(3);
		rechtsonder.setID(4);
		middenonder.setID(5);
		linksonder.setID(6);
		linksmidden.setID(7);
		
		assertEquals("Linksboven + middenboven : ",linksboven.kanBuurAccepteren(middenboven, Tegel.RICHTING.RECHTS), true );
		//linksboven.setBuur(middenboven, Tegel.RICHTING.RECHTS);
		bord.plaatsTegel(middenboven, new BordPositie(linksboven, Tegel.RICHTING.RECHTS) );
		
		
		assertEquals("Middenboven + rechtsboven : ",middenboven.kanBuurAccepteren(rechtsboven, Tegel.RICHTING.RECHTS), true );
		//middenboven.setBuur(rechtsboven, Tegel.RICHTING.RECHTS);	
		bord.plaatsTegel(rechtsboven, new BordPositie(middenboven, Tegel.RICHTING.RECHTS) );
		
		assertEquals("Rechtsboven + rechtsmidden : ",rechtsboven.kanBuurAccepteren(rechtsmidden, Tegel.RICHTING.ONDER), true );
		//rechtsboven.setBuur(rechtsmidden, Tegel.RICHTING.ONDER);	
		bord.plaatsTegel(rechtsmidden, new BordPositie(rechtsboven, Tegel.RICHTING.ONDER) );	
		
		assertEquals("Rechtsmidden + rechtsonder : ",rechtsmidden.kanBuurAccepteren(rechtsonder, Tegel.RICHTING.ONDER), true );
		//rechtsmidden.setBuur(rechtsonder, Tegel.RICHTING.ONDER);
		bord.plaatsTegel(rechtsonder, new BordPositie(rechtsmidden, Tegel.RICHTING.ONDER) );		
		
		assertEquals("Rechtsonder + middenonder : ",rechtsonder.kanBuurAccepteren(middenonder, Tegel.RICHTING.LINKS), true );
		//rechtsonder.setBuur(middenonder, Tegel.RICHTING.LINKS);	
		bord.plaatsTegel(middenonder, new BordPositie(rechtsonder, Tegel.RICHTING.LINKS) );		
		
		assertEquals("middenonder + linksonder : ",middenonder.kanBuurAccepteren(linksonder, Tegel.RICHTING.LINKS), true );
		//middenonder.setBuur(linksonder, Tegel.RICHTING.LINKS);	
		bord.plaatsTegel(linksonder, new BordPositie(middenonder, Tegel.RICHTING.LINKS) );				
		
		assertEquals("linksonder + linksmidden : ",linksonder.kanBuurAccepteren(linksmidden, Tegel.RICHTING.BOVEN), true );
		//linksonder.setBuur(linksmidden, Tegel.RICHTING.BOVEN);	
		bord.plaatsTegel(linksmidden, new BordPositie(linksonder, Tegel.RICHTING.BOVEN) );
		
		/*inksboven.print();
		middenboven.print();
		rechtsboven.print();
		rechtsmidden.print();
		rechtsonder.print();
		middenonder.print();
		linksonder.print();
		linksmidden.print();*/
	
		// structuur is opgezet, nu kunnen we de middenste testen
		// afhankelijk van de rotatie mogen sommige tegels hem wel of niet accepteren
		assertEquals("Test goed", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), true);
		assertEquals("Test goed", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), true);
		assertEquals("Test goed", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), true);
		assertEquals("Test goed", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), true);
		
		assertEquals("Test op spelbord", bord.controleerPlaatsbaarheid(middenmidden, new BordPositie(linksmidden,Tegel.RICHTING.RECHTS)), true );
		
		middenmidden.setRotatie( 0 );
		assertEquals("Test fout", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), false);
		assertEquals("Test fout", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), false);
		assertEquals("Test fout", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), false);
		assertEquals("Test fout", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), false);
		
		assertEquals("Test op spelbord", bord.controleerPlaatsbaarheid(middenmidden, new BordPositie(linksmidden,Tegel.RICHTING.RECHTS)), false );
		
		middenmidden.setRotatie( 90 );
		assertEquals("Test goed", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), true);
		assertEquals("Test fout", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), false);
		assertEquals("Test goed", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), true);
		assertEquals("Test fout", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), false);
		
		assertEquals("Test op spelbord", bord.controleerPlaatsbaarheid(middenmidden, new BordPositie(linksmidden,Tegel.RICHTING.RECHTS)), false );
		
		middenmidden.setRotatie( 270 );
		assertEquals("Test fout", linksmidden.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.RECHTS), false);
		assertEquals("Test goed", middenonder.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.BOVEN), true);
		assertEquals("Test fout", rechtsmidden.kanBuurAccepteren(middenmidden, Tegel.RICHTING.LINKS), false);
		assertEquals("Test goed", middenboven.kanBuurAccepteren(middenmidden,  Tegel.RICHTING.ONDER), true);
		
		assertEquals("Test op spelbord", bord.controleerPlaatsbaarheid(middenmidden, new BordPositie(linksmidden,Tegel.RICHTING.RECHTS)), false );
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
		TegelGebiedBeheerder origineleBeheerder = t.getGebiedBeheerder();
		
		t.setRotatie(90);
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
