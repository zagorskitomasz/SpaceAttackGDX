package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameListener;
import com.zagorskidev.spaceattack.ui.buttons.TextButtonsBuilder;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.consts.UIStrings;
import spaceattack.game.buttons.ChangeStageButtonListener;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.UIStage;
import spaceattack.game.system.GameLoader;

public class MainMenuStage extends UIStage
{

	void init()
	{
		continueGameButton = createContinueGameButton();
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
