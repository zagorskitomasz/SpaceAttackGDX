package spaceattack.game.stages.impl;

import spaceattack.game.stages.UIStage;
import spaceattack.game.system.graphics.StaticImage;

public class MissionsStage extends UIStage
{
	private StaticImage actLogo;

	public void addActLogoImage(StaticImage actLogo)
	{
		this.actLogo = actLogo;
	}
}
