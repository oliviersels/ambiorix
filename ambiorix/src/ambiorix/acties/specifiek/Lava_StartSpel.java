package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.acties.ActieVerzameling;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelers.Speler;

public class Lava_StartSpel extends AbstractActie {

	public Lava_StartSpel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		kit.setTegelAantal("TegelType_WGGWW", 5);
		kit.setTegelAantal("TegelType_RechteWeg", 6);
		kit.setTegelAantal("TegelType_Driesprong", 4);
		kit.setTegelAantal("TegelType_EenZijdeBurcht", 6);
		kit.setTegelAantal("TegelType_BBBBB", 3);
		kit.setTegelAantal("TegelType_GGGGK", 4);
		kit.setTegelAantal("TegelType_BurchtMetBochtweg", 5);

		/*
		 * kit.setTegelAantal("TegelType_LavaMetWeg", 4);
		 * kit.setTegelAantal("TegelType_LavaMetBurchten", 4);
		 * kit.setTegelAantal("TegelType_LavaBocht", 4);
		 * kit.setTegelAantal("TegelType_LavaRecht", 4);
		 */

		// TODO: Spelers juiste pionnen geven
		for (Speler s : kit.getSpelers()) {
			for (int i = 0; i < 7; i++)
				kit.geefSpelerPion(new Pion(0, PionTypeVerzameling
						.getInstantie().getType("PionType_Volgeling"), s), s);
		}

		// Actieve Speler instellen (de eerste)
		kit.setActieveSpeler(kit.getSpelers().get(0));

		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaRecht"));
		kit.setBegintegel(t);

		// ERBOVEN
		Tegel t2 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaBocht"));
		kit.zetTegel(kit.getSpelers().get(0), t2, new BordPositie(t,
				Tegel.RICHTING.BOVEN));

		Tegel t3 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaBocht"));
		t3.setRotatie(180);
		kit.zetTegel(kit.getSpelers().get(0), t3, new BordPositie(t2,
				Tegel.RICHTING.RECHTS));

		Tegel t4 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaRecht"));
		t4.setRotatie(180);
		kit.zetTegel(kit.getSpelers().get(0), t4, new BordPositie(t3,
				Tegel.RICHTING.BOVEN));

		Tegel t5 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaMetWeg"));
		t5.setRotatie(90);
		kit.zetTegel(kit.getSpelers().get(0), t5, new BordPositie(t4,
				Tegel.RICHTING.BOVEN));

		Tegel t6 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaPoel"));
		t6.setRotatie(270);
		kit.zetTegel(kit.getSpelers().get(0), t6, new BordPositie(t5,
				Tegel.RICHTING.BOVEN));

		// ERONDER
		Tegel t7 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaMetBurchten"));
		t7.setRotatie(90);
		kit.zetTegel(kit.getSpelers().get(0), t7, new BordPositie(t,
				Tegel.RICHTING.ONDER));

		Tegel t8 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaRecht"));
		t8.setRotatie(180);
		kit.zetTegel(kit.getSpelers().get(0), t8, new BordPositie(t7,
				Tegel.RICHTING.ONDER));

		Tegel t9 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaMetWeg"));
		t9.setRotatie(90);
		kit.zetTegel(kit.getSpelers().get(0), t9, new BordPositie(t8,
				Tegel.RICHTING.ONDER));

		Tegel t10 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaRecht"));
		kit.zetTegel(kit.getSpelers().get(0), t10, new BordPositie(t9,
				Tegel.RICHTING.ONDER));

		Tegel t11 = new Tegel(TegelTypeVerzameling.getInstantie().getType(
				"TegelType_LavaPoel"));
		t11.setRotatie(90);
		kit.zetTegel(kit.getSpelers().get(0), t11, new BordPositie(t10,
				Tegel.RICHTING.ONDER));

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
	public boolean kanOngedaanMaken() {
		return false;
	}

	@Override
	public AbstractActie maakOngedaan() {
		return null;
	}

	@Override
	protected String getSpecifiekID() {
		return "StartSpel";
	}

}