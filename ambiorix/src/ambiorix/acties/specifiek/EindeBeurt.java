package ambiorix.acties.specifiek;

import java.util.Vector;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelers.Speler;

public class EindeBeurt extends AbstractActie {

	public EindeBeurt(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		// TODO Kijken of het spel voorbij is. Als dit is -> eindespel actie
		// uitvoeren

		Vector<Speler> spelers = kit.getSpelers();
		Speler actief = kit.getActieveSpeler();
		for (int i = 0; i < spelers.size(); i++) {
			if (spelers.get(i) == actief) {
				if (i != spelers.size() - 1)
					kit.setActieveSpeler(spelers.get(i + 1));
				else
					kit.setActieveSpeler(spelers.get(0));
				break;
			}
		}

		try {
			Object[] param = { kit, this };
			Class<?>[] paramKlassen = { SpelToolkit.class, AbstractActie.class };
			return ActieVerzameling.getInstantie().getNewInstantie("GeefTegel",
					param, paramKlassen);
		} catch (Exception e) {
			System.err.println("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected String getSpecifiekID() {
		return "EindeBeurt";
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
