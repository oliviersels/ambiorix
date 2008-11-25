import ambiorix.spelbord.*;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelbord.tegeltypes.TegelType_GGGGK;
import ambiorix.spelbord.terreintypes.TerreinType_Gras;
import ambiorix.spelbord.terreintypes.TerreinType_Klooster;


public class systeem 
{

	public static void main(String args[])
	{	
//		Robin();
		Steven();
		//Jens();
		//Jan();
		//Olivier();
		// ge kunt ook meerdere mains tegelijk hebben, flikkers
	
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
		// TODO Auto-generated method stub
		
	}

	private static void Steven()
	{
		// TODO Auto-generated method stub
		
	}

	public static void Robin()
	{
		PionType_Volgeling volgeling = new PionType_Volgeling();
		PionTypeVerzameling.getInstantie().registreerType(volgeling);
		
		TegelType_GGGGK ggggk = new TegelType_GGGGK();
		TegelTypeVerzameling.getInstantie().registreerType(ggggk);
		
		TerreinType_Gras gras = new TerreinType_Gras();
		TerreinType_Klooster klooster = new TerreinType_Klooster();
		TerreinTypeVerzameling.getInstantie().registreerType(gras);
		TerreinTypeVerzameling.getInstantie().registreerType(klooster);
		
		 
		Pion vg = new Pion("test",PionTypeVerzameling.getInstantie().getType("PionType_Volgeling"));
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK"));
		
		Spelbord spelbord = new Spelbord(t);
		spelbord.setTegelAantal("TegelType_GGGGK", 5);
		Tegel next = spelbord.getVolgendeTegel();
		t.setBuur(next, Tegel.RICHTING.RECHTS);
		
		Tegel next2 = spelbord.getVolgendeTegel();
		next.setBuur(next2, Tegel.RICHTING.ONDER);
		
		Tegel next3 = spelbord.getVolgendeTegel();
		Tegel next4 = spelbord.getVolgendeTegel();
		Tegel next5 = spelbord.getVolgendeTegel();	
	}
		
}
