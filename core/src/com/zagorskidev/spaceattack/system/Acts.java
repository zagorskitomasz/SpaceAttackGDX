package com.zagorskidev.spaceattack.system;

import com.zagorskidev.spaceattack.graphics.Textures;

public enum Acts
{
	I(Textures.ACT_1_LOGO),II(Textures.ACT_2_LOGO),III(Textures.ACT_3_LOGO);

	private Textures logo;

	Acts(Textures texture)
	{
		this.logo = texture;
	}

	public Textures getLogo()
	{
		return logo;
	}

	public static Acts get(int actNumber)
	{
		for (Acts act : values())
		{
			if (act.name().equals(NumbersUtils.toRoman(actNumber)))
				return act;
		}
		return null;
	}
}
