package com.zagorskidev.spaceattack.stages.impl;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.graphics.StaticImageFactory;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.BackButton;
import com.zagorskidev.spaceattack.ui.buttons.ChangeActButton;
import com.zagorskidev.spaceattack.ui.buttons.MissionButton;

public class MissionsStage extends UIStage
{
	private int actNumber;
	private Actor actLogo;

	private List<TextButton> missionsButtons;
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
		initButtons();
	}

	void initButtons()
	{
		missionsButtons = new ArrayList<>(Consts.MISSIONS_IN_ACT);

		for (int i = 0; i < Consts.MISSIONS_IN_ACT; i++)
		{
			TextButton button = new MissionButton(this, Consts.GAME_WIDTH * 0.2f,
					Consts.GAME_HEIGHT * (0.38f + i * 0.7f));
			missionsButtons.add(button);
			addActor(button);
		}
		nextActButton = new ChangeActButton(this, ChangeActButton.Variant.NEXT);
		addActor(nextActButton);

		previousActButton = new ChangeActButton(this, ChangeActButton.Variant.PREV);
		addActor(previousActButton);

		addActor(new BackButton(this));
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
			actNumber = (progress.getMission() - 1) / Consts.MISSIONS_IN_ACT + 1;
			if (actNumber > Consts.ACTS_NUMBER)
				actNumber = Consts.ACTS_NUMBER;
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

	void refreshAct()
	{
		getActors().removeValue(actLogo, true);
		actLogo = createImage(Paths.ACT_LOGO + actNumber, Consts.GAME_WIDTH * 0.4f, Consts.GAME_HEIGHT * 0.3f);
		addActor(actLogo);

		for (int i = 0; i < missionsButtons.size(); i++)
		{
			TextButton button = missionsButtons.get(i);
			button.setText(UIStrings.MISSION + calculateMission(i));
			button.setDisabled(disableMission(i));
		}
		nextActButton.setVisible(showNextAct());
		previousActButton.setVisible(showPrevAct());
	}

	int calculateMission(int buttonIndex)
	{
		return (actNumber - 1) * Consts.MISSIONS_IN_ACT + buttonIndex;
	}

	boolean disableMission(int buttonIndex)
	{
		return calculateMission(buttonIndex) > gameProgress.getMission();
	}

	boolean showNextAct()
	{
		return actNumber < Consts.ACTS_NUMBER && gameProgress.getMission() > actNumber * 3;
	}

	boolean showPrevAct()
	{
		return actNumber > 1;
	}

	int getAct()
	{
		return actNumber;
	}

	void setAct(int actNumber)
	{
		this.actNumber = actNumber;
	}
}
