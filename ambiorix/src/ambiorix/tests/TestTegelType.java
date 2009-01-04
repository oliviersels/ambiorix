package ambiorix.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ambiorix.Systeem;
import ambiorix.spelbord.TegelType;
import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.tegeltypes.TegelType_GGGGK;
import ambiorix.spelbord.tegeltypes.TegelType_WGGWW;

public class TestTegelType 
{

	@Test
	public void testDraaiTerrein() 
	{
		Systeem.prepareForTests();
		
		TegelType_WGGWW type = new TegelType_WGGWW();
		TerreinType[][] terrein = type.draaiTerrein( 90 );
		
		System.out.println("TestTegelType::testDraaiTerrein");
		TegelType.print(terrein);
		
		assertEquals("check rotatie gewoon : ", terrein[1][2].getID(), "TerreinType_Weg");
		assertEquals("check rotatie gewoon : ", terrein[2][1].getID(), "TerreinType_Gras");
		
		
			TegelType_WGGWW type2 = new TegelType_WGGWW();
			TerreinType[][] terrein2 = type2.draaiTerrein( 180 );
			
			System.out.println("TestTegelType::testDraaiTerrein");
			TegelType.print(terrein2);
			
			assertEquals("check rotatie 180 : ", terrein2[2][1].getID(), "TerreinType_Weg");
			assertEquals("check rotatie 180 : ", terrein2[0][1].getID(), "TerreinType_Gras");
	
		
		TegelType_GGGGK type3 = new TegelType_GGGGK();
		TerreinType[][] terrein3 = type3.draaiTerrein( 90 );
		
		System.out.println("TestTegelType::testDraaiTerrein");
		TegelType.print(terrein3);
		
		assertEquals("check rotatie middenkolom : ", terrein3[1][1].getID(), "TerreinType_Klooster");

	}

}
