package ambiorix.acties.specifiek;

import java.lang.reflect.InvocationTargetException;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelbord.Tegel;

public class GeefTegel extends AbstractActie {
	Tegel gegevenTegel;
	

	public GeefTegel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
		
		gegevenTegel = null;
	}

	@Override
	public AbstractActie doeActie() {
		gegevenTegel = kit.getVolgendeTegel();
		if(gegevenTegel == null) {
			return null; // Onmiddelijk stoppen
		}
		kit.geefSpelerTegel(gegevenTegel, kit.getActieveSpeler());
		
		try {
			Object[] param = {kit, this};
			Class<?>[] paramKlassen = {SpelToolkit.class, AbstractActie.class};
			return ActieVerzameling.getInstantie().getNewInstantie("LegTegel", param, paramKlassen);
		} catch (Exception e) {
			System.err.println("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false; // TODO moet nog gemaakt worden
	}

	@Override
	public AbstractActie maakOngedaan() {
		System.out.println("geeftegel -> tegel van speler terug naar de verzameling");
		System.out.println("Terug aan speler vragen om tegel te leggen");
		
		return vorigeActie;
	}

	@Override
	protected String getSpecifiekID() {
		return "GeefTegel";
	}

}
