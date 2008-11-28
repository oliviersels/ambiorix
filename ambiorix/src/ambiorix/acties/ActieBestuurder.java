package ambiorix.acties;

import java.util.EmptyStackException;
import java.util.Stack;

import ambiorix.SpelToolkit;
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
	
	private Stack<Actie> vorigeActies = new Stack<Actie>();
	private Actie huidige = null;
	
	private SpelToolkit speltoolkit;
	
	public ActieBestuurder(SpelToolkit speltoolkit) {
		this.speltoolkit = speltoolkit;
	}

	public void start(Actie start) {
		synchronized(this) {
			if(start==null)
				return;
			if(huidige==null) {
				huidige = start;
				huidige.setSpeltoolkit(speltoolkit);
				huidige.start();
				controleerHuidige();
			}
		}
	}
	
	public void undo() {		
		synchronized(this) {
			if(huidige==null)
				return;
			
			huidige.undo();
			System.out.println("-----> UNDO");
			// kunnen we teruggaan naar de vorige actie?
			if(huidige.getUndo() == UNDO.BESCHIKBAAR) {				
				try {
					System.out.println("-----> UNDO BESCHIKBAAR");
					huidige = vorigeActies.pop();
					huidige.undo();
					huidige.setSpeltoolkit(speltoolkit);
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
	
	public boolean isBezig() {
		return (huidige!=null);
	}
	
	private void controleerHuidige() {
		if(huidige.getStatus() == STATUS.GEDAAN) {
			System.out.println("-----> ADD");
			vorigeActies.add(huidige);
			huidige = huidige.volgende();
			if(huidige == null) {
				System.out.println("-----> SPELCYCLUS AFGELOPEN");
				return;
			}
			huidige.setSpeltoolkit(speltoolkit);
			huidige.start();
			controleerHuidige();
		}
	}
	
	//-------------------------
	
	// inputs van speltoolkit
	public void volgendeBeurt() {		
		synchronized(this) {
			System.out.println("-----> VOLGENDE BEURT");
			if(huidige==null)
				return;
			huidige.volgendeBeurt();
			controleerHuidige();
		}			
	}
	
	public void legTegel() {
		synchronized(this) {
			System.out.println("-----> LEG TEGEL");
			if(huidige==null)
				return;
			huidige.legTegel();
			controleerHuidige();
		}			
	}
	
	public void zetPion() {
		synchronized(this) {
			System.out.println("-----> ZET PION");
			if(huidige==null)
				return;
			huidige.zetPion();
			controleerHuidige();
		}			
	}	
}
