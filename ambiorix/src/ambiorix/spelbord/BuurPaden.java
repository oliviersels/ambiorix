package ambiorix.spelbord;

import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Tegel.RICHTING;

// BOVEN
// LINKS, BOVEN, BOVEN, RECHTS
//        RECHTS        ONDER
// RECHTS BOVEN, BOVEN, LINKS
//        LINKS         ONDER

// RECHTS
// BOVEN, RECHTS, RECHTS, ONDER
//        ONDER           LINKS
// ONDER, RECHTS, RECHTS, BOVEN
//        BOVEN           LINKS

// ONDER
// LINKS, ONDER, ONDER, RECHTS
//        RECHTS        BOVEN
// RECHTS, ONDER, ONDER, LINKS
//         LINKS         BOVEN

// LINKS
// BOVEN, LINKS, LINKS, ONDER
//        ONDER         RECHTS
// ONDER, LINKS, LINKS, BOVEN
//        BOVEN        RECHTS

public class BuurPaden 
{
	public static Tegel.RICHTING[] getPad(Tegel.RICHTING richting, int nr)
	{
		RICHTING[] output = new RICHTING[4];
		
		if(richting == RICHTING.BOVEN)
		{
			if(nr == 1)
			{
				output[0] = RICHTING.LINKS; output[1] =  RICHTING.BOVEN; 
				output[2] = RICHTING.BOVEN; output[3] = RICHTING.RECHTS;
			}
			else if( nr == 2 )
			{

				output[0] = RICHTING.RECHTS; output[1] =  RICHTING.BOVEN; 
				output[2] = RICHTING.BOVEN; output[3] = RICHTING.LINKS;
			}
		}	
		else if(richting == RICHTING.RECHTS)
		{
			if(nr == 1)
			{
				output[0] = RICHTING.BOVEN; output[1] =  RICHTING.RECHTS; 
				output[2] = RICHTING.RECHTS; output[3] = RICHTING.ONDER;
			}
			else if( nr == 2 )
			{

				output[0] = RICHTING.ONDER; output[1] =  RICHTING.RECHTS; 
				output[2] = RICHTING.RECHTS; output[3] = RICHTING.BOVEN;
			}
		}	
		else if(richting == RICHTING.ONDER)
		{
			if(nr == 1)
			{
				output[0] = RICHTING.LINKS; output[1] =  RICHTING.ONDER; 
				output[2] = RICHTING.ONDER; output[3] = RICHTING.RECHTS;
			}
			else if( nr == 2 )
			{

				output[0] = RICHTING.RECHTS; output[1] =  RICHTING.ONDER; 
				output[2] = RICHTING.ONDER; output[3] = RICHTING.LINKS;
			}
		}	
		else if(richting == RICHTING.LINKS)
		{
			if(nr == 1)
			{
				output[0] = RICHTING.BOVEN; output[1] =  RICHTING.LINKS; 
				output[2] = RICHTING.LINKS; output[3] = RICHTING.ONDER;
			}
			else if( nr == 2 )
			{

				output[0] = RICHTING.ONDER; output[1] =  RICHTING.LINKS; 
				output[2] = RICHTING.LINKS; output[3] = RICHTING.BOVEN;
			}
		}
		
		
		return output;
	}
	
	public static Tegel.RICHTING[] getOperaties(Tegel.RICHTING richting, int nr)
	{
		RICHTING[] output = new RICHTING[4];
		
		if( richting == Tegel.RICHTING.BOVEN )
		{
			if(nr == 1)
			{
				output[0] = null; output[1] =  RICHTING.RECHTS; output[2] = null; output[3] = RICHTING.ONDER;
			}
			else if( nr == 2 )
			{
				output[0] = null; output[1] =  RICHTING.LINKS; output[2] = null; output[3] = RICHTING.ONDER;
			}
		}
		else if( richting == Tegel.RICHTING.RECHTS )
		{
			if(nr == 1)
			{
				output[0] = null; output[1] =  RICHTING.ONDER; output[2] = null; output[3] = RICHTING.LINKS;
			}
			else if( nr == 2 )
			{
				output[0] = null; output[1] =  RICHTING.BOVEN; output[2] = null; output[3] = RICHTING.LINKS;
			}
		}
		else if( richting == Tegel.RICHTING.ONDER )
		{
			if(nr == 1)
			{
				output[0] = null; output[1] =  RICHTING.RECHTS; output[2] = null; output[3] = RICHTING.BOVEN;
			}
			else if( nr == 2 )
			{
				output[0] = null; output[1] =  RICHTING.LINKS; output[2] = null; output[3] = RICHTING.BOVEN;
			}
		}
		else if( richting == Tegel.RICHTING.LINKS )
		{
			if(nr == 1)
			{
				output[0] = null; output[1] =  RICHTING.ONDER; output[2] = null; output[3] = RICHTING.RECHTS;
			}
			else if( nr == 2 )
			{
				output[0] = null; output[1] =  RICHTING.BOVEN; output[2] = null; output[3] = RICHTING.RECHTS;
			}
		}				
		
		return output;
	}
}
