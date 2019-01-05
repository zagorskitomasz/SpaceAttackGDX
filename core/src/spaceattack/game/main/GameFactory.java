package spaceattack.game.main;

import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.system.GameLoader;

import spaceattack.ext.gdx.utils.GdxUtilsFactory;
import spaceattack.game.stages.StageBuilder;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.ExtUtils;

public enum GameFactory
{
	INSTANCE;

	public SpaceAttackGame create()
	{
		ExtUtils extUtils = GdxUtilsFactory.INSTANCE.create();

		Game game = new Game(true);

		game.setExtUtils(extUtils);
		game.setGameLoader(GameLoader.INSTANCE);
		game.setStageBuilder(StageBuilder.INSTANCE);
		game.setFrameController(new FrameController(extUtils, Consts.Metagame.FPS));

		return game;
	}
}
