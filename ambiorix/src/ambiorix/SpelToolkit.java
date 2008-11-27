package ambiorix;

import java.util.Vector;

import ambiorix.acties.Actie;
import ambiorix.acties.ActieBestuurder;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelers.Speler;

// TODO_S eigelijk met reflectie via uitbreidingen
import ambiorix.acties.basisspel.*;

public class SpelToolkit {
	
	private Spel spel;
	private Spelbord spelbord;
	private ActieBestuurder actiebestuurder;

	public SpelToolkit(Spel spel) {
		this.spel = spel;
		actiebestuurder = new ActieBestuurder(this);
		// spelbord = new Spelbord();
	}
	
	// TEMP
	public ActieBestuurder getActiebestuurder() {
		return actiebestuurder;
	}
	
	// van Actiebestuurder	
	
	public void start(Actie start) {
		actiebestuurder.start(start);
	}

	// van Spel
	
	public int getAantalSpelers() {
		return spel.getAantalSpelers();
	}

	public Vector<Speler> getSpelers() {
		return spel.getSpelers();
	}	

	public Speler getActieveSpeler() {
		return spel.getActieveSpeler();
	}

	public void setActieveSpeler(Speler actieveSpeler) {
		spel.setActieveSpeler(actieveSpeler);
	}

	// van Spelbord
	
	public Tegel getVolgendeTegel() {
		return spelbord.getVolgendeTegel();
	}
	
	// van typeverzamelingen
	
	public Pion getPion(String piontype) {
		return new Pion(0,PionTypeVerzameling.getInstantie().getType(piontype));
	}

	public void setTegelAantal(String tegelType, int hoeveelheid) {
		spelbord.setTegelAantal(tegelType, hoeveelheid);
	}
}
