package spaceattack.game.system.graphics;

public interface IAnimationFactory
{
	public IAnimation create(String path);

	public IAnimation createLooping(String path);
}
