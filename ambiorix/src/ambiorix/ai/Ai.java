package ambiorix.ai;

import java.util.Vector;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;



public abstract class Ai
{
	protected Spelbord bord;
	protected Vector<Positie> positieLijst;
	protected Vector<Tegel> tegels;
	protected Vector<Pion> pionnen;
	
	public Ai()
	{
		
	}
	
	public void initialiseer(Spelbord b, Vector<Tegel> tegels, Vector<Pion> pionnen, Speler speler)
	{
		bord = b;
		this.tegels = tegels;
		this.pionnen = pionnen;
		positieLijst = new Vector<Positie>();
	}
	
	//---------------------------------------------------------------------------------
	
	public abstract Vector<Positie> maakPosities(Spelbord bord);
	
	public abstract Vector<Antwoord> berekenZet();
	
	public abstract void berekenScore(BordPositie positie);
	
	public abstract double berekenKans(BordPositie positie);
	
	public abstract void reset();
}
