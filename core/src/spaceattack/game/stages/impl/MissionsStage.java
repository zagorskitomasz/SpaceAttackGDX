package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
import spaceattack.consts.UIStrings;
import spaceattack.game.GameProgress;
import spaceattack.game.buttons.IButton;
import spaceattack.game.stages.UIStage;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.utils.NumbersUtils;

public class MissionsStage extends UIStage
{
	private StaticImage actLogo;
	private int actNumber;

	@Override
	public void setGameProgress(GameProgress progress)
	{
		super.setGameProgress(progress);

		actNumber = (progress.getMission() - 1) / Consts.Metagame.MISSIONS_IN_ACT + 1;

		if (actNumber > Consts.Metagame.ACTS_NUMBER)
			actNumber = Consts.Metagame.ACTS_NUMBER;
	}

	public void addActLogoImage(StaticImage actLogo)
	{
		this.actLogo = actLogo;
	}

	public void nextAct()
	{
		actNumber++;
		updateControls();
	}

	public void previousAct()
	{
		actNumber--;
		updateControls();
	}

	public int calculateMission(int buttonGridPosition)
	{
		return (actNumber - 1) * Consts.Metagame.MISSIONS_IN_ACT + buttonGridPosition + 1;
	}

	public boolean isMissionButtonEnabled(IButton missionButton)
	{
		return calculateMission(missionButton.getGridPosition()) <= getGameProgress().getMission();
	}

	public String getMissionButtonText(IButton missionButton)
	{
		return UIStrings.MISSION + calculateMission(missionButton.getGridPosition());
	}

	public boolean isNextActButtonVisible(IButton button)
	{
		return actNumber < Consts.Metagame.ACTS_NUMBER && getGameProgress().getMission() > actNumber * 3;
	}

	public boolean isPreviousActButtonVisible(IButton button)
	{
		return actNumber > 1;
	}

	public String getNextActButtonText(IButton button)
	{
		return UIStrings.NEXT_ACT.replaceAll("#", NumbersUtils.toRoman(actNumber + 1));
	}

	public String getPreviousActButtonText(IButton button)
	{
		return UIStrings.PREV_ACT.replaceAll("#", NumbersUtils.toRoman(actNumber - 1));
	}

	@Override
	public void updateControls()
	{
		super.updateControls();
		actLogo.setTexture(Acts.get(actNumber).getLogo().getTexture());
	}

	void setAct(int actNumber)
	{
		this.actNumber = actNumber;
	}

	int getAct()
	{
		return actNumber;
	}
}
