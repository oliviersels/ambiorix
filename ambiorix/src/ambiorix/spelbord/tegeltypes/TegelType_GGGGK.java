package ambiorix.spelbord.tegeltypes;

import ambiorix.spelbord.*;

public class TegelType_GGGGK extends TegelType
{

	public TegelType_GGGGK()
	{
		super("TegelType_GGGGK");
		
		terrein = new TerreinType[3][3];
		terrein[0][0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[0][1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[0][2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[1][0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[1][1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Klooster");
		terrein[1][2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[2][0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[2][1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[2][2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
	}

}
