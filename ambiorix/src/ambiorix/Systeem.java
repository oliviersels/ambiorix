package ambiorix;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ambiorix.gui.HoofdVenster;
import ambiorix.spelbord.*;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelbord.tegeltypes.*;
import ambiorix.spelbord.terreintypes.*;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;
import ambiorix.uitbreidingen.Uitbreiding;
import ambiorix.uitbreidingen.UitbreidingVerzameling;
import ambiorix.uitbreidingmenu.UitbreidingMenu;
import ambiorix.util.File;
import ambiorix.util.Punt;
import ambiorix.util.TypeVerzameling;


public class Systeem 
{
	private Spel huidigeSpel;
	private HoofdVenster gui;
	private static Systeem instantie;
	
	public static Systeem getInstantie() {
		if(instantie == null)
			instantie = new Systeem();
		return instantie;
	}
	
	protected Systeem() {
		//huidigeSpel = new Spel();
	}
	
	public synchronized void startGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		gui = new HoofdVenster();
		// TODO Spel moet achteraf door gui gestart worden!
		huidigeSpel = new Spel(gui.getUitvoer());
		
		// TODO: Deze spelers moeten achteraf via de gui toegevoegd worden
		Speler s1 = new MenselijkeSpeler(gui.getInvoer(), gui.getUitvoer());
		s1.setNaam("Jan");
		s1.setKleur(Color.RED);
		huidigeSpel.addSpeler(s1);
		gui.voegSpelerToe(s1);
		Speler s2 = new MenselijkeSpeler(gui.getInvoer(), gui.getUitvoer());
		s2.setNaam("Piet");
		s2.setKleur(Color.BLACK);
		huidigeSpel.addSpeler(s2);
		gui.voegSpelerToe(s2);
		//gui.zetActieveSpeler(s1);
		
		try {
			wait();
		} catch (InterruptedException e) {}
	}
	
	public synchronized void startSpel() {
		huidigeSpel.start();
	}
	
	public synchronized void stopSpel() {
		huidigeSpel.stop();
		
		notify();
	}

	public static void main(String args[])
	{	
		//Robin();
		//Jens();
		//Robin();
		//Jens();
		//TestGebiedAanduiding();
		//Jan();
		//Olivier();
		TestUitbreidingen();
	}
	
	private static void TestUitbreidingen()
	{
		UitbreidingVerzameling.getInstantie().vulMetUitbreidingenUit("uitbreidingen/");
		//Uitbreiding uitbreiding = new Uitbreiding("Basis");
		//System.out.println(uitbreiding.getAfbeelding());
		//System.out.println(uitbreiding.getBeschrijving());
		
		for( String naam : UitbreidingVerzameling.getInstantie().getTypes() )
		{
			Uitbreiding uitbreiding = UitbreidingVerzameling.getInstantie().getType(naam);
			System.out.println(uitbreiding.getNaam());
			System.out.println(uitbreiding.getID());
			System.out.println(uitbreiding.getAfbeelding());
			System.out.println(uitbreiding.getBeschrijving());
		}
		
		
		//TypeVerzameling<TerreinType> terreinVerz = TerreinTypeVerzameling.getInstantie();
		//System.out.println( terreinVerz.getType("TerreinType_Lava").getID() );
		//String test = ";";		
		UitbreidingMenu um = new UitbreidingMenu();
		um.setVisible(true);
	}
	
	private static void Olivier()
	{
		// TODO Auto-generated method stub
		
	}

	private static void Jan()
	{
		// TODO Auto-generated method stub
		
	}

	private static void Jens()
	{
		prepareForTests();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HoofdVenster hv = HoofdVenster.geefInstantie();
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW"));
		
		Spelbord spelbord = new Spelbord();
		spelbord.setBegintegel(t);
		spelbord.setTegelAantal("TegelType_WGGWW", 5);
		hv.voegTegelToe(0, 0, t);
		Tegel t2 = spelbord.getVolgendeTegel();
		t2.setRotatie(180);
		spelbord.plaatsTegel(t2, new BordPositie(t, Tegel.RICHTING.ONDER) );
		hv.voegTegelToe(t2, new BordPositie(t, Tegel.RICHTING.ONDER));
		Gebied gebied = spelbord.getGebied( new Terrein(t, new Punt(0,2)) );
		for(int i = 0; i < 50; i++)
			hv.voegPionToe(new Pion(11, PionTypeVerzameling.getInstantie().getType("PionType_Volgeling"), null));

		hv.tekenTerrein(gebied);
		hv.voegSelectieTegelToe(spelbord.getVolgendeTegel());
		hv.voegSelectieTegelToe(spelbord.getVolgendeTegel());
	}
	
	private static void TestGebiedAanduiding()
	{
		Systeem.prepareForTests();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HoofdVenster hv = HoofdVenster.geefInstantie();
		
		
		
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
		bord.setTegelAantal("TegelType_BBBBB", 20);
		bord.setTegelAantal("TegelType_Driesprong", 15);
		
		
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
		


		hv.voegTegelToe(2, 1, linksboven);
		
		/*middenboven.setID(1);
		rechtsboven.setID(2);
		rechtsmidden.setID(3);
		rechtsonder.setID(4);
		middenonder.setID(5);
		linksonder.setID(6);
		linksmidden.setID(7);*/
		
		assertEquals("Linksboven + middenboven : ",linksboven.kanBuurAccepteren(middenboven, Tegel.RICHTING.RECHTS), true );
		//linksboven.setBuur(middenboven, Tegel.RICHTING.RECHTS);
		bord.plaatsTegel(middenboven, new BordPositie(linksboven, Tegel.RICHTING.RECHTS) );

		hv.voegTegelToe(middenboven, new BordPositie(linksboven, Tegel.RICHTING.RECHTS));
		
		
		assertEquals("Middenboven + rechtsboven : ",middenboven.kanBuurAccepteren(rechtsboven, Tegel.RICHTING.RECHTS), true );
		//middenboven.setBuur(rechtsboven, Tegel.RICHTING.RECHTS);	
		bord.plaatsTegel(rechtsboven, new BordPositie(middenboven, Tegel.RICHTING.RECHTS) );
		
		hv.voegTegelToe(rechtsboven, new BordPositie(middenboven, Tegel.RICHTING.RECHTS) );
		
		assertEquals("Rechtsboven + rechtsmidden : ",rechtsboven.kanBuurAccepteren(rechtsmidden, Tegel.RICHTING.ONDER), true );
		//rechtsboven.setBuur(rechtsmidden, Tegel.RICHTING.ONDER);	
		bord.plaatsTegel(rechtsmidden, new BordPositie(rechtsboven, Tegel.RICHTING.ONDER) );	
		
		hv.voegTegelToe(rechtsmidden, new BordPositie(rechtsboven, Tegel.RICHTING.ONDER) );
		
		assertEquals("Rechtsmidden + rechtsonder : ",rechtsmidden.kanBuurAccepteren(rechtsonder, Tegel.RICHTING.ONDER), true );
		//rechtsmidden.setBuur(rechtsonder, Tegel.RICHTING.ONDER);
		bord.plaatsTegel(rechtsonder, new BordPositie(rechtsmidden, Tegel.RICHTING.ONDER) );		
		
		hv.voegTegelToe(rechtsonder, new BordPositie(rechtsmidden, Tegel.RICHTING.ONDER) );
		
		assertEquals("Rechtsonder + middenonder : ",rechtsonder.kanBuurAccepteren(middenonder, Tegel.RICHTING.LINKS), true );
		//rechtsonder.setBuur(middenonder, Tegel.RICHTING.LINKS);	
		bord.plaatsTegel(middenonder, new BordPositie(rechtsonder, Tegel.RICHTING.LINKS) );	
		
		hv.voegTegelToe(middenonder, new BordPositie(rechtsonder, Tegel.RICHTING.LINKS) );
		
		assertEquals("middenonder + linksonder : ",middenonder.kanBuurAccepteren(linksonder, Tegel.RICHTING.LINKS), true );
		//middenonder.setBuur(linksonder, Tegel.RICHTING.LINKS);	
		bord.plaatsTegel(linksonder, new BordPositie(middenonder, Tegel.RICHTING.LINKS) );	

		hv.voegTegelToe(linksonder, new BordPositie(middenonder, Tegel.RICHTING.LINKS) );	
		
		assertEquals("linksonder + linksmidden : ",linksonder.kanBuurAccepteren(linksmidden, Tegel.RICHTING.BOVEN), true );
		//linksonder.setBuur(linksmidden, Tegel.RICHTING.BOVEN);	
		bord.plaatsTegel(linksmidden, new BordPositie(linksonder, Tegel.RICHTING.BOVEN) );

		hv.voegTegelToe(linksmidden, new BordPositie(linksonder, Tegel.RICHTING.BOVEN) );	
		
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
	
		
		
		// TODO : dit is eigenlijk deel van spelbordTest => testGetGebied
		middenmidden.setRotatie(180);
		bord.plaatsTegel( middenmidden, new BordPositie(linksmidden, Tegel.RICHTING.RECHTS) );
		

		hv.voegTegelToe(middenmidden, new BordPositie(linksmidden, Tegel.RICHTING.RECHTS) );
		
		Pion pion = new Pion(11, PionTypeVerzameling.getInstantie().getType("PionType_Volgeling"), null);
		
		middenmidden.plaatsPion(new Punt(0,1), pion);
		
		
		// laat deze weg voor goed te zien, laat em staan om verwijderen van tegel te testen
		// opgelet : gui tekent em nog wel !!!!
		//bord.verwijderTegel(linksmidden);
		
		
		Gebied gebied = bord.getGebied( new Terrein(rechtsmidden, new Punt(0,1)) );
		
		hv.tekenTerrein(gebied);
		
		gebied.print();
		
		Pion vg = new Pion(0,PionTypeVerzameling.getInstantie().getType("PionType_Volgeling"), null); // TODO: Oli fixt, maar speler moet niet null zijn
		hv.voegPionToe(middenonder, vg, new Punt(0,0));
		
		// kloosters mogen er niet in
		/*for( Terrein terrein: gebied.getTerreinStukken() )
		{
			System.out.println("Zoek de weg : " +  terrein.getTegel().getID() + "->" +  terrein.getPositie().toString() );
		}*/
		
		//System.out.println( bord.toXML() );
		File.writeContents(bord.toXML(), "xmlTest.xml");
		String contents = File.getContents("xmlTest.xml");
		
		contents.replace("\n", "");
		contents.replace("\t", "");

		System.out.println( contents );
		//System.out.println( "TESTING");
		
		Spelbord nieuwTestBord = Spelbord.fromXML( contents );
		File.writeContents(nieuwTestBord.toXML(), "xmlTestNIEUW.xml");
		
		
		//Spelbord b = Spelbord.fromXML("<spelbord><beginTegel>0</beginTegel><volgendeTegelID>9</volgendeTegelID><overgeblevenTegels></overgeblevenTegels><tegels><tegel><id>8</id><type>TegelType_WGGWW</type><rotatie>180</rotatie><buur><id>1</id><richting>BOVEN</richting></buur><pionnen><pion><id>11</id><type>PionType_Volgeling</type><speler>1</speler></pion></pionnen></tegel><tegel><id>3</id><type>TegelType_WGGWW</type><rotatie>270</rotatie><buur><id>2</id><richting>BOVEN</richting></buur></tegel><tegel><id>7</id><type>TegelType_GGGGK</type><rotatie>0</rotatie><buur><id>0</id><richting>BOVEN</richting></buur></tegel><tegel><id>4</id><type>TegelType_WGGWW</type><rotatie>0</rotatie><buur><id>3</id><richting>BOVEN</richting></buur></tegel><tegel><id>2</id><type>TegelType_WGGWW</type><rotatie>0</rotatie><buur><id>3</id><richting>ONDER</richting></buur></tegel><tegel><id>1</id><type>TegelType_WGGWW</type><rotatie>90</rotatie><buur><id>2</id><richting>RECHTS</richting></buur></tegel><tegel><id>0</id><type>TegelType_WGGWW</type><rotatie>0</rotatie><buur><id>1</id><richting>RECHTS</richting></buur></tegel><tegel><id>5</id><type>TegelType_WGGWW</type><rotatie>90</rotatie><buur><id>8</id><richting>BOVEN</richting></buur></tegel><tegel><id>6</id><type>TegelType_WGGWW</type><rotatie>270</rotatie><buur><id>7</id><richting>BOVEN</richting></buur></tegel></tegels></spelbord>");
		
	}

	private static void Steven()
	{
		// TODO Auto-generated method stub
		
	}

	public static void Robin()
	{
		prepareForTests();
		
		 
		/*Pion vg = new Pion(0,PionTypeVerzameling.getInstantie().getType("PionType_Volgeling"));
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord(t);
		spelbord.setTegelAantal("TegelType_GGGGK", 5);
		
		
		Tegel next = spelbord.getVolgendeTegel();
		t.setBuur(next, Tegel.RICHTING.RECHTS);
		
		t.print();
		//t.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		next.print();
		//next.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		
		
		Tegel next2 = spelbord.getVolgendeTegel();
		next.setBuur(next2, Tegel.RICHTING.ONDER);
		
		t.print();
		//t.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		next.print();
		//next.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		next2.print();
		//next2.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		
		Tegel next3 = spelbord.getVolgendeTegel();
		next2.setBuur(next3, Tegel.RICHTING.LINKS);
		
		t.print();
		//t.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		next.print();
		//next.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		next2.print();
		//next2.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		next3.print();
		//next3.gebiedBeheerder.print();
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		System.out.println( " ----------------------- " );
		
		
		/*Tegel next4 = spelbord.getVolgendeTegel();
		Tegel next5 = spelbord.getVolgendeTegel();	*/
	}
	
	public static void prepareForTests()
	{
		PionType_Volgeling volgeling = new PionType_Volgeling();
		PionTypeVerzameling.getInstantie().registreerType(volgeling);
	
		
		TerreinType_Gras gras = new TerreinType_Gras();
		TerreinType_Klooster klooster = new TerreinType_Klooster();
		TerreinType_Weg weg = new TerreinType_Weg();
		TerreinType_Burcht burcht = new TerreinType_Burcht();
		TerreinType_Wildcard wildcard = new TerreinType_Wildcard();
		TerreinTypeVerzameling.getInstantie().registreerType(gras);
		TerreinTypeVerzameling.getInstantie().registreerType(klooster);
		TerreinTypeVerzameling.getInstantie().registreerType(weg);
		TerreinTypeVerzameling.getInstantie().registreerType(burcht);
		TerreinTypeVerzameling.getInstantie().registreerType(wildcard);
		
		
		TegelType_GGGGK ggggk = new TegelType_GGGGK();
		TegelType_WGGWW wggww = new TegelType_WGGWW();
		TegelType_BBBBB bbbbb = new TegelType_BBBBB();
		TegelType_BurchtMetBochtweg bochtweg = new TegelType_BurchtMetBochtweg();
		TegelType_Driesprong driesprong = new TegelType_Driesprong();
		TegelType_EenZijdeBurcht eenzijdeBurcht = new TegelType_EenZijdeBurcht();
		TegelType_RechteWeg rechteweg = new TegelType_RechteWeg();
		
		TegelTypeVerzameling.getInstantie().registreerType(ggggk);
		TegelTypeVerzameling.getInstantie().registreerType(wggww);
		TegelTypeVerzameling.getInstantie().registreerType(bbbbb);
		TegelTypeVerzameling.getInstantie().registreerType(bochtweg);
		TegelTypeVerzameling.getInstantie().registreerType(driesprong);
		TegelTypeVerzameling.getInstantie().registreerType(eenzijdeBurcht);
		TegelTypeVerzameling.getInstantie().registreerType(rechteweg);
	}	
}
