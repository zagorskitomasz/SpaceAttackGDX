package spaceattack.game.system.graphics;

import spaceattack.game.factories.Factories;

public enum StaticImageFactory {
    INSTANCE;

    public StaticImage create(ITexture texture, float x, float y) {

        StaticImage image = new StaticImage(texture, x, y);
        image.setActor(Factories.getActorFactory().create(image));

        return image;
    }
}
