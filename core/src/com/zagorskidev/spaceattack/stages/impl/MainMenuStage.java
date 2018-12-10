package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.ui.buttons.ChangeStageButtonListener;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameListener;
import com.zagorskidev.spaceattack.ui.buttons.NewGameListener;
import com.zagorskidev.spaceattack.ui.buttons.TextButtonsBuilder;

public class MainMenuStage extends UIStage
{
	private Button continueGameButton;

	public MainMenuStage()
	{
		init();
	}

	void init()
	{
		continueGameButton = createContinueGameButton();

		addActor(createImage(Paths.MENU_BACKGROUND, 0, 0));
		addActor(createImage(Paths.LOGO, 0, getConstGameHeight() * 0.03f));
		addActor(createNewGameButton());
		addActor(continueGameButton);
		addActor(createExitGameButton());
	}

	float getConstGameHeight()
	{
		return Consts.GAME_HEIGHT;
	}

	@Override
	public void loadGame(GameLoader loader)
	{
		gameProgress = loader.load(this);
		if (!loader.fileExists())
		{
			continueGameButton.setTouchable(Touchable.disabled);
			continueGameButton.setDisabled(true);
		}
	}

	Button createNewGameButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.NEW_GAME)
				.setPosition(Consts.GAME_WIDTH * 0.2f, getConstGameHeight() * 0.5f)
				.setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT)
				.addListener(new NewGameListener(this, Stages.MISSIONS))
				.build();
		//@formatter:on
	}

	Button createContinueGameButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.CONTINUE)
				.setPosition(Consts.GAME_WIDTH * 0.2f, getConstGameHeight() * 0.38f)
				.setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT)
				.addListener(new ChangeStageButtonListener(this, Stages.MISSIONS))
				.build();
		//@formatter:on
	}

	Button createExitGameButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.EXIT,Consts.RED_BTN)
				.setPosition(Consts.GAME_WIDTH * 0.2f, getConstGameHeight() * 0.1f)
				.setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT)
				.addListener(new ExitGameListener(this))
				.build();
		//@formatter:on
	}
}
