package ambiorix.spelbord.tegeltypes;

import ambiorix.spelbord.TegelType;
import ambiorix.spelbord.TerreinType;
import ambiorix.spelbord.TerreinTypeVerzameling;

public class TegelType_WGGWW extends TegelType {
	public TegelType_WGGWW() {
		super("TegelType_WGGWW");

		terrein = new TerreinType[5][5];
		terrein[0][0] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[0][1] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[0][2] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Weg");
		terrein[0][3] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[0][4] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");

		terrein[1][0] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[1][1] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[1][2] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Weg");
		terrein[1][3] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[1][4] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");

		terrein[2][0] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Weg");
		terrein[2][1] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Weg");
		terrein[2][2] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Weg");
		terrein[2][3] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[2][4] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");

		terrein[3][0] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[3][1] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[3][2] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[3][3] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[3][4] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");

		terrein[4][0] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[4][1] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[4][2] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[4][3] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
		terrein[4][4] = TerreinTypeVerzameling.getInstantie().getType(
				"TerreinType_Gras");
	}
}
