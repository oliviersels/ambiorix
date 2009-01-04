package ambiorix.tests;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.acties.specifiek.BerekenScore;
import ambiorix.acties.specifiek.EindeBeurt;
import ambiorix.acties.specifiek.EindeSpel;
import ambiorix.acties.specifiek.GeefTegel;
import ambiorix.acties.specifiek.LegTegel;
import ambiorix.acties.specifiek.StartSpel;
import ambiorix.acties.specifiek.ZetPion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelbord.tegeltypes.TegelType_BBBBB;
import ambiorix.spelbord.tegeltypes.TegelType_BurchtMetBochtweg;
import ambiorix.spelbord.tegeltypes.TegelType_Driesprong;
import ambiorix.spelbord.tegeltypes.TegelType_EenZijdeBurcht;
import ambiorix.spelbord.tegeltypes.TegelType_GGGGK;
import ambiorix.spelbord.tegeltypes.TegelType_LavaBocht;
import ambiorix.spelbord.tegeltypes.TegelType_LavaMetBurchten;
import ambiorix.spelbord.tegeltypes.TegelType_LavaMetWeg;
import ambiorix.spelbord.tegeltypes.TegelType_LavaPoel;
import ambiorix.spelbord.tegeltypes.TegelType_LavaRecht;
import ambiorix.spelbord.tegeltypes.TegelType_RechteWeg;
import ambiorix.spelbord.tegeltypes.TegelType_WGGWW;
import ambiorix.spelbord.terreintypes.TerreinType_Burcht;
import ambiorix.spelbord.terreintypes.TerreinType_Gras;
import ambiorix.spelbord.terreintypes.TerreinType_Klooster;
import ambiorix.spelbord.terreintypes.TerreinType_Lava;
import ambiorix.spelbord.terreintypes.TerreinType_Weg;
import ambiorix.spelbord.terreintypes.TerreinType_Wildcard;

public class Testtest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PionType_Volgeling volgeling = new PionType_Volgeling();
		PionTypeVerzameling.getInstantie().registreerType(volgeling);
	
		
		TerreinType_Gras gras = new TerreinType_Gras();
		TerreinType_Klooster klooster = new TerreinType_Klooster();
		TerreinType_Weg weg = new TerreinType_Weg();
		TerreinType_Burcht burcht = new TerreinType_Burcht();
		TerreinType_Wildcard wildcard = new TerreinType_Wildcard();
		TerreinType_Lava lava = new TerreinType_Lava();
		
		TerreinTypeVerzameling.getInstantie().registreerType(gras);
		TerreinTypeVerzameling.getInstantie().registreerType(klooster);
		TerreinTypeVerzameling.getInstantie().registreerType(weg);
		TerreinTypeVerzameling.getInstantie().registreerType(burcht);
		TerreinTypeVerzameling.getInstantie().registreerType(wildcard);
		TerreinTypeVerzameling.getInstantie().registreerType(lava);
		
		
		TegelType_GGGGK ggggk = new TegelType_GGGGK();
		TegelType_WGGWW wggww = new TegelType_WGGWW();
		TegelType_BBBBB bbbbb = new TegelType_BBBBB();
		TegelType_BurchtMetBochtweg bochtweg = new TegelType_BurchtMetBochtweg();
		TegelType_Driesprong driesprong = new TegelType_Driesprong();
		TegelType_EenZijdeBurcht eenzijdeBurcht = new TegelType_EenZijdeBurcht();
		TegelType_RechteWeg rechteweg = new TegelType_RechteWeg();
		
		TegelType_LavaRecht lavaRecht = new TegelType_LavaRecht();
		TegelType_LavaMetBurchten lavaMetBurchten = new TegelType_LavaMetBurchten();
		TegelType_LavaBocht lavaBocht = new TegelType_LavaBocht();
		TegelType_LavaMetWeg lavaMetWeg = new TegelType_LavaMetWeg();
		TegelType_LavaPoel lavaPoel = new TegelType_LavaPoel();
		
		TegelTypeVerzameling.getInstantie().registreerType(lavaRecht);	
		TegelTypeVerzameling.getInstantie().registreerType(lavaMetBurchten);
		TegelTypeVerzameling.getInstantie().registreerType(lavaBocht);
		TegelTypeVerzameling.getInstantie().registreerType(lavaMetWeg);	
		TegelTypeVerzameling.getInstantie().registreerType(lavaPoel);	
		
		
		
		TegelTypeVerzameling.getInstantie().registreerType(ggggk);
		TegelTypeVerzameling.getInstantie().registreerType(wggww);
		TegelTypeVerzameling.getInstantie().registreerType(bbbbb);
		TegelTypeVerzameling.getInstantie().registreerType(bochtweg);
		TegelTypeVerzameling.getInstantie().registreerType(driesprong);
		TegelTypeVerzameling.getInstantie().registreerType(eenzijdeBurcht);
		TegelTypeVerzameling.getInstantie().registreerType(rechteweg);
		
		LegTegel legTegel = new LegTegel(null, null);
		StartSpel startSpel = new StartSpel(null, null);
		GeefTegel geefTegel = new GeefTegel(null, null);
		ZetPion zetPion = new ZetPion(null, null, null);
		BerekenScore berekenScore = new BerekenScore(null, null, null);
		EindeBeurt eindeBeurt = new EindeBeurt(null, null);
		EindeSpel eindeSpel = new EindeSpel(null, null);
		
		/*ActieVerzameling.getInstantie().registreerType((Class<AbstractActie>) legTegel.getClass());
		ActieVerzameling.getInstantie().registreerType(startSpel);
		ActieVerzameling.getInstantie().registreerType(geefTegel);
		ActieVerzameling.getInstantie().registreerType(zetPion);
		ActieVerzameling.getInstantie().registreerType(berekenScore);
		ActieVerzameling.getInstantie().registreerType(eindeBeurt);
		ActieVerzameling.getInstantie().registreerType(eindeSpel);*/
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

}
