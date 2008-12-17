package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;

public class StartSpel extends AbstractActie {

	public StartSpel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		// TODO: Voeg alle tegels toe aan de pool (juiste aantallen)
		kit.setTegelAantal("TegelType_WGGWW", 4);
		kit.setTegelAantal("TegelType_RechteWeg", 4);
		kit.setTegelAantal("TegelType_Driesprong", 3);
		
		// TODO: Zet de starttegel
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_BurchtMetBochtweg"));
		kit.setBegintegel(t);
		
		return new GeefTegel(kit, this);
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false;
	}

	@Override
	public AbstractActie maakOngedaan() {
		return null;
	}

}
