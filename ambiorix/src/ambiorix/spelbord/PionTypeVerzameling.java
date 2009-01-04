package ambiorix.spelbord;

import ambiorix.util.TypeVerzameling;

public class PionTypeVerzameling extends TypeVerzameling<PionType> {

	protected PionTypeVerzameling() {

	}

	private static PionTypeVerzameling instantie = null;

	public static PionTypeVerzameling getInstantie() {
		if (instantie == null)
			instantie = new PionTypeVerzameling();

		return instantie;
	}
}
