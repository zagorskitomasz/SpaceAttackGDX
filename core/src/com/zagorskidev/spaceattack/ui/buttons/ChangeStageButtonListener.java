package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.system.GameProgress;

public class ChangeStageButtonListener extends ClickListener
{
	private IStage stage;
	private Stages nextStage;

	public ChangeStageButtonListener(IStage stage,Stages nextStage)
	{
		this.stage = stage;
		this.nextStage = nextStage;
	}

	@Override
	public void clicked(InputEvent event,float x,float y)
	{
		finalizeStage();
	}

	private StageResult createResult(GameProgress progress)
	{
		StageResult result = new StageResult();
		result.setNextStage(nextStage);
		result.setGameProgress(progress);

		return result;
	}

	public void finalizeStage()
	{
		stage.setResult(createResult(stage.getGameProgress()), true);
	}
}
