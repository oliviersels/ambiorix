package ambiorix.acties.basisspel;

import java.util.Random;

import ambiorix.acties.Actie;
import ambiorix.spelers.Speler;

public class Pre_BeginSpeler extends Actie {

	public Pre_BeginSpeler() {
		System.out.println("-------> Pre - BeginSpeler");
		
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		// willekeurige speler kiezen
		int verkozen = (int) (Math.random() * speltoolkit.getAantalSpelers());
		Speler speler = speltoolkit.getSpelers().get(verkozen);		
		speltoolkit.setActieveSpeler(speler);
		
		System.out.println("Pre - BeginSpeler -> "+speler.getNaam());
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Pre - BeginSpeler -> geen undo");

		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Pre - BeginSpeler -> volgende");
		return new Beurt_Begin();
	}

}
