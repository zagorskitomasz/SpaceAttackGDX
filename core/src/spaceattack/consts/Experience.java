package spaceattack.consts;

import java.util.Arrays;

public class Experience
{
	public static long[] nextLevelReq = populateExperience();
	
	private static long[] populateExperience()
	{
		nextLevelReq = new long[Consts.Gameplay.MAX_EXP_LEVEL];
		
		for(int i = 0; i < nextLevelReq.length; i++)
		{
			nextLevelReq[i] = expForLevel(i);
		}
		System.out.println(Arrays.toString(nextLevelReq));
		return nextLevelReq;
	}

	private static long expForLevel(int level) 
	{
		if(level == 0)
			return 0;
		
		if(level == 1)
			return Consts.Gameplay.LEVEL_1_EXP;
		
		return Math.round(nextLevelReq[level - 1] + nextLevelReq[level - 1] * Consts.Gameplay.EXP_FACTOR);
	}
}
