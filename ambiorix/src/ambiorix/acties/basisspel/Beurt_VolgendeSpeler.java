package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;
import ambiorix.spelers.Speler;

public class Beurt_VolgendeSpeler extends Actie {

	public Beurt_VolgendeSpeler() {
		System.out.println("-------> Beurt - VolgendeSpeler");
		
		undo = UNDO.NIET_BESCHIKBAAR;
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		
		Speler actieve = speltoolkit.getActieveSpeler();
		boolean gevonden = false; 
		
		for (Speler speler : speltoolkit.getSpelers()) {
			if(gevonden)
				speltoolkit.setActieveSpeler(speler);
			
			gevonden = (speler == actieve);		
		}
		
		if(gevonden) // laatste in de rij -> herbeginnen bij de eerste
			speltoolkit.setActieveSpeler( speltoolkit.getSpelers().firstElement() );
		
		System.out.println("Beurt - VolgendeSpeler -> " + speltoolkit.getActieveSpeler().getNaam());
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Beurt - VolgendeSpeler -> geen undo");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {		
		System.out.println("Beurt - VolgendeSpeler -> volgende");
		return new Beurt_Begin();
	}

}
