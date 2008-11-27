package ambiorix.acties;

public abstract class Actie implements ActieInputs {
	
	public enum STATUS {ONKLAAR, KLAAR, BEZIG, GEDAAN, UNDO};
	public enum UNDO {NIET_BESCHIKBAAR, BESCHIKBAAR};
	
	// ONKLAAR -> constructor -> KLAAR
	// KLAAR -> start() -> BEZIG / GEDAAN
	// BEZIG -> inputs() -> BEZIG / GEDAAN
	
	// undo:
	// ONKLAAR, KLAAR -> negeren
	// BEZIG -> (dingen zijn veranderd, bijhouden !!!) undo -> KLAAR brengen
	// GEDAAN -> undo, enkel bij beschikbaar -> UNDO
	
	public abstract void start();

	public abstract void undo();
	
	public abstract Actie volgende();
	
	public STATUS getStatus() {
		return status;
	}

	public UNDO getUndo() {
		return undo;
	}	
	
	// interface inputs
	
	public void volgendeBeurt() {}
	public void legTegel() {}
	public void zetPion() {}
	
	
	// member vars;
	
	protected STATUS status = STATUS.ONKLAAR;
	protected UNDO undo = UNDO.BESCHIKBAAR;
	
}
