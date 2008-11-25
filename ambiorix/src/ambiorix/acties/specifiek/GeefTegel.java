package ambiorix.acties.specifiek;

import ambiorix.acties.Actie;

public class GeefTegel extends Actie {

	public GeefTegel() {
		System.out.println("CONS -> geeftegel");
		
		undo = UNDO.NIET_BESCHIKBAAR;
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("start -> geeftegel");
		
		System.out.println("geeftegel -> geef tegel aan huidige speler");
		System.out.println("geeftegel -> verwacht geen input ofzo");		

		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("geeftegel -> tegel van speler terug naar de verzameling");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("geeftegel -> volgende -> LegTegel");
		return new LegTegel();
	}

}
