package spaceattack.game.stages.impl;

import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public class Mission5StageBuilder extends Act2StageBuilder
{
	@Override
	public void setMissionNumber()
	{
		stage.setCurrentMission(5);
	}
	
	@Override
	public void addBackground()
	{
		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M5_BACKGROUND.getTexture(), 0, 0);
		stage.addBackground(background);
	}

	@Override
	protected void setTanks(EnemyBase enemyBase) 
	{
		enemyBase.setTanksPool(7);
		enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMinorBoss(getAct(), stage));
	}
}
