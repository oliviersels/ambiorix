package ambiorix.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.systeem;

public class TestSpelbord 
{

	@Test
	public void testGetVolgendeTegel() 
	{
		systeem.prepareForTests();
		
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord(t);
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
		systeem.prepareForTests();
		
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord(t);
		spelbord.setTegelAantal("TegelType_GGGGK", 5);
		
		assertEquals("test aantal van type : ", spelbord.getTegelAantal("TegelType_GGGGK"), 5);
	}

	@Test
	public void testBordOpbouw()
	{
		// geen "echte" unitTest, kijk naar de output !
		// maakt iets van de vorm :
		//  0  1
		//  3  2
		// Controleren of alle buren gezet zijn op tegel, en alle gebieden gelinkt in TegelGebiedBeheerder
		
		systeem.prepareForTests();
		

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
