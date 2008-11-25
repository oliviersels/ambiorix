package ambiorix.acties;

import java.util.Stack;

import ambiorix.acties.Actie.STATUS;
import ambiorix.acties.Actie.UNDO;

// alle events van speltoolkit binnen krijgen, zelf doorgeven aan huidige actie
// stap per stap doorgaan dan, geen while
// tussen stappen, tijd voor undo
//  undo: huidige undo doen, zien dat deze dat aankan !!!
//        vorige van stack halen, undo doen en de volgende hierven vragen
// volgende vragen na normaal verloop, na elke stap checken op status

// voorbeeld
// actie : volgendeBeurt
// input : ga naar volgende beurt

public class ActieBestuurder implements ActieInputs {
	
	public ActieBestuurder() {}
	
	// TODO_S sychronization!!!
	
	public void start(Actie start) {
		huidige = start;
		huidige.start();
	}
	
	public void undo() {
		synchronized(this) {
			huidige.undo();
			// kunnen we teruggaan naar de vorige actie?
			if(huidige.getUndo() == UNDO.BESCHIKBAAR) {
				huidige = stapel.pop();
				huidige.undo();
				huidige = huidige.volgende();
				huidige.start();
			}
		}		
	}

	// use in emergency, and in emergency ONLY
	public void stop() {		
		
	}
	
	public void controleerHuidige() {
		if(huidige.getStatus() == STATUS.GEDAAN && huidige.volgende()!=null) {
			stapel.add(huidige);
			huidige = huidige.volgende();
			huidige.start();
		}
	}
	
	//-------------------------
	
	// inputs van speltoolkit
	public void volgendeBeurt() {
		synchronized(this) {
			huidige.volgendeBeurt();
			controleerHuidige();
		}			
	}

	// TODO_S delete lol
	// stapel, lol
	Stack<Actie> stapel = new Stack<Actie>();
	Actie huidige = null;	
	
}
