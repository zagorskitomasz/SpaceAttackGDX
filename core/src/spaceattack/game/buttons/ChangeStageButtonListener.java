package spaceattack.game.buttons;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.Stages;

public class ChangeStageButtonListener implements IListener
{
	protected IGameStage stage;
	private Stages nextStage;

	public ChangeStageButtonListener(IGameStage stage,Stages nextStage)
	{
		this.stage = stage;
		this.nextStage = nextStage;
	}

	@Override
	public void clicked()
	{
		finalizeStage();
	}

	public void finalizeStage()
	{
		stage.setResult(createResult(stage.getGameProgress()));
	}

	protected StageResult createResult(GameProgress progress)
	{
		StageResult result = new StageResult();
		result.setNextStage(nextStage);
		result.setGameProgress(progress);

		return result;
	}
}
