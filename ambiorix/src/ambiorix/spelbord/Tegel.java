package ambiorix.spelbord;

import ambiorix.util.Punt;

public class Tegel 
{
	private int ID = -1;
	private int rotatie = 0;
	
	public enum RICHTING
	{
		BOVEN,
		RECHTS,
		ONDER,
		LINKS
	}
	
	private TegelType type = null;
	
	// positieindexen komen overeen met indexen op type.terrein
	private Pion[][] pionPosities = null; 
	private TerreinType[][] terrein = null;
	
	private Tegel[] buren = new Tegel[4];
	
	// PRIVATE ZETTEN !!!!
	public TegelGebiedBeheerder gebiedBeheerder = null;
	
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

	public void setRotatie(int rotatie) 
	{
		this.rotatie = rotatie;
		
		terrein = type.draaiTerrein(rotatie);
		gebiedBeheerder = new TegelGebiedBeheerder(this);
	}
	
	public void setBuur( Tegel buur, RICHTING richting )
	{
		setBuur(buur, richting, true);
	}
	
	// TODO : wat als er al een buur staat daar ?
	protected void setBuur(Tegel buur, RICHTING richting, boolean synchronizeer)
	{
		//System.out.println("setBuur : " + this.ID + " zet op " + richting + " => " + buur.getID() + ", " + synchronizeer );
		
		if( getBuur( richting ) == null )
		{
			buren[ richting.ordinal() ] = buur;
			
			// de buur moet zelf ook deze tegel als zijn buur zetten !
			if( richting == RICHTING.BOVEN )
				buur.setBuur(this, RICHTING.ONDER, false);
			if( richting == RICHTING.ONDER )
				buur.setBuur(this, RICHTING.BOVEN, false);	
			if( richting == RICHTING.RECHTS )
				buur.setBuur(this, RICHTING.LINKS, false);		
			if( richting == RICHTING.LINKS )
				buur.setBuur(this, RICHTING.RECHTS, false);	
			
		} 
		else
		{
			// TODO ; mogen we dit hier gewoon zo laten? 
			// Momenteel gaat hij altijd hierin komen, door bovenstaande code.
			// ik zou zeggen : gewoon niks doen en oude behouden.
			// mss een functie "vervangtegel" ?
			
			//System.out.println("Tegel::SetBuur : TEGEL IS AL GEZET");
			//System.out.println("setBuur : " + this.ID + " zet op " + richting + " => " + buur.getID() + ", " + synchronizeer );
			return;
		}
		
		gebiedBeheerder.setBuur(buur, richting);
		
		if(!synchronizeer)
			return;
		

		// ook zorgen dat omliggende tegels deze tegel registreren als buur!
		// hangt totaal van de richting af!
		
		// algoritme is gebaseerd op een reeks tegels die je moet doorlopen om alle aangrenzende
		// tegels van de nieuw geplaatste te bereiken. Omdat we maar 4 buren bijhouden, slaan we
		// telkens 1 over om setBuur op aan te roepen.
		// Pad zegt dus welk pad er moet gevolgd worden, operaties bij welke tegel in dat pad de buur
		// moet aangepast worden. (4 elementen in pad, 2 in operaties)
		
		
		// BOVEN
		// LINKS, BOVEN, BOVEN, RECHTS
		//        RECHTS        ONDER
		// RECHTS BOVEN, BOVEN, LINKS
		//        LINKS         ONDER
		
		if(richting == RICHTING.BOVEN)
		{
			RICHTING[] pad = 		{RICHTING.LINKS, RICHTING.BOVEN, RICHTING.BOVEN, RICHTING.RECHTS};
			RICHTING[] operaties =  {null          , RICHTING.RECHTS, null		   , RICHTING.ONDER};
			
			synchronizeerBuren(buur, pad, operaties);
			
			RICHTING[] pad2 = 		 {RICHTING.RECHTS, RICHTING.BOVEN, RICHTING.BOVEN, RICHTING.LINKS};
			RICHTING[] operaties2 =  {null           , RICHTING.LINKS, null		     , RICHTING.ONDER};
			
			synchronizeerBuren(buur, pad2, operaties2);
		}
		
		// RECHTS
		// BOVEN, RECHTS, RECHTS, ONDER
		//        ONDER           LINKS
		// ONDER, RECHTS, RECHTS, BOVEN
		//        BOVEN           LINKS
		
		if(richting == RICHTING.RECHTS)
		{
			RICHTING[] pad = 		{RICHTING.BOVEN, RICHTING.RECHTS, RICHTING.RECHTS, RICHTING.ONDER};
			RICHTING[] operaties =  {null          , RICHTING.ONDER, null		     , RICHTING.LINKS};
			
			synchronizeerBuren(buur, pad, operaties);
			
			RICHTING[] pad2 = 		 {RICHTING.ONDER, RICHTING.RECHTS, RICHTING.RECHTS, RICHTING.BOVEN};
			RICHTING[] operaties2 =  {null          , RICHTING.BOVEN, null		      , RICHTING.LINKS};
			
			synchronizeerBuren(buur, pad2, operaties2);
		}
		
		// ONDER
		// LINKS, ONDER, ONDER, RECHTS
		//        RECHTS        BOVEN
		// RECHTS, ONDER, ONDER, LINKS
		//         LINKS         BOVEN

		if(richting == RICHTING.ONDER)
		{
			RICHTING[] pad = 		{RICHTING.LINKS, RICHTING.ONDER, RICHTING.ONDER, RICHTING.RECHTS};
			RICHTING[] operaties =  {null          , RICHTING.RECHTS, null		   , RICHTING.BOVEN};
			
			synchronizeerBuren(buur, pad, operaties);
			
			RICHTING[] pad2 = 		 {RICHTING.RECHTS, RICHTING.ONDER, RICHTING.ONDER, RICHTING.LINKS};
			RICHTING[] operaties2 =  {null           , RICHTING.LINKS, null		     , RICHTING.BOVEN};
			
			synchronizeerBuren(buur, pad2, operaties2);
		}
		
		// LINKS
		// BOVEN, LINKS, LINKS, ONDER
		//        ONDER         RECHTS
		// ONDER, LINKS, LINKS, BOVEN
		//        BOVEN        RECHTS

		if(richting == RICHTING.LINKS)
		{
			RICHTING[] pad = 		{RICHTING.BOVEN, RICHTING.LINKS, RICHTING.LINKS, RICHTING.ONDER};
			RICHTING[] operaties =  {null          , RICHTING.ONDER, null		   , RICHTING.RECHTS};

			synchronizeerBuren(buur, pad, operaties);
			
			RICHTING[] pad2 = 		 {RICHTING.ONDER, RICHTING.LINKS, RICHTING.LINKS, RICHTING.BOVEN};
			RICHTING[] operaties2 =  {null           , RICHTING.BOVEN, null		    , RICHTING.RECHTS};
			
			synchronizeerBuren(buur, pad2, operaties2);
		}		
		
		// originele code
		// geeft een goed idee hoe de synchronizeerBuren-functie werkt
		// louter ter DOCUMENTATIE bedoeld!!!
		/*if( richting == RICHTING.BOVEN )
		{
			// langs links naar boven gaan en kijken of daar buren zitten.
			Tegel links = getBuur( RICHTING.LINKS );
			
			if( links != null )
			{
				Tegel linksboven = links.getBuur(RICHTING.BOVEN);
				if( linksboven != null )
				{
					linksboven.setBuur(buur, RICHTING.RECHTS);
					
					Tegel dubbellinksboven = linksboven.getBuur(RICHTING.BOVEN);
					if( dubbellinksboven != null )
					{
						Tegel dubbelboven = dubbellinksboven.getBuur(RICHTING.RECHTS);
						if(dubbelboven != null)
							dubbelboven.setBuur(buur, RICHTING.ONDER);
					}
				}
			}
			
		}*/
	}
		// hulpfunctie voor setBuur()
		private void synchronizeerBuren(Tegel buur, RICHTING[] pad, RICHTING[] operaties)
		{
			int teller = 0;
			Tegel tegel = this;
			
			while( teller < pad.length )
			{
				tegel = tegel.getBuur( pad[teller] );
				if( tegel == null )
					return;
				
				if( operaties[teller] != null )
					tegel.setBuur(buur, operaties[teller], false);
				
				teller++;
			}
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
	
	public Tegel getBuur(RICHTING richting)
	{
		return buren[ richting.ordinal() ];
	}
	
	/*
	 * Tegel werkt met 2D coordinaten naar de buitenwereld.
	 * Intern moet zo'n 2D punt eerst omgezet worden naar arraycoordinaten,
	 * zodat pionPosities dezelfde logica gebruikt als TerreinType.terrein
	 */
	public void PlaatsPion(Punt positie, Pion pion)
	{
		boolean bestaat = ( positie.getX() < pionPosities.length ) && ( positie.getY() < pionPosities[0].length );
		
		if( bestaat )
			pionPosities[ positie.getX() ][ positie.getY() ] = pion;
		else
		{
			// TODO : exception
			
		}
	}
	
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

	public TerreinType[][] getTerrein() 
	{
		return terrein;
	}

	
}
