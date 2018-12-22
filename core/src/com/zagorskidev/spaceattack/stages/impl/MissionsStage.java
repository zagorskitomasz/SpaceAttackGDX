package com.zagorskidev.spaceattack.stages.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.consts.Paths;
import com.zagorskidev.spaceattack.consts.UIStrings;
import com.zagorskidev.spaceattack.graphics.Sizes;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameProgress;
import com.zagorskidev.spaceattack.system.NumbersUtils;
import com.zagorskidev.spaceattack.ui.buttons.ActChangeListener;
import com.zagorskidev.spaceattack.ui.buttons.ChangeStageButtonListener;
import com.zagorskidev.spaceattack.ui.buttons.TextButtonsBuilder;

public class MissionsStage extends UIStage
{
	private int actNumber;
	private Actor actLogo;

	private List<Entry<TextButton, ChangeStageButtonListener>> missionsButtons;

	private TextButton nextActButton;
	private TextButton previousActButton;

	public MissionsStage()
	{
		init();
	}

	void init()
	{
		addActor(createImage(Paths.Graphics.UI.MENU_BACKGROUND, 0, 0));
		addActor(createImage(Paths.Graphics.UI.LOGO, 0, 0));
		initButtons();
	}

	void initButtons()
	{
		missionsButtons = new ArrayList<>(Consts.Metagame.MISSIONS_IN_ACT);

		for (int i = 0; i < Consts.Metagame.MISSIONS_IN_ACT; i++)
		{
			ChangeStageButtonListener listener = new ChangeStageButtonListener(this,
					Stages.getMissionStage(calculateMission(i + 1)));
			TextButton button = createMissionButton(i + 1, listener);

			missionsButtons.add(new SimpleEntry<>(button, listener));

			addActor(button);
		}
		nextActButton = createNextActButton();
		addActor(nextActButton);

		previousActButton = createPrevActButton();
		addActor(previousActButton);

		addActor(createBackButton());
	}

	private TextButton createNextActButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.NEXT_ACT.replaceAll("#", NumbersUtils.toRoman(actNumber + 1)),Consts.RED_BTN)
				.setPosition(Sizes.gameWidth() * 0.6f, Sizes.gameHeight() * 0.5f)
				.setSize(Sizes.buttonWidth() * 0.5f, Sizes.buttonHeight())
				.addListener(new ActChangeListener(this,ActChangeListener.Variants.NEXT))
				.build();
		//@formatter:on
	}

	private TextButton createPrevActButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.PREV_ACT.replaceAll("#", NumbersUtils.toRoman(actNumber - 1)),Consts.RED_BTN)
				.setPosition(Sizes.gameWidth() * 0.1f, Sizes.gameHeight() * 0.5f)
				.setSize(Sizes.buttonWidth() * 0.5f, Sizes.buttonHeight())
				.addListener(new ActChangeListener(this,ActChangeListener.Variants.PREV))
				.build();
		//@formatter:on
	}

	private TextButton createMissionButton(int buttonIndex,ChangeStageButtonListener listener)
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.MISSION + buttonIndex)
				.setPosition(Sizes.gameWidth() * 0.2f, Sizes.gameHeight() * (0.48f - buttonIndex * 0.1f))
				.setSize(Sizes.buttonWidth(), Sizes.buttonHeight())
				.addListener(listener)
				.build();
		//@formatter:on
	}

	private Button createBackButton()
	{
		//@formatter:off
		return TextButtonsBuilder
				.INSTANCE
				.getBuilder(this)
				.init(UIStrings.BACK,Consts.RED_BTN)
				.setPosition(Sizes.gameWidth() * 0.2f, Sizes.gameHeight() * 0.04f)
				.setSize(Sizes.buttonWidth(), Sizes.buttonHeight())
				.addListener(new ChangeStageButtonListener(this, Stages.MAIN_MENU))
				.build();
		//@formatter:on
	}

	@Override
	public void setGameProgress(GameProgress progress)
	{
		super.setGameProgress(progress);

		if (progress != null)
		{
			actNumber = (progress.getMission() - 1) / Consts.Metagame.MISSIONS_IN_ACT + 1;
			if (actNumber > Consts.Metagame.ACTS_NUMBER)
				actNumber = Consts.Metagame.ACTS_NUMBER;
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
		actLogo = createImage(Paths.Graphics.UI.ACT_LOGO.replaceAll("#", String.valueOf(actNumber)),
				Sizes.gameWidth() * 0.25f, Sizes.gameHeight() * 0.27f);
		addActor(actLogo);

		for (int i = 0; i < missionsButtons.size(); i++)
		{
			TextButton button = missionsButtons.get(i).getKey();
			button.setText(UIStrings.MISSION + calculateMission(i + 1));
			button.setDisabled(disableMission(i + 1));
			button.setTouchable(button.isDisabled() ? Touchable.disabled : Touchable.enabled);
			missionsButtons.get(i).getValue().setNextStage(Stages.getMissionStage(calculateMission(i + 1)));
		}
		getActors();
		nextActButton.setVisible(showNextAct());
		nextActButton.setText(UIStrings.NEXT_ACT.replaceAll("#", NumbersUtils.toRoman(actNumber + 1)));

		previousActButton.setVisible(showPrevAct());
		previousActButton.setText(UIStrings.PREV_ACT.replaceAll("#", NumbersUtils.toRoman(actNumber - 1)));
	}

	int calculateMission(int buttonIndex)
	{
		return (actNumber - 1) * Consts.Metagame.MISSIONS_IN_ACT + buttonIndex;
	}

	boolean disableMission(int buttonIndex)
	{
		return calculateMission(buttonIndex) > gameProgress.getMission();
	}

	boolean showNextAct()
	{
		return actNumber < Consts.Metagame.ACTS_NUMBER && gameProgress.getMission() > actNumber * 3;
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
