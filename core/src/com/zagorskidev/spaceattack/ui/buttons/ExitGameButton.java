package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.Consts;
import com.zagorskidev.spaceattack.UIStrings;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.UIStage;

public class ExitGameButton extends TextButton
{
	private IStage stage;

	public ExitGameButton(UIStage stage)
	{
		super(UIStrings.EXIT, stage.getSkin(), Consts.DEFAULT);
		this.stage = stage;
	}
}
