package com.zagorskidev.spaceattack.stages.impl;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.graphics.StaticImageFactory;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.ui.buttons.ChangeStageButtonListener;
import com.zagorskidev.spaceattack.ui.buttons.TextButtonsBuilder;

public class MissionsStage extends UIStage
{
	private int actNumber;
	private Actor actLogo;

	private List<TextButton> missionsButtons;
	private Button nextActButton;
	private Button previousActButton;

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
			TextButton button = createMissionButton(i);
			missionsButtons.add(button);
			addActor(button);
		}
		nextActButton = createChangeActButton();
		addActor(nextActButton);

		previousActButton = createChangeActButton();
		addActor(previousActButton);

		addActor(createBackButton());
	}

	Button createChangeActButton()
	{
		// TODO
		// return nextActButton = new ChangeActButton(this, NEXT);
		return null;
	}

	private TextButton createMissionButton(int buttonIndex)
	{
		// TODO
		// TextButton button = new MissionButton(this, Consts.GAME_WIDTH * 0.2f,
		// Consts.GAME_HEIGHT * (0.38f + buttonIndex * 0.7f));
		return null;
	}

	private Button createBackButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.BACK)
				.setPosition(Consts.GAME_WIDTH * 0.2f, Consts.GAME_HEIGHT * 0.1f)
				.setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT)
				.addListener(new ChangeStageButtonListener(this, Stages.MAIN_MENU))
				.build();
		//@formatter:on
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
