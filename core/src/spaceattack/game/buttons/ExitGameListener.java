package spaceattack.game.buttons;

import spaceattack.consts.UIStrings;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.utils.IUtils;

public class ExitGameListener implements IListener
{
	private IGameStage stage;
	private IUtils utils;

	public ExitGameListener(IGameStage stage)
	{
		this.stage = stage;
		utils = Factories.getUtilsFactory().create();
	}

	@Override
	public void clicked()
	{
		utils.confirmDialog(UIStrings.EXIT, UIStrings.EXIT_QUESTION, stage.getStage(), this::processExitDialogResult);
	}

	public void processExitDialogResult(boolean dialogResult)
	{
		if (dialogResult)
			utils.exit();
	}
}