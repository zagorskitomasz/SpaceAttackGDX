package com.zagorskidev.spaceattack.stages.impl;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.graphics.StaticImageFactory;
import com.zagorskidev.spaceattack.stages.AbstractStage;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.ChangeActButton;
import com.zagorskidev.spaceattack.ui.buttons.MissionButton;

public class MissionsStage extends AbstractStage
{
	private int actNumber;

	private MissionButton[] missionsButtons;
	private ChangeActButton nextActButton;
	private ChangeActButton previousActButton;

	public MissionsStage()
	{
		init();
	}

	void init()
	{
		addActor(createImage(Paths.MENU_BACKGROUND, 0, 0));
		addActor(createImage(Paths.LOGO, 0, 30));
		// TODO create buttons and add them as actors
	}

	Actor createImage(String path,float x,float y)
	{
		return StaticImageFactory.INSTANCE.create(path, x, y);
	}

	@Override
	public void setGameProgress(GameProgress progress)
	{
		super.setGameProgress(progress);

		if (progress != null)
		{
			actNumber = (progress.getMission() - 1) / 3 + 1;
			refreshAct();
		}
	}

	public void nextAct()
	{
		actNumber++;
		refreshAct();
	}

	public void previousAct()
	{
		actNumber--;
		refreshAct();
	}

	private void refreshAct()
	{
		// TODO change act logo and button text, set mission buttons enabled and arrows
		// visible
	}

	int getAct()
	{
		return actNumber;
	}
}
