package ambiorix.spelbord;

import ambiorix.util.Type;

public class PionType extends Type {

	public PionType(String ID) {
		this.ID = ID;
	}

	public String getAfbeelding() {
		// dit is standaard, als de images ergens anders zitten (bijv. bij
		// uitbreidingen)
		// moet deze functie overschreven worden door de specifieke tegelTypes
		return "uitbreidingen/Basis/afbeeldingen/pionnen/" + this.ID + ".png";
	}
}
