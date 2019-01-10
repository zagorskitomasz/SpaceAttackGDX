package spaceattack.game.stages.impl;

import spaceattack.game.buttons.IButton;
import spaceattack.game.stages.UIStage;

public class MainMenuStage extends UIStage
{
	public boolean continueButtonEnabled(IButton button)
	{
		return gameLoader != null && gameLoader.fileExists();
	}
}
