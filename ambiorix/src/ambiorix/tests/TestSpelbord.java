package ambiorix.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ambiorix.Systeem;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.Terrein;
import ambiorix.util.Punt;

public class TestSpelbord
{

	@Test
	public void testGetVolgendeTegel() 
	{
		Systeem.prepareForTests();
		
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord();
		spelbord.setBegintegel(t);
		spelbord.setTegelAantal("TegelType_WGGWW", 5);
		
		Tegel next = null; 

		for(int i = 1; i <= 5; i++ )
		{
			next = spelbord.getVolgendeTegel();
			assertEquals( " Test ID : ", next.getID(), i );
			assertEquals( " Test TegelType FlyWeight : ",  next.getType(), TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW") );
		}
		
		next = spelbord.getVolgendeTegel();
		assertEquals( " Test einde tegelpool : ",  next, null );
	}

	@Test
	public void testSetTegelAantal() 
	{
		Systeem.prepareForTests();
		
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord();
		spelbord.setBegintegel(t);
		spelbord.setTegelAantal("TegelType_GGGGK", 5);
		
		assertEquals("test aantal van type : ", spelbord.getTegelAantal("TegelType_GGGGK"), 5);
	}
	
	@Test
	public void testGetGebied()
	{
		Systeem.prepareForTests();
		
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord();
		spelbord.setBegintegel(t);
		spelbord.setTegelAantal("TegelType_GGGGK", 5);
		
		Tegel t2 = spelbord.getVolgendeTegel();
		spelbord.plaatsTegel(t2, new BordPositie(t, Tegel.RICHTING.LINKS) );
		
		Gebied gebied = spelbord.getGebied( new Terrein(t, new Punt(0,2)) );
		
		// kloosters mogen er niet in
		for( Terrein terrein: gebied.getTerreinStukken() )
		{
			assertEquals("Geen kloosters toegelaten : ", terrein.toString() == t.getTerrein(new Punt(1,1)).toString(), false);
			assertEquals("Geen kloosters toegelaten : ", terrein.toString() == t2.getTerrein(new Punt(1,1)).toString(), false);
			System.out.println( terrein.getTegel().getID() + "->" +  terrein.getPositie().toString() );
		}
	}

	@Test
	public void testBordOpbouw()
	{
		System.out.println("--------------------------------------------------------------------------");
		
		// TODO : nog geen "echte" unitTest, kijk naar de output ! (niet omdat hij geen fouten geeft dat hij geslaagd is)
		// maakt iets van de vorm :
		//  0  1
		//  3  2
		// Controleren of alle buren gezet zijn op tegel, en alle gebieden gelinkt in TegelGebiedBeheerder
		
		Systeem.prepareForTests();
		

		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord();
		spelbord.setBegintegel(t);
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
		
		assertEquals(true, true);
		
	}
}
