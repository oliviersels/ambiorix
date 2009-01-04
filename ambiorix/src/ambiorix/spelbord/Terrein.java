package ambiorix.spelbord;

import ambiorix.util.Punt;

public class Terrein implements TerreinBasis {
	private Tegel tegel = null;
	private Punt positie = null;

	public Terrein(Tegel tegel, Punt positie) {
		this.tegel = tegel;
		this.positie = positie;
	}

	public TerreinType getType() {
		return tegel.getTerreinType(positie);
	}

	public Tegel getTegel() {
		return tegel;
	}

	public void setTegel(Tegel tegel) {
		this.tegel = tegel;
	}

	public Punt getPositie() {
		return positie;
	}

	public void setPositie(Punt positie) {
		this.positie = positie;
	}

	@Override
	public String toString() {
		return tegel.getID() + "-" + positie.getX() + "," + positie.getY();
	}

}
