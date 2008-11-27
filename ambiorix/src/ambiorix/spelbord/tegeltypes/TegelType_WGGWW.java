package ambiorix.spelbord.tegeltypes;

import ambiorix.spelbord.TegelType;
import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.TerreinTypeVerzameling;

public class TegelType_WGGWW extends TegelType
{
	public TegelType_WGGWW()
	{
		super("TegelType_WGGWW");
		
		terrein = new TerreinType[3][3];
		terrein[0][0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[0][1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg");
		terrein[0][2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		
		terrein[1][0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg");
		terrein[1][1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Weg");
		terrein[1][2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		
		terrein[2][0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[2][1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[2][2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
	}
}
