package spaceattack.game.buttons;

import spaceattack.consts.Sizes;
import spaceattack.consts.UIStrings;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.impl.MainMenuStage;
import spaceattack.game.stages.impl.MissionsStage;
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

	public IButton backToMenuButton(MissionsStage stage)
	{
		IButton button = factory.createAlertButton(UIStrings.BACK);

		button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.04f);
		button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
		button.addListener(new ChangeStageButtonListener(stage, Stages.MAIN_MENU));

		return button;
	}

	public IButton previousActButton(MissionsStage stage)
	{
		IButton button = factory.create(UIStrings.PREV_ACT);

		button.setPosition(Sizes.GAME_WIDTH * 0.1f, Sizes.GAME_HEIGHT * 0.5f);
		button.setSize(Sizes.BUTTON_WIDTH * 0.5f, Sizes.BUTTON_HEIGHT);
		button.addListener(new ActChangeButtonListener(stage, ActChangeButtonListener.Variants.PREV));

		return button;
	}

	public IButton nextActButton(MissionsStage stage)
	{
		IButton button = factory.create(UIStrings.NEXT_ACT);

		button.setPosition(Sizes.GAME_WIDTH * 0.6f, Sizes.GAME_HEIGHT * 0.5f);
		button.setSize(Sizes.BUTTON_WIDTH * 0.5f, Sizes.BUTTON_HEIGHT);
		button.addListener(new ActChangeButtonListener(stage, ActChangeButtonListener.Variants.NEXT));

		return button;
	}

	public IButton missionButton(MissionsStage stage,int gridPosition)
	{
		IButton button = factory.create(UIStrings.MISSION);

		button.setColumnPosition(gridPosition);
		button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * (0.48f - (gridPosition + 1) * 0.1f));
		button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
		button.addListener(new LaunchMissionButtonListener(stage, gridPosition));

		return button;
	}
}
