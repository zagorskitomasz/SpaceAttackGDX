package spaceattack.game.system.graphics;

public interface IAnimationFactory {

    IAnimation create(Animations animation, float fps);

    IAnimation createLooping(Animations animation, float fps);
}
