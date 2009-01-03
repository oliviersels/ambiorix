package ambiorix.acties.specifiek;

import java.util.HashMap;
import java.util.Iterator;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;

public class BerekenScore extends AbstractActie {
	Tegel geplaatsteTegel;

	public BerekenScore(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
		geplaatsteTegel = null;
	}
	
	public BerekenScore(SpelToolkit kit, AbstractActie vorige, Tegel geplaatsteTegel) {
		super(kit, vorige);
		this.geplaatsteTegel = geplaatsteTegel;
	}

	@Override
	public AbstractActie doeActie() {
		System.out.println("begin scoreberekenen");
		if(geplaatsteTegel != null) {
			/*
			 * We gaan alle pionnen opvragen.. Daar gebieden van berekenen en dan
			 * van scorende gebieden pionnen afhalen.
			 */
			HashMap<Pion, Terrein> pionnen = kit.getPionnenEnPosities();
			Iterator<Pion> it = pionnen.keySet().iterator();
			while(it.hasNext()) {
				Pion p = it.next();
				Terrein t = pionnen.get(p);
				Gebied g = kit.getGebied(t);
				boolean gescoord = false;
				for(Pion p2 : g.getPionnen()) {
					int score = 0;
					score = kit.getScoreBerekenaar().berekenScore(g, p2.getSpeler());
					if(score > 0) {
						p2.getSpeler().addScore(score);
						gescoord = true;
					}
				}
				if(gescoord) {
					for(Pion p3 : g.getPionnen()) {
						kit.geefSpelerPion(p3, p3.getSpeler());
						kit.verwijderPion(p3);
					}
					pionnen = kit.getPionnenEnPosities();
					it = pionnen.keySet().iterator();
				}
			}
			
			try {
				Object[] param = {kit, this};
				Class<?>[] paramKlassen = {SpelToolkit.class, AbstractActie.class};
				return ActieVerzameling.getInstantie().getNewInstantie("EindeBeurt", param, paramKlassen);
			} catch (Exception e) {
				System.err.println("Unexpected Exception: " + e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		else
			return null;
	}

	@Override
	protected String getSpecifiekID() {
		return "BerekenScore";
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
