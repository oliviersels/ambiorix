package ambiorix;

import ambiorix.acties.ActieBestuurder;
import ambiorix.spelbord.Spelbord;

// TODO_S eigelijk met reflectie via uitbreidingen
import ambiorix.acties.basisspel.*;

public class SpelToolkit {

	public SpelToolkit() {
		actiebestuurder = new ActieBestuurder(this);
		// spelbord = new Spelbord();
		
		actiebestuurder.start(new Pre_GeefPionnen());
	}
	
	private Spelbord spelbord;
	private ActieBestuurder actiebestuurder;

}
