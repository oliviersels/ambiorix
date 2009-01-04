package ambiorix.uitbreidingen.implementaties;

import java.util.Vector;

import ambiorix.uitbreidingen.UitbreidingImplementatie;

public class Uitbreiding_Basis extends UitbreidingImplementatie 
{
	public Uitbreiding_Basis()
	{
		uitbreidingPad = "uitbreidingen/Basis/";
	}
	
	public void bereidVoor(Vector<String> andereUitbreidingen)
	{
		System.out.println("Basisuitbreiding is ingeladen");
		
		super.bereidVoor(andereUitbreidingen);
	}
	
	public String getEersteActie()
	{
		return "StartSpel";
	}
}
