package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameProgress;

public class ContinueGameButton extends TextButton
{
	private IStage stage;

	public ContinueGameButton(UIStage stage)
	{
		super(UIStrings.CONTINUE, stage.getSkin(), Consts.DEFAULT);
		this.stage = stage;

		init();
	}

	void init()
	{
		setPosition(Consts.GAME_WIDTH * 0.2f, 7 * Consts.BUTTON_HEIGHT);
		setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
		addListener(createListener());
	}

	ContinueGameListener createListener()
	{
		return new ContinueGameListener();
	}

	void setStage(IStage stage)
	{
		this.stage = stage;
	}

	class ContinueGameListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event,float x,float y)
		{
			finalizeStage();
		}

		private StageResult createResult(GameProgress progress)
		{
			StageResult result = new StageResult();
			result.setNextStage(Stages.MISSIONS);
			result.setGameProgress(progress);

			return result;
		}

		public void finalizeStage()
		{
			stage.setResult(createResult(new GameProgress()), true);
		}
	}
}
