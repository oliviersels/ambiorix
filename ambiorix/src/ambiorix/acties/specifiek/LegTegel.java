package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Tegel;
import ambiorix.spelers.Speler;

public class LegTegel extends AbstractActie {
	private Tegel gekozenTegel;
	private BordPositie positie;

	public LegTegel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}
	
	private boolean controleerPositie(BordPositie p, Tegel t) {
		if(p == null || t == null)
			return false;
		return kit.controleerPlaatsbaarheid(t, p);
	}

	@Override
	public AbstractActie doeActie() {
		gekozenTegel = null;
		positie = null;
		Speler actieveSpeler = kit.getActieveSpeler();
		
		while(!controleerPositie(positie, gekozenTegel)) {
			if(gekozenTegel != null)
				kit.geefSpelerTegel(gekozenTegel, actieveSpeler);
			
			try {
				gekozenTegel = kit.selecteerSpelerTegel(actieveSpeler);
			} catch (InterruptedException e) {
				return null; // Huidige spel is afgelopen
			}
			
			try {
				positie = kit.selecteerBordPositie(actieveSpeler);
			} catch (InterruptedException e) {
				return null;
			}
			
			kit.neemSpelerTegelAf(gekozenTegel, actieveSpeler);
		}
		kit.zetTegel(actieveSpeler, gekozenTegel, positie);

		try {
			Object[] param = {kit, this, gekozenTegel};
			Class<?>[] paramKlassen = {SpelToolkit.class, AbstractActie.class, Tegel.class};
			return ActieVerzameling.getInstantie().getNewInstantie("ZetPion", param, paramKlassen);
		} catch (Exception e) {
			System.err.println("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false; // TODO: Ongedaan maken van LegTegel
	}

	@Override
	public AbstractActie maakOngedaan() {
		System.out.println("legtegel -> Ongedaan maken");
		System.out.println("legtegel -> tegel verwijderen van speelbord en teruggeven aan speler");

		return null;
	}

	@Override
	protected String getSpecifiekID() {
		return "LegTegel";
	}
	
	

}
