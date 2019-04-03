package spaceattack.game.stages.impl;

import spaceattack.game.ai.Act1EnemyBase;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;
import spaceattack.game.utils.IUtils;

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
		MusicPlayer.INSTANCE.playAct(getAct());
	}

	@Override
	protected void setTanks(EnemyBase enemyBase) 
	{
		enemyBase.setTanksPool(8);
	}

	@Override
	protected Acts getAct() 
	{
		return Acts.I;
	}

	@Override
	protected EnemyBase createEnemyBase(IUtils utils) 
	{
		return new Act1EnemyBase(utils);
	}
}
