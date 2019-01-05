package spaceattack.ext.gdx.utils;

import spaceattack.game.utils.ExtUtils;

public enum GdxUtilsFactory
{
	INSTANCE;

	public ExtUtils create()
	{
		return GdxUtils.INSTANCE;
	}
}
