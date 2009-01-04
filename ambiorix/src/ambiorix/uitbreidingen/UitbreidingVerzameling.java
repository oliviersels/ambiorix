package ambiorix.uitbreidingen;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import ambiorix.ai.Ai;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.util.TypeVerzameling;

public class UitbreidingVerzameling extends TypeVerzameling<Uitbreiding> {
	private Vector<Uitbreiding> ingeladenUitbreidingen = null;

	protected UitbreidingVerzameling() {

	}

	/*
	 * Gaat in het opgegeven pad zoeken naar uitbreidingen (neem aan dat er
	 * enkel uitbreidingenfolders in die map zit!)
	 */
	public void vulMetUitbreidingenUit(String pad) {
		// FIXME : algemeen voor files : GEBRUIK File.SEPARATOR ipv / of \
		// ook aanpassen in implementaties zefl !!!

		// jammer genoeg kan File niet gewoon alle directories listen zonder de
		// files...
		// thumbs.db en .DS_Store enzo moeten dus gefilterd worden... om nog
		// maar te zwijgen van .svn :)
		// ActionScript in Flex kan dit trouwens WEL gewoon, take THAT java
		//
		// Probeer dit eens:
		// File dirs[] = map.listFiles(new FileFilter() {
		// public boolean accept(File pathName) {
		// return pathName.isDirectory();
		// }
		// });

		File map = new File(pad);
		String[] uitbreidingenMappen = map.list();

		for (String uitbreidingMap : uitbreidingenMappen) {
			File test = new File(pad + uitbreidingMap + "/info.xml");
			if (test.exists()) {
				Uitbreiding uitbreiding = new Uitbreiding(pad, uitbreidingMap);
				this.registreerType(uitbreiding);
			}
		}
	}

	public void bereidUitbreidingenVoor(Vector<String> uitbreidingenIDS) {
		// Basis moet er ALTIJD bij, dus deze zelf toevoegen
		uitbreidingenIDS.add("Uitbreiding_Basis");

		// de uitbreidingen moeten in een bepaalde VOLGORDE worden ingelezen
		// dit om overschrijfbaarheid te behouden.

		// eerst rangschikken op volgnummer dus

		ingeladenUitbreidingen = new Vector<Uitbreiding>();

		for (String uitbreidingID : uitbreidingenIDS) {
			ingeladenUitbreidingen.add(this.getType(uitbreidingID));
		}

		if (uitbreidingenIDS.size() > 0)
			Collections.sort(ingeladenUitbreidingen, this
					.getType(uitbreidingenIDS.get(0)).new Sorteerder());

		for (Uitbreiding uitbreiding : ingeladenUitbreidingen)
			uitbreiding.bereidVoor(uitbreidingenIDS);
	}

	public String getEersteActie() {
		// gaan ervanuit dat de lijst gesorteerd is
		// zodat de meest recente uitbreiding eerst wordt ingelezen

		for (int i = ingeladenUitbreidingen.size() - 1; i >= 0; i--) {
			if (ingeladenUitbreidingen.get(i).getEersteActie() != null)
				return ingeladenUitbreidingen.get(i).getEersteActie();
		}

		// FIXME : THROW EXCEPTION
		System.out
				.println("UitbreidingVerzameling::getEersteActie : geen gevonden");
		return null;
	}

	public ScoreBerekenaar getScoreBerekenaar() {
		// gaan ervanuit dat de lijst gesorteerd is
		// zodat de meest recente uitbreiding eerst wordt ingelezen

		for (int i = ingeladenUitbreidingen.size() - 1; i >= 0; i--) {
			if (ingeladenUitbreidingen.get(i).getScoreBerekenaar() != null)
				return ingeladenUitbreidingen.get(i).getScoreBerekenaar();
		}

		// FIXME : THROW EXCEPTION
		System.out
				.println("UitbreidingVerzameling::getScoreBerekenaar : geen gevonden");
		return null;
	}

	public Ai getAi() {
		// gaan ervanuit dat de lijst gesorteerd is
		// zodat de meest recente uitbreiding eerst wordt ingelezen

		for (int i = ingeladenUitbreidingen.size() - 1; i >= 0; i--) {
			if (ingeladenUitbreidingen.get(i).getAi() != null)
				return ingeladenUitbreidingen.get(i).getAi();
		}

		// FIXME : THROW EXCEPTION
		System.out.println("UitbreidingVerzameling::getAi : geen gevonden");
		return null;
	}

	@Override
	public Vector<String> getTypes() {
		Vector<String> output = super.getTypes();

		// Basis uitbreiding moet er altijd bij. Dit doen we hardcoded binnen
		// deze verzameling. Dus niet
		// zichtbaar maken aan de buitenwereld.
		output.remove(output.indexOf("Uitbreiding_Basis"));

		return output;
	}

	private static UitbreidingVerzameling instantie = null;

	public static UitbreidingVerzameling getInstantie() {
		if (instantie == null)
			instantie = new UitbreidingVerzameling();

		return instantie;
	}
}
