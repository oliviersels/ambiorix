package ambiorix.uitbreidingen.implementaties;

import java.util.Vector;

import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.uitbreidingen.UitbreidingImplementatie;

public class Uitbreiding_Lava extends UitbreidingImplementatie 
{

	public Uitbreiding_Lava()
	{
		uitbreidingPad = "uitbreidingen/Lava/";
	}
	
	public void bereidVoor(Vector<String> andereUitbreidingen)
	{
		System.out.println("Lavauitbreiding is ingeladen");
		
		super.bereidVoor(andereUitbreidingen);
	}	
	

	public ScoreBerekenaar getScoreBerekenaar()
	{
		// rekenen op scoreberekenaar van de onderliggende uitbreidingen
		//System.out.println("We zitten in de getScoreBerekenaar van Lava");
		return super.getScoreBerekenaar();
	}
	
	public String getEersteActie()
	{
		return "StartSpel";
	}
}
