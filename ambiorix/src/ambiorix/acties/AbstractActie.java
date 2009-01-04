package ambiorix.acties;

import ambiorix.SpelToolkit;
import ambiorix.util.Type;

/**
 * Deze klasse biedt basisfunctionaliteit voor concrete Acties. Tot deze
 * basisfunctionaliteit behoort de afhandeling van undo. Subklassen moeten dus
 * ook niet meer start() overwriten maar doeActie() en maakOngedaan().<br/>
 * <br/>
 * doeActie() wordt aangeroepen indien de actie start en echt uitgevoerd dient
 * te worden.<br/>
 * maakOngedaan() wordt aangeroepen als de actie afgelopen is maar ongedaan
 * gemaakt moet worden. Dit moet maar tot de vorige input gebeuren.
 * 
 * @author olivier
 */
public abstract class AbstractActie extends Type implements Actie {
	protected SpelToolkit kit;
	protected AbstractActie vorigeActie;
	protected AbstractActie volgendeActie;

	public AbstractActie(SpelToolkit kit, AbstractActie vorige) {
		this.kit = kit;
		vorigeActie = vorige;
		volgendeActie = null;
		ID = getSpecifiekID();
	}

	protected abstract String getSpecifiekID();

	/**
	 * Subklassen van AbstractActie moeten niet start() overwriten maar
	 * doeActie(). start() zal doeActie() en maakOngedaan() automatisch
	 * aanroepen.
	 */
	public Actie start() {
		if (volgendeActie != null) { // Dit betekent dat we undo bezig zijn
			AbstractActie temp = maakOngedaan();
			if (temp == null) { // We kunnen niet ongedaan maken
				return volgendeActie;
			} else {
				vorigeActie = temp;
				return vorigeActie;
			}
		} else { // Hier doen we de echte actie
			volgendeActie = doeActie();
			return volgendeActie;
		}
	}

	/**
	 * Wordt automatisch aangeroepen als de echte actie moet gebeuren
	 * 
	 * @return De volgende actie die uitgevoerd moet worden
	 */
	public abstract AbstractActie doeActie();

	/**
	 * Wordt automatisch aangeroepen als de actie ongedaan gemaakt moet worden.
	 * 
	 * @return De volgende actie die uitgevoerd moet worden (normaal de vorige
	 *         actie) of null indien deze actie niet ongedaan gemaakt kan
	 *         worden.
	 */
	public abstract AbstractActie maakOngedaan();

	/**
	 * Hiermee geeft de actie aan of ze ongedaan gemaakt kan worden. Opgelet:
	 * dit slaat op het ongedaan maken vanaf het einde tot de laatste invoer.
	 * Als de rest van de actie niet ongedaan gemaakt kan worden, dan moet toch
	 * true teruggegeven worden.
	 */
	public abstract boolean kanOngedaanMaken();
}
