package ambiorix.acties;

import ambiorix.SpelToolkit;

/**
 * Deze klasse biedt basisfunctionaliteit voor concrete Acties.
 * @author olivier
 */
public abstract class AbstractActie implements Actie {
	protected SpelToolkit kit;
	protected AbstractActie vorigeActie;
	protected AbstractActie volgendeActie;
	
	public AbstractActie(SpelToolkit kit, AbstractActie vorige) {
		this.kit = kit;
		vorigeActie = vorige;
		volgendeActie = null;
	}
	
	/**
	 * Subklassen van AbstractActie moeten niet start() overwriten maar 
	 * doeActie(). start() zal doeActie() en maakOngedaan() automatisch
	 * aanroepen.
	 */
	public Actie start() {
		if(volgendeActie != null) { // Dit betekent dat we undo bezig zijn
			AbstractActie temp = maakOngedaan();
			if(temp == null) { // We kunnen niet ongedaan maken
				return volgendeActie;
			}
			else {
				vorigeActie = temp;
				return vorigeActie;
			}
		}
		else { // Hier doen we de echte actie
			volgendeActie = doeActie();
			return volgendeActie;
		}
	}
	
	/**
	 * Wordt automatisch aangeroepen als de echte actie moet gebeuren
	 * @return De volgende actie die uitgevoerd moet worden
	 */
	public abstract AbstractActie doeActie();
	
	/**
	 * Wordt automatisch aangeroepen als de actie ongedaan gemaakt moet worden.
	 * @return De volgende actie die uitgevoerd moet worden (normaal de
	 * vorige actie) of null indien deze actie niet ongedaan gemaakt kan worden.
	 */
	public abstract AbstractActie maakOngedaan();
	
	/**
	 * Hiermee geeft de actie aan of ze ongedaan gemaakt kan worden. Opgelet:
	 * dit slaat op het ongedaan maken vanaf het einde tot de laatste invoer.
	 * Als de rest van de actie niet ongedaan gemaakt kan worden, dan moet toch
	 * true teruggegeven worden.
	 * @return
	 */
	public abstract boolean kanOngedaanMaken();
}
