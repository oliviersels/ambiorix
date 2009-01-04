package ambiorix.uitbreidingen.implementaties;

import java.util.Vector;

import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.uitbreidingen.UitbreidingImplementatie;

public class Uitbreiding_Lava extends UitbreidingImplementatie {

	public Uitbreiding_Lava() {
		uitbreidingPad = "uitbreidingen/Lava/";
	}

	@Override
	public void bereidVoor(Vector<String> andereUitbreidingen) {
		super.bereidVoor(andereUitbreidingen);
	}

	@Override
	public ScoreBerekenaar getScoreBerekenaar() {
		return super.getScoreBerekenaar();
	}

	@Override
	public String getEersteActie() {
		return "StartSpel";
	}
}
