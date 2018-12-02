package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.ui.buttons.ContinueGameButton;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameButton;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton;

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

		addActor(createNewGameButton());
		addActor(continueGameButton);
		addActor(createExitGameButton());
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
		return new NewGameButton(this);
	}

	Button createContinueGameButton()
	{
		return new ContinueGameButton(this);
	}

	Button createExitGameButton()
	{
		return new ExitGameButton(this);
	}
}
