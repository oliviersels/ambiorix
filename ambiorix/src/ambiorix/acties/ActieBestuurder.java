package ambiorix.acties;

import java.util.EmptyStackException;
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
	
	public void start(Actie start) {
		synchronized(this) {
			if(huidige==null) {
				huidige = start;
				huidige.start();
				controleerHuidige();
			}
		}
	}
	
	public void undo() {
		if(huidige==null)
			return;
		
		synchronized(this) {
			huidige.undo();
			System.out.println("-----> UNDO");
			// kunnen we teruggaan naar de vorige actie?
			if(huidige.getUndo() == UNDO.BESCHIKBAAR) {				
				try {
					System.out.println("-----> UNDO BESCHIKBAAR");
					huidige = stapel.pop();
					huidige.undo();
					huidige.start();
					controleerHuidige();
				} catch (EmptyStackException e) {
					// geen vorige acties!
				}
			}
		}		
	}

	// use in emergency, and in emergency ONLY
	public void stop() {		
		
	}
	
	private void controleerHuidige() {
		if(huidige.getStatus() == STATUS.GEDAAN) {
			System.out.println("-----> ADD");
			stapel.add(huidige);
			huidige = huidige.volgende();
			if(huidige == null) {
				System.out.println("-----> SPELCYCLUS AFGELOPEN");
				return;
			}
			huidige.start();
			controleerHuidige();
		}
	}
	
	//-------------------------
	
	// inputs van speltoolkit
	public void volgendeBeurt() {
		if(huidige==null)
			return;
		
		System.out.println("-----> VOLGENDE BEURT");
		synchronized(this) {
			huidige.volgendeBeurt();
			controleerHuidige();
		}			
	}
	
	public void legTegel() {
		if(huidige==null)
			return;
		
		System.out.println("-----> LEG TEGEL");
		synchronized(this) {
			huidige.legTegel();
			controleerHuidige();
		}			
	}
	
	public void zetPion() {
		if(huidige==null)
			return;
		
		System.out.println("-----> ZET PION");
		synchronized(this) {
			huidige.zetPion();
			controleerHuidige();
		}			
	}

	// TODO_S delete lol
	// stapel, lol
	Stack<Actie> stapel = new Stack<Actie>();
	Actie huidige = null;	
	
}
