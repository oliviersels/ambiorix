package ambiorix.uitbreidingen;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import ambiorix.spelbord.*;
import ambiorix.util.Type;
import ambiorix.xml.XmlNode;

/*
 * Gebruikt het Proxy-designpattern om uitbreidingen aan te spreken.
 * Tot de functie bereidVoor wordt aangeroepen, is de uitbreiding nog niet ingeladen in het systeem,
 * en geldt dit object enkel als "placeholder".
 */
public class Uitbreiding extends Type implements UitbreidingInterface
{
	private String naam = null;
	private String uitbreidingPad = "";
	private UitbreidingImplementatie implementatie = null;
	
	private String afbeelding = null;
	private String beschrijving = null;
	
	public Uitbreiding(String pad, String naam)
	{
		this.naam = naam;
		uitbreidingPad = pad + naam + "/";
		// zoeken naar de Uitbreiding met deze naam
		File uitbreiding = new File( uitbreidingPad + "info.xml");
		if( !uitbreiding.exists() )
		{
			// TODO : throw exception
			System.out.println("Uitbreiding::Constructor : kan uitbreiding met naam " + naam + " niet vinden");
		}
		
		// inhoud van de info.xml opslaan, hebben we later nog nodig.
		String xmlinhoud = ambiorix.util.File.getContents( uitbreiding );
		XmlNode info = XmlNode.fromString(xmlinhoud);
		info = info.getChild("info");
		
		this.afbeelding = info.getChild("afbeelding").getValue();
		this.beschrijving = info.getChild("beschrijving").getValue();
		this.ID = info.getChild("type").getValue();
	}
	
	private void laadImplementatie()
	{
		if( implementatie == null )
		{
			// uitbreiding class laden van filesystem
			try
			{
				KlasseLader<UitbreidingImplementatie> lader = new KlasseLader<UitbreidingImplementatie>(uitbreidingPad);
				Class<UitbreidingImplementatie> imp = lader.LaadKlasse("ambiorix.uitbreidingen.implementaties." + ID );
				implementatie = imp.newInstance();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void bereidVoor()
	{
		laadImplementatie();
		
		implementatie.bereidVoor();

	}
	
	public String getAfbeelding()
	{
		return uitbreidingPad + afbeelding;
	}
	
	public String getBeschrijving()
	{
		return beschrijving;
	}
	
	public String getNaam()
	{
		return naam;
	}
	
	/*public void leesIn()
	{
		// terreintypes
		File terreinTypeDir = new File( uitbreidingPad + "terreintypes/" );
		String[] terreinTypeFiles = terreinTypeDir.list();
		
		for( String terreinType : terreinTypeFiles )
		{
			
		}
	}*/
}
