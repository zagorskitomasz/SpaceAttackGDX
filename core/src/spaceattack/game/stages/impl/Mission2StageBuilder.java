package spaceattack.game.stages.impl;

import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public class Mission2StageBuilder extends GameplayStageBuilder
{
	@Override
	public void setMissionNumber()
	{
		stage.setCurrentMission(2);
	}
	
	@Override
	public void addBackground()
	{
		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M2_BACKGROUND.getTexture(), 0, 0);
		stage.addBackground(background);
	}

	@Override
	protected void setTanks(EnemyBase enemyBase) 
	{
		enemyBase.setTanksPool(7);
		enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMinorBoss(Acts.I, stage));
	}
}
