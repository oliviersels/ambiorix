package ambiorix.spelbord;

import ambiorix.spelers.Speler;

public interface ScoreBerekenaar {
	public int berekenScore(Gebied gebied, Speler speler);

	public void zetEindeSpel(boolean eindeSpel);

	public boolean isEindeSpel();
}
