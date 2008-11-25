package ambiorix.acties;

public abstract class Actie implements ActieInputs {
	
	public enum STATUS {ONKLAAR, KLAAR, BEZIG, GEDAAN, UNDO};
	public enum UNDO {NIET_BESCHIKBAAR, BESCHIKBAAR};
	
	//
	// ONKLAAR -> constructor -> KLAAR
	// KLAAR -> start() -> BEZIG / GEDAAN
	// BEZIG -> inputs() -> BEZIG / GEDAAN
	
	// undo:
	// ONKLAAR, KLAAR -> negeren
	// BEZIG -> (dingen zijn veranderd, bijhouden !!!) undo -> KLAAR brengen
	// GEDAAN -> undo, enkel bij beschikbaar -> UNDO
	
	abstract void start();

	abstract void undo();
	
	abstract Actie volgende();
	
	public STATUS getStatus() {
		return status;
	}

	public UNDO getUndo() {
		return undo;
	}	
	
	// member vars;
	
	private STATUS status = STATUS.ONKLAAR;
	private UNDO undo = UNDO.BESCHIKBAAR;

	
}
