package ambiorix.uitbreidingen;

import java.io.File;
import java.util.Comparator;
import java.util.Vector;

import ambiorix.spelbord.ScoreBerekenaar;
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
	private int volgnummer = -1;
	
	private Vector<String> compatibelMet = new Vector<String>();
	
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
		
		this.volgnummer = Integer.parseInt( info.getChild("nr").getValue() );
		this.afbeelding = info.getChild("afbeelding").getValue();
		this.beschrijving = info.getChild("beschrijving").getValue();
		this.ID = info.getChild("type").getValue();
		
		XmlNode compatibelMetNode = info.getChild("compatibel_met");
		Vector<XmlNode> compatibeleUitbreidingen = compatibelMetNode.getChildren("uitbreiding");
		
		for( XmlNode compatibeleUitbreiding : compatibeleUitbreidingen )
		{
			this.compatibelMet.add( compatibeleUitbreiding.getValue() );
		}
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
	
	@Override
	public void bereidVoor(Vector<String> andereUitbreidingen)
	{
		laadImplementatie();
		
		implementatie.bereidVoor(andereUitbreidingen);

	}
	
	public boolean isCompatibelMet(String uitbreidingType)
	{
		// eerst checken op wildcard 
		if( (compatibelMet.size() == 1) && (compatibelMet.get(0) == "*") )
			return true;
		else
		{
			return compatibelMet.contains(uitbreidingType);
		}
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
	
	public int getVolgnummer()
	{
		return this.volgnummer;
	}
	
	@Override
	public String getEersteActie() 
	{
		laadImplementatie();
		
		return implementatie.getEersteActie();
	}

	@Override
	public ScoreBerekenaar getScoreBerekenaar() 
	{
		laadImplementatie();
		
		return implementatie.getScoreBerekenaar();
	}
	

	public class Sorteerder implements Comparator<Uitbreiding>
	{
		public int compare(Uitbreiding t1, Uitbreiding t2)
		{
			//System.out.println("COMPARE : " + t1.getID() + " <> " + t2.getID() );
			
			if( t1.getVolgnummer() < t2.getVolgnummer() )
				return -1;
			if( t1.getVolgnummer() > t2.getVolgnummer() )
				return 1;
			
			return 0;
		}	
	}




		
}
