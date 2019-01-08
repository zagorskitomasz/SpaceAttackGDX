package spaceattack.game.buttons;

import spaceattack.consts.UIStrings;
import spaceattack.game.GameProgress;
import spaceattack.game.buttons.ChangeStageButtonListener;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;
import spaceattack.game.system.GameLoader;
import spaceattack.game.utils.IUtils;

public class NewGameListener extends ChangeStageButtonListener
{
	private GameLoader loader;
	private IUtils utils;

	public NewGameListener(IGameStage stage,GameLoader gameLoader)
	{
		super(stage, Stages.MISSIONS);
		loader = gameLoader;
		utils = Factories.getUtilsFactory().create();
	}

	@Override
	public void clicked()
	{
		if (!loader.fileExists() || confirm())
			finalizeStage();
	}

	private boolean confirm()
	{
		return utils.confirmDialog(UIStrings.NEW_GAME, UIStrings.NEW_GAME_QUESTION, stage.getStage());
	}

	@Override
	public void finalizeStage()
	{
		stage.setResult(createResult(new GameProgress()));
	}
}