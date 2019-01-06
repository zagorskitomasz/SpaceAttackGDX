package spaceattack.game.utils;

import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;

public class NumbersUtils
{
	public static String toRoman(int number)
	{
		switch (number)
		{
			case 1:
				return "I";
			case 2:
				return "II";
			case 3:
				return "III";
			case 4:
				return "IV";
			case 5:
				return "V";
			case 6:
				return "VI";
			case 7:
				return "VII";
			case 8:
				return "VIII";
			case 9:
				return "IX";
			case 10:
				return "X";
		}
		return "";
	}

	public static double distance(IVector object1,IVector object2)
	{
		IUtils utils = Factories.getUtilsFactory().create();

		return utils.sqrt(
				utils.pow((object2.getX() - object1.getX()), 2) + utils.pow((object2.getY() - object1.getY()), 2));
	}
}
