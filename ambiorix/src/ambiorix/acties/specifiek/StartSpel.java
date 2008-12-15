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
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_WGGWW"));
		kit.zetTegel(kit.getActieveSpeler(), t, new BordPositie(null, null));
		
		return new GeefTegel(kit, this);
	}

	@Override
	public boolean kanOngedaanMaken() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AbstractActie maakOngedaan() {
		// TODO Auto-generated method stub
		return null;
	}

}
