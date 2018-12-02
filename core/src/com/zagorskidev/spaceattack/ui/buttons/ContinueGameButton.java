package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.UIStrings;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.stages.IStage;
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
		setPosition(Consts.GAME_WIDTH * 0.2f, 10 * Consts.BUTTON_HEIGHT);
		setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
	}

	void setStage(IStage stage)
	{
		this.stage = stage;
	}
}
