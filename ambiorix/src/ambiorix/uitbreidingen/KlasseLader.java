package ambiorix.uitbreidingen;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

// http://www.exampledepot.com/egs/java.lang/LoadClass.html
/*
 * Gaat in het opgegeven pad zoeken naar de gevraagde classes 
 * 
 */
public class KlasseLader<T> {
	ClassLoader lader = null;

	public KlasseLader(String pad) {
		File file = new File(pad);

		try {
			// Convert File to a URL
			URL url = file.toURI().toURL();
			URL[] urls = new URL[] { url };

			// Create a new class loader with the directory
			lader = new URLClassLoader(urls);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Class<T> LaadKlasse(String naam) {
		Class<T> output = null;

		try {
			output = (Class<T>) lader.loadClass(naam);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}
}
