package ambiorix.uitbreidingen;

import java.util.Comparator;
import java.util.Vector;

import ambiorix.ai.Ai;
import ambiorix.spelbord.ScoreBerekenaar;

public interface UitbreidingInterface 
{
	public void bereidVoor(Vector<String> andereUitbreidingen);
	public String getEersteActie();
	public ScoreBerekenaar getScoreBerekenaar();
	public Ai getAi();
}
