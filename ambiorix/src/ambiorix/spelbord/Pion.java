package ambiorix.spelbord;

import ambiorix.spelers.Speler;
import ambiorix.util.Punt;
import ambiorix.xml.XmlNode;

public class Pion implements PionBasis
{
	private int ID = -1;
	private PionType type = null;
	
	private Speler speler = null;
	
	public Pion(int ID, PionType type, Speler s)
	{
		this.ID = ID;
		this.type = type;
		speler = s;
	}

	public int getID() 
	{
		return ID;
	}

	public PionType getType() 
	{
		return type;
	}

	public Speler getSpeler() 
	{
		return speler;
	}

	public void setSpeler(Speler speler) 
	{
		this.speler = speler;
	}
	
	public String toXML(Punt locatie)
	{
		String output = "\t\t\t\t<pion>\n";
		
			output += "\t\t\t\t\t<id>" 			+ this.ID + 				"</id>\n";
			output += "\t\t\t\t\t<type>" 		+ this.type.getID() + 		"</type>\n";
			output += "\t\t\t\t\t<locatie>"   	+ locatie.toString()+       "</locatie>\n";
			
			/*if(speler != null)
				output += "\t\t\t\t\t<speler>" 	+ this.speler.getNaam() + 	"</speler>\n";
			else
				output += "\t\t\t\t\t<speler>" 	+ "1" + 	"</speler>\n";*/
				
		
		output += "\t\t\t\t</pion>\n";
		
		return output;
	}
	
	public static Pion fromXML( XmlNode input )
	{
		// TODO : speler zien vast te krijgen !!! of mss speler gewoon van pion weglaten ? interesting...
		int id = Integer.parseInt(input.getChild("id").getValue());
		String type = input.getChild("type").getValue();
		
		
		return new Pion(id, PionTypeVerzameling.getInstantie().getType(type), null); // FIXME: Oli fixt maar robin doet de rest!
	}
}
