package ambiorix.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class File 
{
	public static String getContents(String filename)
	{
		String output = "";
		java.io.File file = new java.io.File(filename);
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line = "";
			while( line != null )
			{
				output += line;
				line = reader.readLine();
			}
			
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static void writeContents(String contents, String filename)
	{
		java.io.File file = new java.io.File(filename);
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(contents);
			
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
	}
}
