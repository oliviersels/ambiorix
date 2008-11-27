package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Pre_BeginSpeler extends Actie {

	public Pre_BeginSpeler() {
		System.out.println("-------> Pre - BeginSpeler");
		
		// TODO_S ref naar speler bijhouden
		
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Pre - BeginSpeler -> alle Spelers opvragen");
		System.out.println("Pre - BeginSpeler -> een kiezen als beginspeler");
		System.out.println("Pre - BeginSpeler -> Speler zetten als beginspeler");
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Pre - BeginSpeler -> Speler verwijderen als beginspeler");

		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Pre - BeginSpeler -> volgende");
		return new Beurt_Begin();
	}

}
