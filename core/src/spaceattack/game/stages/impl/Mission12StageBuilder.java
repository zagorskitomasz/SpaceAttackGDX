package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public class Mission12StageBuilder extends Act4StageBuilder {

    @Override
    public void setMissionNumber() {

        stage.setCurrentMission(12);
    }

    @Override
    public void addBackground() {

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M12_BACKGROUND.getTexture(), 0, 0);
        stage.addBackground(background);
    }

    @Override
    protected void setTanks(final EnemyBase enemyBase) {

        enemyBase.setTanksPool(Consts.Gameplay.MAJOR_BOSS_TANKS_POOL + getAct().getNumber());
        enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMajorBoss(getAct(), stage));
    }
}
