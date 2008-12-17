package ambiorix.spelbord;

import java.util.Vector;

import org.w3c.dom.Node;

import ambiorix.util.Punt;

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
		String output = "<tegel>";
		
			output += "<id>" 		+ this.ID + 			"</id>";
			output += "<type>" 		+ this.type.getID() + 	"</type>";
			output += "<rotatie>" 	+ this.rotatie + 		"</rotatie>";
			
			String burenOut = "";
			
			// eerste beste buur meegeven ( = positie op het spelbord)
			for( RICHTING richting : RICHTING.values() )
			{
				if( buren[ richting.ordinal() ] != null )
				{
					burenOut += "<buur>";
					burenOut += "<id>" 		+ this.buren[ richting.ordinal() ].getID() 	+ "</id>";
					burenOut += "<richting>" 	+ richting.toString() 						+ "</richting>";
					burenOut += "</buur>";
					break;
				}
			}
			
			if(burenOut != "")
				output += "<buren>" + burenOut + "</buren>";
				
			String pionnen = "";
			for(int i = 0; i < pionPosities.length; i++)
			{
				for(int j = 0; j < pionPosities[0].length; j++)
				{
					if( pionPosities[i][j] != null )
					{
						pionnen += pionPosities[i][j].toXML(); 
					}
				}
			} 
			
			if(pionnen != "")
				output += "<pionnen>" + pionnen + "</pionnen>";
				
		output += "</tegel>";
		
		return output;
	}
	
	public static Tegel fromXML(Node input)
	{
		Tegel output = new Tegel(null);
		
		
		
		return output;
	}
	
}
