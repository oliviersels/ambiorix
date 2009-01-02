package ambiorix.ai;

import java.util.Vector;

import ambiorix.Spel;
import ambiorix.spelbord.*;
import ambiorix.spelers.*;
import ambiorix.ai.*;



public abstract class Ai
{
	protected Spelbord bord;
	protected Vector<Positie> positieLijst;
	protected Vector<Tegel> tegels;
	protected Vector<Pion> pionnen;
	
	public Ai(Spelbord b, Vector<Tegel> tegels, Vector<Pion> pionnen)
	{
		bord = b;
		this.tegels = tegels;
		this.pionnen = pionnen;
		positieLijst = new Vector<Positie>();
	}
	
	//---------------------------------------------------------------------------------
	
	public abstract Vector<Positie> MaakPosities(Spelbord bord);
	
	public abstract Vector<Antwoord> berekenZet();
	
	public abstract void berekenScore(BordPositie positie);
	
	public abstract double berekenKans(BordPositie positie);
}
