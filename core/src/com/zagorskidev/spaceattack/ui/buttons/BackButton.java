package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;

public class BackButton extends TextButton
{
	public BackButton(UIStage stage)
	{
		super(UIStrings.BACK, stage.getSkin(), Consts.DEFAULT);
		init(stage);
	}

	void init(UIStage stage)
	{
		setPosition(Consts.GAME_WIDTH * 0.2f, Consts.GAME_HEIGHT * 0.1f);
		setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
		addListener(new ChangeStageButtonListener(stage, Stages.MAIN_MENU));
	}
}