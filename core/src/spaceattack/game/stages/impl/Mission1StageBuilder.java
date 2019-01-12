package spaceattack.game.stages.impl;

import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

import spaceattack.game.GameProgress;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.MissionInputHandler;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public enum Mission1StageBuilder implements IStageBuilder
{
	INSTANCE;

	@Override
	public IGameStage build(GameProgress progress)
	{
		GameplayStage stage = new GameplayStage();

		GameProgress gameProgress = GameLoaderFactory.INSTANCE.create().load();
		gameProgress.registerObserver(stage);

		stage.setStage(Factories.getStageFactory().create());
		stage.setGameSaver(GameSaverFactory.INSTANCE.create());
		stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
		stage.setGameProgress(gameProgress);

		// TODO missiles launcher, weapon controller, pools, bars

		IShip playersShip = null;
		FireButton primaryFireButton = null;
		FireButton secondaryFireButton = null;

		MissionInputHandler processor = new MissionInputHandler();
		processor.registerFireButton(primaryFireButton);
		processor.registerFireButton(secondaryFireButton);
		processor.registerShip(playersShip);
		stage.setInputProcessor(processor);
		Factories.getUtilsFactory().create().setInputProcessor(processor);

		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M1_BACKGROUND.getTexture(), 0, 0);

		stage.addActor(background);

		return stage;
	}
}
