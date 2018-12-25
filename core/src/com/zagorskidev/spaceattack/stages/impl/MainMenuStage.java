package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.graphics.Textures;
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

		addActor(createImage(Textures.MENU_BACKGROUND.getTexture(), 0, 0));
		addActor(createImage(Textures.LOGO.getTexture(), 0, getConstGameHeight() * 0.03f));
		addActor(createNewGameButton());
		addActor(continueGameButton);
		addActor(createExitGameButton());
	}

	float getConstGameHeight()
	{
		return Sizes.gameHeight();
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
				.setPosition(Sizes.gameWidth() * 0.2f, getConstGameHeight() * 0.5f)
				.setSize(Sizes.buttonWidth(), Sizes.buttonHeight())
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
				.setPosition(Sizes.gameWidth() * 0.2f, getConstGameHeight() * 0.38f)
				.setSize(Sizes.buttonWidth(), Sizes.buttonHeight())
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
				.setPosition(Sizes.gameWidth() * 0.2f, getConstGameHeight() * 0.1f)
				.setSize(Sizes.buttonWidth(), Sizes.buttonHeight())
				.addListener(new ExitGameListener(this))
				.build();
		//@formatter:on
	}
}
