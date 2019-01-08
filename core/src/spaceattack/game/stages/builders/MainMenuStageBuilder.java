package spaceattack.game.stages.builders;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.UIStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public enum MainMenuStageBuilder implements IStageBuilder
{
	INSTANCE;

	@Override
	public IGameStage build(GameProgress progress)
	{
		IGameStage stage = new UIStage();

		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.MENU_BACKGROUND.getTexture(), 0, 0);
		StaticImage logo = StaticImageFactory.INSTANCE.create(Textures.LOGO.getTexture(), 0, Sizes.GAME_HEIGHT * 0.03f);

		IButton newGameButton = MenuButtonsBuilder.INSTANCE.newGameButton(stage);
		IButton continueGameButton = MenuButtonsBuilder.INSTANCE.continueGameButton();
		IButton exitGameButton = MenuButtonsBuilder.INSTANCE.exitGameButton();

		stage.addActor(background);
		stage.addActor(logo);
		stage.addActor(newGameButton);
		stage.addActor(continueGameButton);
		stage.addActor(exitGameButton);

		stage.setGameProgress(GameLoaderFactory.INSTANCE.create().load());
		Factories.getUtilsFactory().create().setInputProcessor(stage.getStage());

		return stage;
	}
}
