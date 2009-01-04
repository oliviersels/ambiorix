package ambiorix.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class File {
	public static String getContents(java.io.File file) {
		String output = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = "";
			while (line != null) {
				output += line;
				line = reader.readLine();
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	public static String getContents(String filename) {
		java.io.File file = new java.io.File(filename);

		return getContents(file);
	}

	public static void writeContents(String contents, String filename) {
		java.io.File file = new java.io.File(filename);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(contents);

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
