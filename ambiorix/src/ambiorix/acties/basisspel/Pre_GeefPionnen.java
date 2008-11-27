package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Pre_GeefPionnen extends Actie {

	public Pre_GeefPionnen() {
		// alle spelers 7 pionnen geven, niks qua data voor undo bijhouden
		System.out.println("-------> Pre - GeefPionnen");
		
		undo = UNDO.NIET_BESCHIKBAAR; // 1e Actie
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Pre - GeefPionnen -> spelers opvragen");
		System.out.println("Pre - GeefPionnen -> elke speler 7 pionnen geven");
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Pre - GeefPionnen -> spelers opvragen");
		System.out.println("Pre - GeefPionnen -> alle pionnen verwijderen");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Pre - GeefPionnen -> volgende");
		return new Pre_EersteTegel();
	}

}
