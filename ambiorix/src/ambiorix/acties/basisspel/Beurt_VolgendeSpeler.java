package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Beurt_VolgendeSpeler extends Actie {

	public Beurt_VolgendeSpeler() {
		System.out.println("-------> Beurt - VolgendeSpeler");
		
		// TODO_S ref naar huidige speler
		// TODO_S ref naar volgende speler

		// geen undo, anders kunnen we valsspelen door steeds een nieuwe tegel te krijgens
		undo = UNDO.NIET_BESCHIKBAAR;
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Beurt - VolgendeSpeler -> lijst van spelers opvragen");
		System.out.println("Beurt - VolgendeSpeler -> huidige speler opvragen");
		System.out.println("Beurt - VolgendeSpeler -> volgende speler bepalen");
		System.out.println("Beurt - VolgendeSpeler -> speler aanduiden als huidige speler");
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Beurt - VolgendeSpeler -> vorige speler pakken");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {		
		System.out.println("Beurt - VolgendeSpeler -> volgende");
		return new Beurt_Begin();
	}

}
