package ambiorix.spelbord;

import ambiorix.util.TypeVerzameling;

public class TegelTypeVerzameling extends TypeVerzameling<TegelType> {
	protected TegelTypeVerzameling() {

	}

	private static TegelTypeVerzameling instantie = null;

	public static TegelTypeVerzameling getInstantie() {
		if (instantie == null)
			instantie = new TegelTypeVerzameling();

		return instantie;
	}
}
