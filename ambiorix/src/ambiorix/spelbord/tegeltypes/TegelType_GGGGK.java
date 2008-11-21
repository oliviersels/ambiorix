package ambiorix.spelbord.tegeltypes;

import ambiorix.spelbord.*;
import ambiorix.spelbord.terreintypes.*;

public class TegelType_GGGGK extends TegelType
{

	public TegelType_GGGGK()
	{
		super("TegelType_GGGGK");
		
		terrein = new TerreinType[9];
		terrein[0] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[1] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[2] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[3] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[4] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Klooster");
		terrein[5] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[6] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[7] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
		terrein[8] = TerreinTypeVerzameling.getInstantie().getType("TerreinType_Gras");
	}

}
