package spaceattack.ext.animation;

import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.IAnimationFactory;

public enum ExtAnimationFactory implements IAnimationFactory
{
	INSTANCE;

	@Override
	public IAnimation create(String path,float fps)
	{
		return new GdxAnimation(path, false, fps);
	}

	@Override
	public IAnimation createLooping(String path,float fps)
	{
		return new GdxAnimation(path, true, fps);
	}
}
