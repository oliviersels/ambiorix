package ambiorix.spelbord;

import java.util.Vector;

import org.w3c.dom.Node;

import ambiorix.util.Punt;
import ambiorix.xml.XmlNode;

public class Tegel implements TegelBasis
{
	private int ID = -1;
	private int rotatie = 0;
	private boolean draaibaar = true;
	
	private TegelType type = null;
	
	// positieindexen komen overeen met indexen op type.terrein
	private Pion[][] pionPosities = null; 
	private TerreinType[][] terrein = null;
	
	private Tegel[] buren = new Tegel[4];
	
	private TegelGebiedBeheerder gebiedBeheerder = null;
	
	public Tegel(TegelType type)
	{
		setType(type);
		
		for(int i = 0; i < buren.length; i++)
			buren[i] = null;
		
		//gebiedBeheerder = new TegelGebiedBeheerder(this);
	}
	
	public void setType(TegelType type)
	{
		this.type = type;
		
		this.pionPosities = new Pion[type.terrein.length][type.terrein[0].length];
		
		for(int i = 0; i < pionPosities.length; i++)
			for(int j = 0; j < pionPosities[0].length; j++)
				pionPosities[i][j] = null;	
		
		setRotatie(rotatie);
	}

	/*
	 * Roteert de tegel met een bepaald aantal graden.
	 * OPGELET : is NIET incrementeel !
	 * maw : setRotatie(90) en daarna setRotatie(180) betekent een rotatie van 180, niet van 270 !!!
	 */
	public void setRotatie(int rotatie) 
	{
		if( (rotatie == this.rotatie) && ( terrein != null ) )
		{
			System.out.println("Tegel::setRotatie : GEEN GEVOLG");
			return;
		}
		
		if(!draaibaar)
		{
			// TODO : exception
			System.out.println("Tegel::setRotatie : NIET TOEGESTAAN");
			return;
		}
		
		if( (rotatie != 90) && (rotatie != 180) && (rotatie != 270) && (rotatie != 0)  )
		{
			// TODO : exception
			System.out.println("Tegel::setRotatie : FOUTE GRADEN");
			return;
		}
		
		this.rotatie = rotatie;
		
		terrein = type.draaiTerrein(rotatie);
		gebiedBeheerder = new TegelGebiedBeheerder(this);
	}
	
	public boolean kanBuurAccepteren(Tegel buur, RICHTING richting)
	{
		// terreintypes in aangrenzende vakjes van de terreinmatrix checken en zien of ze overeenkomen
		
		// we maken gebruik van TegelGebiedBeheerder
		boolean output = this.gebiedBeheerder.controleerOvereenkomstigeZijden(buur.getGebiedBeheerder(), richting);
		
		// ook omliggende buren nog checken !!!!
		/*RICHTING[] pad1 = BuurPaden.getPad(richting, 1);
		RICHTING[] operaties1 = BuurPaden.getOperaties(richting, 1);
		RICHTING[] pad2 = BuurPaden.getPad(richting, 2);
		RICHTING[] operaties2 = BuurPaden.getOperaties(richting, 2);
		
		output = output && kanBuurAccepterenUitgebreid(buur, pad1, operaties1);
		output = output && kanBuurAccepterenUitgebreid(buur, pad2, operaties2);	*/	
		
		return output;
	}
		
		/*private boolean kanBuurAccepterenUitgebreid(Tegel buur, RICHTING[] pad, RICHTING[] operaties)
		{
			int teller = 0;
			Tegel tegel = this;
			boolean output = true;
			
			while( teller < pad.length )
			{
				tegel = tegel.getBuur( pad[teller] );
				if( tegel == null )
					return output;
				
				if( operaties[teller] != null )
					output = tegel.getGebiedBeheerder().controleerOvereenkomstigeZijden(buur.getGebiedBeheerder(), operaties[teller]);
				
				if(output == false)
					return output;
				
				teller++;
			}	
			
			return output;
		}*/
	
	public void setBuur( Tegel buur, RICHTING richting )
	{
		draaibaar = false;
		//setBuur(buur, richting, true);
		//System.out.println("SET BUUR : NOG AANPASSEN");
		
		buren[ richting.ordinal() ] = buur;
		gebiedBeheerder.setBuur(buur, richting);
		
		// moeten ons geen zorgen meer maken over andere buren. Spelbord regelt dit.
	}
	
	public void verwijderBuur(RICHTING richting)
	{
		buren[ richting.ordinal() ] = null;
		gebiedBeheerder.verwijderBuur(richting);
	}
	
	public Tegel getBuur(RICHTING richting)
	{
		return buren[ richting.ordinal() ];
	}
	
	/*
	 * Houdt geen rekening met eventuele bestaande pionnen
	 */
	public void plaatsPion(Punt positie, Pion pion)
	{
		boolean bestaat = ( positie.getX() < pionPosities.length ) && ( positie.getY() < pionPosities[0].length );
		
		if( bestaat )
			pionPosities[ positie.getX() ][ positie.getY() ] = pion;
		else
		{
			// TODO : exception
			
		}
	}
	
	public void verwijderPion(Punt positie)
	{
		boolean bestaat = ( positie.getX() < pionPosities.length ) && ( positie.getY() < pionPosities[0].length );
		
		if( bestaat )
			pionPosities[ positie.getX() ][ positie.getY() ] = null;
		else
		{
			// TODO : exception
			
		}
	}
	
	/*
	 * Geeft null terug als er geen pion aanwezig is op positie.
	 */
	public Pion getPion(Punt positie)
	{
		boolean bestaat = ( positie.getX() < pionPosities.length ) && ( positie.getY() < pionPosities[0].length );
		if( bestaat )
			return pionPosities[ positie.getX() ][ positie.getY() ];
		else
		{
			// TODO : exception
			return null;
		}
	}
	
	
	public Gebied getGebied(Terrein start)
	{
		return gebiedBeheerder.getGebied(start);
	}
	
	public int getID() 
	{
		return ID;
	}

	public void setID(int id) 
	{
		ID = id;
	}

	public int getRotatie() 
	{
		return rotatie;
	}

	public TegelType getType() 
	{
		return type;
	}
	
	public TerreinType getTerreinType( Punt locatie )
	{
		// TODO : controle op indices
		return terrein[ locatie.getX() ][ locatie.getY() ]; 
	}
	
	public TerreinBasis getTerrein(Punt locatie)
	{
		// TODO : controle op indices
		return new Terrein( this, new Punt(locatie) );
	}
	
	public int getTerreinBreedte()
	{
		return terrein[0].length;
	}
	
	public int getTerreinHoogte()
	{
		return terrein.length;
	}
	
	// TODO : deze moet hier weg !
	public TegelGebiedBeheerder getGebiedBeheerder()
	{
		return this.gebiedBeheerder;
	}

	public boolean isDraaibaar()
	{
		return draaibaar;
	}
	
	
	public void print()
	{
		System.out.println( "Tegel::Print voor tegel " + ID );
		
		for(RICHTING r: RICHTING.values())
		{
			Tegel buur = getBuur(r);
			if(buur == null)
				System.out.println(r + " = LEEG");
			else
				System.out.println(r + " = " + buur.getID());
				
		}
	}
	
	public String toXML()
	{
		String output = "\t\t<tegel>\n";
		
			output += "\t\t\t<id>" 				+ this.ID + 			"</id>\n";
			output += "\t\t\t<type>" 			+ this.type.getID() + 	"</type>\n";
			output += "\t\t\t<rotatie>" 		+ this.rotatie + 		"</rotatie>\n";
			
			
			// eerste beste buur meegeven ( = positie op het spelbord)
			for( RICHTING richting : RICHTING.values() )
			{
				if( buren[ richting.ordinal() ] != null )
				{
					// buur met lager ID nemen, anders kunnen we later in de problemen komen bij het
					// terug inladen.
					if( buren[ richting.ordinal() ].getID() < this.ID )
					{
						output += "\t\t\t<buur>\n";
							output += "\t\t\t\t<id>" 		+ this.buren[ richting.ordinal() ].getID() 	+ "</id>\n";
							output += "\t\t\t\t<richting>" 	+ richting.toString() 						+ "</richting>\n";
						output += "\t\t\t</buur>\n";
						break;
					}
				}
			}
				
			String pionnen = "";
			for(int i = 0; i < pionPosities.length; i++)
			{
				for(int j = 0; j < pionPosities[0].length; j++)
				{
					if( pionPosities[i][j] != null )
					{
						pionnen += pionPosities[i][j].toXML( new Punt(i,j) ); 
					}
				}
			} 
			
			if(pionnen != "")
				output += "\t\t\t<pionnen>\n" + pionnen + "\t\t\t</pionnen>\n";
				
		output += "\t\t</tegel>\n";
		
		return output;
	}
	
	/*
	 * Deze functie maakt de tegel aan met de gegeven type, id en rotatie.
	 * De tegel op het spelbord plaatsen (buren toekennen) en de pionnen eropzetten,
	 * moet gedaan worden via spelbord, niet in deze functie.
	 */
	public static Tegel fromXML(XmlNode input)
	{
		
		int id = 	  Integer.parseInt( input.getChild("id").getValue());
    	String type = 					input.getChild("type").getValue();
    	int rotatie = Integer.parseInt( input.getChild("rotatie").getValue());
		
		Tegel output = new Tegel( TegelTypeVerzameling.getInstantie().getType(type) );
		output.setID(id);
		output.setRotatie(rotatie);
		
		return output;
	}
	
}
