package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Beurt_TegelPlaatsen extends Actie {
	
	public Beurt_TegelPlaatsen() {
		System.out.println("-------> Beurt - TegelPlaatsen");
		
		// TODO_S ref naar huidige speler
		// TODO_S ref naar tegel

		// geen undo, anders kunnen we valsspelen door steeds een nieuwe tegel te krijgens
		undo = UNDO.NIET_BESCHIKBAAR;
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Beurt - TegelPlaatsen -> wachten op input (legtegel)");

		status = STATUS.BEZIG;
	}

	@Override
	public void undo() {
		System.out.println("Beurt - TegelPlaatsen -> undo");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Beurt - TegelPlaatsen -> volgende");
		return new Beurt_PionPlaatsen();
	}

	@Override
	public void legTegel() {
		// TODO_S niet autmatisch doorgaan naar volgende
		
		System.out.println("Beurt - TegelPlaatsen -> controleren of tegel daar kan gezet worden");
		
		boolean plaatsing = true;
		
		if(plaatsing) {			
			System.out.println("Beurt - TegelPlaatsen -> Tegel plaatsen");
			System.out.println("Beurt - TegelPlaatsen -> Tegel van speler afnemen");
			
			status = STATUS.GEDAAN;
		} else {
			System.out.println("Beurt - TegelPlaatsen -> MELDING, foute plaatsing");			
		}
			
		
	}
	
	

}
