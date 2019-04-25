package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public class Mission6StageBuilder extends Act2StageBuilder
{
	@Override
	public void setMissionNumber()
	{
		stage.setCurrentMission(6);
	}
	
	@Override
	public void addBackground()
	{
		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M6_BACKGROUND.getTexture(), 0, 0);
		stage.addBackground(background);
	}

	@Override
	protected void setTanks(EnemyBase enemyBase) 
	{
		enemyBase.setTanksPool(Consts.Gameplay.BOSS_TANKS_POOL);
		enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMajorBoss(getAct(), stage));
	}
}
