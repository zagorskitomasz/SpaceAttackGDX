package spaceattack.game;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.GameStageFactory;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;

public enum GameFactory {
    INSTANCE;

    public IGame create() {

        IUtils utils = Factories.getUtilsFactory().create();

        Game game = new Game(false);

        game.setExtUtils(utils);
        game.setStageBuilder(GameStageFactory.INSTANCE);
        game.setFrameController(new FrameController(utils, Consts.Metagame.FPS));

        return game;
    }
}
