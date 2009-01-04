package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.acties.UndoException;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Speler;

public class ZetPion extends AbstractActie {
	private Pion gekozenPion;
	private Terrein gekozenTerrein;
	private Tegel vorigeTegel;

	public ZetPion(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
		vorigeTegel = null;
	}

	public ZetPion(SpelToolkit kit, AbstractActie vorige, Tegel vorigeTegel) {
		super(kit, vorige);
		this.vorigeTegel = vorigeTegel;
	}

	private boolean controleerPositie(Pion p, Terrein t) {
		if (p == null || t == null)
			return false;

		// Check of we wel op dezelfde tegel plaatsen als de vorige
		if (vorigeTegel != null) {
			if (!vorigeTegel.equals(t.getTegel()))
				return false;
		}

		return kit.controleerPlaatsbaarheid(p, t);
	}

	@Override
	public AbstractActie doeActie() {
		Speler actieveSpeler = kit.getActieveSpeler();

		// Heeft de speler nog pionnen?
		if (actieveSpeler.getAantalPionnen() != 0) {
			gekozenPion = null;
			gekozenTerrein = null;

			while (!controleerPositie(gekozenPion, gekozenTerrein)) {
				if (gekozenPion != null)
					kit.geefSpelerPion(gekozenPion, actieveSpeler);
				try {
					gekozenPion = kit.selecteerSpelerPion(actieveSpeler);
				} catch (InterruptedException e) {
					return null; // Spel afgelopen
				} catch (UndoException e) {
					return vorigeActie;
				}
				// We willen geen pion zetten!
				if (gekozenPion == null)
					break;
				try {
					gekozenTerrein = kit.selecteerTegelGebied(actieveSpeler);
				} catch (InterruptedException e) {
					return null; // Spel gedaan
				} catch (UndoException e) {
					return vorigeActie;
				}
				kit.neemSpelerPionAf(gekozenPion, actieveSpeler);
			}
			if (gekozenPion != null) {
				kit.zetPion(actieveSpeler, gekozenPion, gekozenTerrein);
			}
		}
		System.out.println("voor score bereken");
		try {
			Object[] param = { kit, this, vorigeTegel };
			Class<?>[] paramKlassen = { SpelToolkit.class, AbstractActie.class,
					Tegel.class };
			return ActieVerzameling.getInstantie().getNewInstantie(
					"BerekenScore", param, paramKlassen);
		} catch (Exception e) {
			System.err.println("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean kanOngedaanMaken() {
		// TODO Nog niet
		return false;
	}

	@Override
	public AbstractActie maakOngedaan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSpecifiekID() {
		return "ZetPion";
	}

}
