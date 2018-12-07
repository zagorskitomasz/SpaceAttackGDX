package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.stages.impl.MissionsStage;

public class ChangeActButton extends TextButton
{

	public enum Variant
	{
		NEXT,PREV;
	}

	public ChangeActButton(MissionsStage missionsStage,Variant next)
	{
		super(null, null, null);
	}
}
