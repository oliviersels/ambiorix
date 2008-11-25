package ambiorix.acties.specifiek;

import ambiorix.acties.Actie;

public class LegTegel extends Actie {

	public LegTegel() {
		System.out.println("CONS -> legtegel");
		
		undo = UNDO.BESCHIKBAAR;
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		// onklaar > stoppen !
		System.out.println("start -> legtegel");
		
		System.out.println("legtegel -> niks doen");
		
		status = STATUS.BEZIG;
	}

	@Override
	public void undo() {
		System.out.println("legtegel -> tegel terug naar speler");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("legtegel -> volgende -> NULL");
		return null;
	}

	@Override
	public void legTegel() {
		System.out.println("legtegel -> tegel goed gelegd");
		
		status = STATUS.GEDAAN;
	}
	
	

}
