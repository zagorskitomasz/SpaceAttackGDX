package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.Consts;
import com.zagorskidev.spaceattack.UIStrings;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;

public class NewGameButton extends TextButton
{
	private IStage stage;

	public NewGameButton(UIStage pmStage)
	{
		super(UIStrings.CONTINUE, pmStage.getSkin(), Consts.DEFAULT);
		stage = pmStage;

		init();
	}

	void init()
	{
		addListener(createListener());
	}

	NewGameListener createListener()
	{
		return new NewGameListener();
	}

	void setStage(IStage stage)
	{
		this.stage = stage;
	}

	class NewGameListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event,float x,float y)
		{
			StageResult result = new StageResult();
			result.setNextStage(Stages.MISSIONS);
			getStage().setResult(result);
		}

		IStage getStage()
		{
			return stage;
		}
	}
}