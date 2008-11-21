package ambiorix.spelbord.tegeltypes;

import ambiorix.spelbord.TegelType;
import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.TerreinTypeVerzameling;

public class TegelType_WGGWW extends TegelType
{
	public TegelType_WGGWW()
	{
		super("TegelType_WGGWW");
		
		terrein = new TerreinType[9];
		terrein[0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg");
		terrein[2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		
		terrein[3] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg");
		terrein[4] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg");
		terrein[5] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		
		terrein[6] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[7] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[8] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
	}
}
