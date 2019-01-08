package spaceattack.game.buttons;

import spaceattack.consts.Sizes;
import spaceattack.consts.UIStrings;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;

public enum MenuButtonsBuilder
{
	INSTANCE;

	private ITextButtonFactory factory;

	private MenuButtonsBuilder()
	{
		factory = Factories.getTextButtonFactory();
	}

	public IButton newGameButton(IGameStage stage)
	{
		IButton button = factory.create(UIStrings.NEW_GAME);

		button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.5f);
		button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
		button.addListener(new NewGameListener(stage, GameLoaderFactory.INSTANCE.create()));

		return button;
	}

	public IButton continueGameButton()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public IButton exitGameButton()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
