package ambiorix.acties;

import ambiorix.Spel;
import ambiorix.acties.basisspel.Pre_GeefPionnen;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelers.*;

public class TESTMAIN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		try {
			Spel spel = new Spel();
			PionTypeVerzameling.getInstantie().registreerType(new PionType_Volgeling());			
			
			Speler speler;
			
			speler = new MenselijkeSpeler();
			speler.setNaam("Joske");
			spel.addSpeler(speler);
			
			speler = new MenselijkeSpeler();
			speler.setNaam("Jefke");
			spel.addSpeler(speler);
			
			ActieBestuurder ab = spel.getSpeltoolkit().getActiebestuurder();	
			
			DRAAD d = null;
			d = new DRAAD(ab, 1);
			d = new DRAAD(ab, 2);
			d = new DRAAD(ab, 3);
			d = new DRAAD(ab, 4);
			
			spel.start(new Pre_GeefPionnen());
			
			d = new DRAAD(ab, 0);
			d.join();
			
			System.out.println("---- DONE ----");
		} catch (/*Interrupted*/Exception e) {}
	}

}
