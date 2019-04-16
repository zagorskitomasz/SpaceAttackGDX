package spaceattack.consts;

public enum Experience
{
	INSTANCE;
	
	private long[] experience;
	
	Experience()
	{
		experience = new long[Consts.Gameplay.MAX_EXP_LEVEL];
		for(int i = 0; i < experience.length; i++)
		{
			experience[i] =  Math.round(144373000000l + (769.2301 - 144373000000l)/(1 + Math.pow((i/68184.15),1.80433)));
		}
		experience[0] = 0;
		experience[1] = 0;
	}

	public long expForLevel(int level) 
	{
		return experience[level];
	}
}
