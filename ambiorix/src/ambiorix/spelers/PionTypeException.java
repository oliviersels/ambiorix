package ambiorix.spelers;

public class PionTypeException extends Exception {
	private static final long serialVersionUID = 1L;

	Object pionType; // TODO: moet klasse PionType worden
	
	public PionTypeException() {
		super();
		pionType = null;
	}

	public PionTypeException(String s, Throwable t) {
		super(s, t);
		pionType = null;
	}

	public PionTypeException(String s) {
		super(s);
		pionType = null;
	}

	public PionTypeException(Throwable t) {
		super(t);
		pionType = null;
	}
	
	public void zetPionType(Object pionType) {
		this.pionType = pionType;
	}
	
	Object getPionType() {
		return pionType;
	}
	
	static void throwNow(Object pionType) throws PionTypeException {
		PionTypeException e = new PionTypeException();
		e.zetPionType(pionType);
		throw e;
	}
}
