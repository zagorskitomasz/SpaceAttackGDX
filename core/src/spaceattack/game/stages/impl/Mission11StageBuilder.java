package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
import spaceattack.game.ai.Act4Mission11EnemyBase;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.IUtils;

public class Mission11StageBuilder extends Act4StageBuilder {

    @Override
    public void setMissionNumber() {

        stage.setCurrentMission(11);
    }

    @Override
    public void addBackground() {

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M11_BACKGROUND.getTexture(), 0, 0);
        stage.addBackground(background);
    }

    @Override
    protected void setTanks(final EnemyBase enemyBase) {

        enemyBase.setTanksPool(Consts.Gameplay.MINOR_BOSS_TANKS_POOL + getAct().getNumber());
        enemyBase.setBoss(EnemyShipsFactory.INSTANCE.createMinorBoss(getAct(), stage));
    }

    @Override
    protected EnemyBase createEnemyBase(final IUtils utils) {

        return new Act4Mission11EnemyBase(utils).setBerserkGainer(playersShip::gainBerserk);
    }
}
