package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
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

public enum MissionsStageBuilder implements IStageBuilder
{
	INSTANCE;

	@Override
	public IGameStage build(GameProgress progress)
	{
		MissionsStage stage = new MissionsStage();

		stage.setStage(Factories.getStageFactory().create());
		stage.setGameSaver(GameSaverFactory.INSTANCE.create());
		stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
		stage.setGameProgress(GameLoaderFactory.INSTANCE.create().load());

		Factories.getUtilsFactory().create().setInputProcessor(stage.getStage());

		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.MENU_BACKGROUND.getTexture(), 0, 0);
		StaticImage logo = StaticImageFactory.INSTANCE.create(Textures.LOGO.getTexture(), 0, Sizes.GAME_HEIGHT * 0.03f);
		StaticImage actLogo = StaticImageFactory.INSTANCE.create(Textures.ACT_1_LOGO.getTexture(),
				Sizes.GAME_WIDTH * 0.25f, Sizes.GAME_HEIGHT * 0.3f);

		IButton backToMenuButton = MenuButtonsBuilder.INSTANCE.backToMenuButton(stage);
		IButton previouwActButton = MenuButtonsBuilder.INSTANCE.previousActButton(stage);
		IButton nextActButton = MenuButtonsBuilder.INSTANCE.nextActButton(stage);

		stage.addActLogoImage(actLogo);

		for (int i = 0; i < Consts.Metagame.MISSIONS_IN_ACT; i++)
		{
			IButton missionButton = MenuButtonsBuilder.INSTANCE.missionButton(stage, i);
			stage.addButtonsEnabledPredicate(missionButton, stage::isMissionButtonEnabled);
			stage.addButtonsTextFunction(missionButton, stage::getMissionButtonText);
			stage.addActor(missionButton);
		}

		stage.addButtonsVisiblePredicate(nextActButton, stage::isNextActButtonVisible);
		stage.addButtonsVisiblePredicate(previouwActButton, stage::isPreviousActButtonVisible);
		stage.addButtonsTextFunction(nextActButton, stage::getNextActButtonText);
		stage.addButtonsTextFunction(previouwActButton, stage::getPreviousActButtonText);

		stage.addActorBeforeGUI(actLogo);
		stage.addActorBeforeGUI(logo);
		stage.addActorBeforeGUI(background);
		stage.addActor(backToMenuButton);
		stage.addActor(previouwActButton);
		stage.addActor(nextActButton);

		stage.updateControls();

		return stage;
	}
}
