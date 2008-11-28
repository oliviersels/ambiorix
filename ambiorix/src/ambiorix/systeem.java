package ambiorix;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ambiorix.gui.HoofdVenster;
import ambiorix.spelbord.*;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelbord.tegeltypes.TegelType_GGGGK;
import ambiorix.spelbord.tegeltypes.TegelType_WGGWW;
import ambiorix.spelbord.terreintypes.*;


public class systeem 
{

	public static void main(String args[])
	{	
		Robin();
		//Jens();
		//Jan();
		//Olivier();
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
		/*for(int i = 0; i <20; i++)
			for(int y = 0; y <20 ;y++)
				hv.voegTegelToe(i, y, new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK")));
		*/
		hv.voegTegelToe(2, 2, new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK")));
		hv.voegTegelToe(3, 2, new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_GGGGK")));
		
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
		TerreinTypeVerzameling.getInstantie().registreerType(gras);
		TerreinTypeVerzameling.getInstantie().registreerType(klooster);
		TerreinTypeVerzameling.getInstantie().registreerType(weg);
		
		
		TegelType_GGGGK ggggk = new TegelType_GGGGK();
		TegelType_WGGWW wggww = new TegelType_WGGWW();
		TegelTypeVerzameling.getInstantie().registreerType(ggggk);
		TegelTypeVerzameling.getInstantie().registreerType(wggww);
	}
	
	Spel huidigeSpel;		
}
