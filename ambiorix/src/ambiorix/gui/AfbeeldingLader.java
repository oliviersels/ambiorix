package ambiorix.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class AfbeeldingLader {
	private static Vector<BufferedImage> geladenAfbeeldingen = new Vector <BufferedImage>();
	private static Vector<String> geladenAfbeeldingNamen = new Vector <String>();
	private static String afBeeldingMap = "";
	public static BufferedImage geefAfbeelding(String naam)
	{
		int index = geladenAfbeeldingNamen.indexOf(naam);
		if(index != -1)
		{
			return geladenAfbeeldingen.get(index);
		}else
		{
			try {
				BufferedImage nieuweAfbeelding = ImageIO.read(new File(afBeeldingMap + naam));
				if(nieuweAfbeelding != null)
				{
					geladenAfbeeldingen.add(nieuweAfbeelding);
					geladenAfbeeldingNamen.add(naam);
				}
				return nieuweAfbeelding;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
}
