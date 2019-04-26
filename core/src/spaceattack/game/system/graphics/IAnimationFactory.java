package spaceattack.game.system.graphics;

public interface IAnimationFactory {

    public IAnimation create(String path, float fps);

    public IAnimation createLooping(String path, float fps);
}
