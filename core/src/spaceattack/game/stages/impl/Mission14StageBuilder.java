package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
import spaceattack.game.ai.Act5Mission14EnemyBase;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.IUtils;

public class Mission14StageBuilder extends Act5StageBuilder {

    @Override
    public void setMissionNumber() {

        stage.setCurrentMission(14);
    }

    @Override
    protected EnemyBase createEnemyBase(final IUtils utils) {

        return new Act5Mission14EnemyBase(utils);
    }

    @Override
    public void addBackground() {

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M14_BACKGROUND.getTexture(), 0, 0);
        stage.addBackground(background);
    }

    @Override
    protected void setTanks(final EnemyBase enemyBase) {

        enemyBase.setTanksPool(Consts.Gameplay.BOSS_TANKS_POOL);
        enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMajorBoss(getAct(), stage));
    }
}
