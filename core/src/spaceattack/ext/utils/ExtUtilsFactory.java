package spaceattack.ext.utils;

import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.IUtilsFactory;

public enum ExtUtilsFactory implements IUtilsFactory
{
	INSTANCE;

	@Override
	public IUtils create()
	{
		return GdxUtils.INSTANCE;
	}
}
