package spaceattack.game.system.sound;

public interface ISound {

    long play();

    void stop();

    long loop();

    void stop(long instanceId);
}
