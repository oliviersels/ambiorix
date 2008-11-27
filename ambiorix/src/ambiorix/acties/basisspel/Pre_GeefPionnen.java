package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;
import ambiorix.spelers.Speler;

public class Pre_GeefPionnen extends Actie {

	public Pre_GeefPionnen() {
		System.out.println("-------> Pre - GeefPionnen");
		
		undo = UNDO.NIET_BESCHIKBAAR; // 1e Actie
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		for (Speler speler : speltoolkit.getSpelers()) {
			System.out.println("Pre - GeefPionnen -> "+speler.getNaam());
			for (int i = 0; i < 7; ++i) // TODO_S naar speltoolkit, of rechtstreeks op speler?
				speler.addPion( speltoolkit.getPion("PionType_Volgeling") ); 
			System.out.println("Pre - GeefPionnen -> "+speler.getAantalPionnen());
		}
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Pre - GeefPionnen -> geen undo");		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Pre - GeefPionnen -> volgende");
		return new Pre_EersteTegel();
	}

}
