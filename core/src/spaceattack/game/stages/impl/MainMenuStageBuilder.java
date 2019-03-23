package spaceattack.game.stages.impl;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;

public class MainMenuStageBuilder implements IStageBuilder
{
	@Override
	public IGameStage build(GameProgress progress)
	{
		MainMenuStage stage = new MainMenuStage();

		stage.setStage(Factories.getStageFactory().create());
		stage.setGameSaver(GameSaverFactory.INSTANCE.create());
		stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
		stage.setGameProgress(GameLoaderFactory.INSTANCE.create().load());

		Factories.getUtilsFactory().create().setInputProcessor(stage.getStage());

		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.MENU_BACKGROUND.getTexture(), 0, 0);
		StaticImage logo = StaticImageFactory.INSTANCE.create(Textures.LOGO.getTexture(), 0, Sizes.GAME_HEIGHT * 0.03f);

		IButton newGameButton = MenuButtonsBuilder.INSTANCE.newGameButton(stage);
		IButton continueGameButton = MenuButtonsBuilder.INSTANCE.continueGameButton(stage);
		IButton exitGameButton = MenuButtonsBuilder.INSTANCE.exitGameButton(stage);

		stage.addButtonsEnabledPredicate(continueGameButton, stage::isContinueButtonEnabled);

		stage.addBackground(background);
		stage.addActorBeforeGUI(logo);
		stage.addActor(newGameButton);
		stage.addActor(continueGameButton);
		stage.addActor(exitGameButton);

		stage.updateControls();
		
		MusicPlayer.INSTANCE.playMenu();

		return stage;
	}
}
