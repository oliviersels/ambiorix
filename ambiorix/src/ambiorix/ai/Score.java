package ambiorix.ai;

import ambiorix.spelers.Speler;


/**
 * Bevat een speler en een puntenwaarde die aan de speler toegekentd zou worden, moest de zet uitgevoerd worden.
 * 
 * @author 1ntegr0
 *
 */
public class Score
{
	private Speler speler;
	private int score;
	
	public Score(Speler speler, int score)
	{
		this.speler = speler;
		this.score = score;
	}
	
	public Score(String s)
	{
		for( int i = 0; i < s.length(); ++i )
		{
			if( s.charAt(i) == '-' )
			{
				score = Integer.parseInt( s.substring( 0, i-1 ) );
				speler.setNaam( s.substring( i+1, s.length() ) );
				
				i = s.length();
			}
		}
	}
	
	//---------------------------------------------------------------------------------
	
	public Speler getSpeler()
	{
		return speler;
	}
	
	public int getScore()
	{
		return score;
	}
	
	//---------------------------------------------------------------------------------
	
	public void setSpeler(Speler speler)
	{
		this.speler = speler;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	//---------------------------------------------------------------------------------
	
	public String toString()
	{
		return ( "" + score + "-" + speler.getNaam() );
	}
}
