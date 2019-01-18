package spaceattack.ext.sound;

import spaceattack.game.system.sound.ISound;
import spaceattack.game.system.sound.ISoundFactory;

public enum ExtSoundFactory implements ISoundFactory
{
	INSTANCE;

	@Override
	public ISound create(String path)
	{
		return new GdxSound(path);
	}
}
