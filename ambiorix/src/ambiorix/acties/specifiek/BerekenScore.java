package ambiorix.acties.specifiek;

import java.util.Set;
import java.util.Vector;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelers.Speler;
import ambiorix.util.Punt;

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
			Vector<Punt> punten = geplaatsteTegel.getGebiedBeginPunten();
			for(Punt p : punten) {
				Terrein terrein = (Terrein)geplaatsteTegel.getTerrein(p);
				if(terrein.getType() == TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras"))
					continue;
				Gebied g = kit.getGebied((Terrein)geplaatsteTegel.getTerrein(p));
				int score = 0;
				for(Speler s : kit.getSpelers()) {
					score = kit.getScoreBerekenaar().berekenScore(g, s);
					if(score > 0) {
						s.addScore(score); // TODO: Moet via speltoolkit
						System.out.println("Speler " + s.getNaam() + " krijgt extra score " + score);
						break;
					}
				}
				if(score > 0) {
					Pion pionnen[] = new Pion[0];
					pionnen = g.getPionnen().toArray(pionnen);
					for(Pion pion : pionnen) {
						kit.verwijderPion(pion);
					}
				}
			}
			try {
				Object[] param = {kit, this};
				Class<?>[] paramKlassen = {SpelToolkit.class, AbstractActie.class};
				return ActieVerzameling.getInstantie().getNewInstantie("GeefTegel", param, paramKlassen);
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
