package spaceattack.game.buttons;

import spaceattack.consts.Sizes;
import spaceattack.consts.UIStrings;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.impl.MainMenuStage;
import spaceattack.game.system.GameLoaderFactory;

public enum MenuButtonsBuilder
{
	INSTANCE;

	private ITextButtonFactory factory;

	private MenuButtonsBuilder()
	{
		factory = Factories.getTextButtonFactory();
	}

	public IButton newGameButton(MainMenuStage stage)
	{
		IButton button = factory.create(UIStrings.NEW_GAME);

		button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.5f);
		button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
		button.addListener(new NewGameListener(stage, GameLoaderFactory.INSTANCE.create()));

		return button;
	}

	public IButton continueGameButton(MainMenuStage stage)
	{
		IButton button = factory.create(UIStrings.CONTINUE);

		button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.38f);
		button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
		button.addListener(new ChangeStageButtonListener(stage, Stages.MISSIONS));

		return button;
	}

	public IButton exitGameButton(MainMenuStage stage)
	{
		IButton button = factory.create(UIStrings.EXIT);

		button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.1f);
		button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
		button.addListener(new ExitGameListener(stage));

		return button;
	}
}
