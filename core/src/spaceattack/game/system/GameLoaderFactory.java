package spaceattack.game.system;

import spaceattack.game.factories.Factories;

public enum GameLoaderFactory {
    INSTANCE;

    public GameLoader create() {

        GameLoader loader = new GameLoader();
        loader.setUtils(Factories.getUtilsFactory().create());

        return loader;
    }
}
