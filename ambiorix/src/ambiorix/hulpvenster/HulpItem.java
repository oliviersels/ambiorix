package ambiorix.hulpvenster;

public class HulpItem {
	private String naam;
	private String beschrijving;
	private static final String scheiding = ">>>======>";

	public HulpItem() {
		super();
	}

	/*
	 * Constructor die een String aanneemt die gegenereerd is met naarString().
	 */
	public HulpItem(String s) {
		vanString(s);
	}

	public HulpItem(String naam, String beschrijving) {
		super();
		this.naam = naam;
		this.beschrijving = beschrijving;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public String naarString() {
		String rString = naam + scheiding + beschrijving;
		rString.replace("\n", "<br>");
		return rString;
	}

	public void vanString(String s) {
		s.replace("<br>", "\n");
		String split[] = s.split(scheiding);
		naam = split[0];
		beschrijving = split[1];

	}

	@Override
	/*
	 * Geeft true terug als de namen gelijk zijn.
	 */
	public boolean equals(Object o) {
		return ((HulpItem) o).naam.equals(this.naam);
	}
}
