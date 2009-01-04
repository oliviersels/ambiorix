package ambiorix.acties.specifiek;

import java.util.HashMap;
import java.util.Iterator;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.Terrein;

public class EindeSpel extends AbstractActie {

	public EindeSpel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		/*
		 * We gaan alle pionnen opvragen.. Daar gebieden van berekenen en dan
		 * van scorende gebieden pionnen afhalen.
		 */
		HashMap<Pion, Terrein> pionnen = kit.getPionnenEnPosities();
		Iterator<Pion> it = pionnen.keySet().iterator();
		ScoreBerekenaar sb = kit.getScoreBerekenaar();
		sb.zetEindeSpel(true);
		while (it.hasNext()) {
			Pion p = it.next();
			Terrein t = pionnen.get(p);
			Gebied g = kit.getGebied(t);
			for (Pion p2 : g.getPionnen()) {
				int score = 0;
				score = sb.berekenScore(g, p2.getSpeler());
				if (score > 0)
					p2.getSpeler().addScore(score);
			}
			/* Alle pionnen verwijderen */
			for (Pion p3 : g.getPionnen()) {
				kit.geefSpelerPion(p3, p3.getSpeler());
				kit.verwijderPion(p3);
			}
			pionnen = kit.getPionnenEnPosities();
			it = pionnen.keySet().iterator();
		}

		kit.toonEindeSpel();

		return null;
	}

	@Override
	protected String getSpecifiekID() {
		return "EindeSpel";
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
