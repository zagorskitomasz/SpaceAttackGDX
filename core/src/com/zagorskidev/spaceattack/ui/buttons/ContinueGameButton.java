package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;

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
		setPosition(Consts.GAME_WIDTH * 0.2f, Consts.GAME_HEIGHT * 0.38f);
		setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
		addListener(createListener());
	}

	ClickListener createListener()
	{
		return new ChangeStageButtonListener(stage, Stages.MISSIONS);
	}

	void setStage(IStage stage)
	{
		this.stage = stage;
	}
}
