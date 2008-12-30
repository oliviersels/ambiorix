package ambiorix.spelbord;

import ambiorix.util.Type;

public class TegelType extends Type
{
	protected TerreinType[][] terrein = null;
	
	public TegelType(String ID)
	{
		this.ID = ID;
	}
	
	public TerreinType[][] getTerrein()
	{
		return terrein;
	}
	
	
	/* Roteert het Terrein met x maal 90 graden
	*  en geeft een kopie terug. De internet terrein wordt dus NIET vervangen !!!!!
	*  (is ook niet de bedoeling, want dit object doet dienst als een FLYWEIGHT en wordt dus gedeeld door verschillende).
	*/ 
	public TerreinType[][] draaiTerrein(int rotatie)
	{
		if(rotatie == 90 )
			return draaiNegentig( terrein );
		if(rotatie == 180)
			return draaiNegentig( draaiNegentig(terrein) );
		if( rotatie == 270 )
			return draaiNegentig( draaiNegentig( draaiNegentig(terrein) ) );
		
		if( rotatie == 0 ) // kopie teruggeven
		{
			TerreinType[][] output = new TerreinType[ terrein.length ][ terrein[0].length ];
			
			for( int i = 0; i < terrein.length; i++)
				for( int j = 0; j < terrein[0].length; j++)
					output[i][j] = terrein[i][j];
			
			return output;
		}
		
		// TODO : exception
		return null;
	}
	
		private TerreinType[][] draaiNegentig( TerreinType[][] terrein )
		{
			// transponeren van een matrix geeft al een min of meer ok resultaat
			// MAAR de kolommen staan nog in verkeerde volgorde. Dus eerst transponeren
			// dan de kolommen ten opzichte van "de middenste kolom" spiegelen.
			
			TerreinType[][] output = new TerreinType[ terrein[0].length ][ terrein.length ];
			
			// transponeren
			for( int i = 0; i < terrein.length; i++)
				for( int j = 0; j < terrein[0].length; j++)
					output[j][i] = terrein[i][j];
			
			
			int teller = 0;
			int midden = terrein.length / 2;
			TerreinType hulp = null;
			
			while( teller <= midden )
			{
				// ganse kolom afgaan en waardes kopiëren naar gespiegelde kolom "aan de andere kant"
				for( int t = 0; t < terrein.length; t++)
				{
					hulp = output[ t ][ teller ];
					output[ t ][ teller ] = output[ t ][ output[0].length - teller - 1 ];
					output[ t ][ output[0].length - teller - 1 ] = hulp;
				}
				
				teller++;
			}
			
			return output;		
		}
	
	// TODO : moet er uit
	public static void print(TerreinType[][] terrein)
	{
		for(int i = 0; i < terrein.length; i++)
			for(int j = 0; j < terrein.length; j++)
			{
				System.out.print( terrein[i][j].getID() + "     " );
				
				if( j == terrein.length - 1 )
					System.out.println();
			}	
	}
	
}
