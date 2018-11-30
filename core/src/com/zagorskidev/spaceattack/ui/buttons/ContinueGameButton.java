package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.Consts;
import com.zagorskidev.spaceattack.UIStrings;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.UIStage;

public class ContinueGameButton extends TextButton
{
	private IStage stage;

	public ContinueGameButton(UIStage stage)
	{
		super(UIStrings.CONTINUE, stage.getSkin(), Consts.DEFAULT);
		this.stage = stage;
	}

}
