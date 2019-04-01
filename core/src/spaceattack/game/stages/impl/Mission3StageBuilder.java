package spaceattack.game.stages.impl;

import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;

public class Mission3StageBuilder extends GameplayStageBuilder
{
	@Override
	public void setMissionNumber()
	{
		stage.setCurrentMission(3);
	}
	
	@Override
	public void addBackground()
	{
		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M3_BACKGROUND.getTexture(), 0, 0);
		stage.addBackground(background);
		MusicPlayer.INSTANCE.playAct(Acts.I);
	}

	@Override
	protected void setTanks(EnemyBase enemyBase) 
	{
		enemyBase.setTanksPool(7);
		enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMajorAct1Boss(stage));
	}
}
