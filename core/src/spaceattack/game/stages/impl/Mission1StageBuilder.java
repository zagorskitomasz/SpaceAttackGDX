package spaceattack.game.stages.impl;

import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public class Mission1StageBuilder extends GameplayStageBuilder
{
	@Override
	public void setMissionNumber()
	{
		stage.setCurrentMission(1);
	}
	
	@Override
	public void addBackground()
	{
		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M1_BACKGROUND.getTexture(), 0, 0);
		stage.addBackground(background);
	}
}
