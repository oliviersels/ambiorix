package ambiorix.hulpvenster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

import ambiorix.util.File;

public class HulpVenster {
	private static final String FILE_NAAM = "hulp.txt";
	private Vector<HulpItem> hulpItems = new Vector<HulpItem>();

	private void laadVanStandardBestand()
	{
		hulpItems.clear();
		try {
			BufferedReader in = new BufferedReader(new FileReader(FILE_NAAM));
			String lijn;
			while((lijn = in.readLine()) != null)
			{
				HulpItem hi = new HulpItem(lijn);
				hulpItems.add(hi);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
