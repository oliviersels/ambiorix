package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Beurt_PionPlaatsen extends Actie {
	
	public Beurt_PionPlaatsen() {
		System.out.println("-------> Beurt - PionPlaatsen");
		
		// TODO_S ref naar huidige speler
		// TODO_S ref naar tegel
		// TODO_S ref naar pion

		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Beurt - PionPlaatsen -> wachten op input (legtegel | beurt afsluiten)");

		status = STATUS.BEZIG;
	}

	@Override
	public void undo() {
		System.out.println("Beurt - PionPlaatsen -> pion van tegel afnemen");
		System.out.println("Beurt - PionPlaatsen -> pion terug aan speler geven");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Beurt - PionPlaatsen -> score berekenen");
		
		return new Beurt_VolgendeSpeler();
	}

	@Override
	public void zetPion() {
		// TODO_S niet autmatisch doorgaan naar volgende
		
		System.out.println("Beurt - PionPlaatsen -> controleren of pion daar kan gezet worden");
		
		boolean plaatsing = true;
		
		if(plaatsing) {			
			System.out.println("Beurt - PionPlaatsen -> Pion plaatsen");
			System.out.println("Beurt - PionPlaatsen -> Pion van speler afnemen");
			
			status = STATUS.GEDAAN;
		} else {
			System.out.println("Beurt - PionPlaatsen -> MELDING, foute plaatsing");			
		}
		
	}

	@Override
	public void volgendeBeurt() {
		System.out.println("Beurt - PionPlaatsen -> volgende beurt");

		status = STATUS.GEDAAN;
	}
	
	

}
