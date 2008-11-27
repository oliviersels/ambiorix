package ambiorix.spelbord;

public class BordPositie 
{
	private Tegel buur = null;
	private Tegel.RICHTING richting = null;
	
	public BordPositie( Tegel buur, Tegel.RICHTING richting )
	{
		this.buur = buur;
		this.richting = richting;
	}

	public Tegel getBuur() 
	{
		return buur;
	}

	public void setBuur(Tegel buur) 
	{
		this.buur = buur;
	}

	public Tegel.RICHTING getRichting() 
	{
		return richting;
	}

	public void setRichting(Tegel.RICHTING richting) 
	{
		this.richting = richting;
	}
	
	
}
