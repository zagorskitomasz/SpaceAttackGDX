package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.stages.impl.MissionsStage;

public class ActChangeListener extends ClickListener
{
	public enum Variants
	{
		NEXT,PREV;
	}

	private MissionsStage stage;
	private Variants variant;

	public ActChangeListener(MissionsStage stage,Variants variant)
	{
		this.stage = stage;
		this.variant = variant;
	}

	@Override
	public void clicked(InputEvent event,float x,float y)
	{
		switch (variant)
		{
			case NEXT:
				stage.nextAct();
				break;
			case PREV:
				stage.previousAct();
				break;
		}
	}
}
