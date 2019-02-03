package spaceattack.game.animations;

import spaceattack.game.system.graphics.ITexture;

public interface IAnimation
{
	public ITexture getKeyFrame(float elapsed);

	public boolean isCompleted();

}
