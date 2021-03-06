package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;

public class Mission4StageBuilder extends Act2StageBuilder {

    @Override
    public void setMissionNumber() {

        stage.setCurrentMission(4);
    }

    @Override
    public void addBackground() {

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M4_BACKGROUND.getTexture(), 0, 0);
        stage.addBackground(background);
    }

    @Override
    protected void setTanks(final EnemyBase enemyBase) {

        enemyBase.setTanksPool(Consts.Gameplay.NO_BOSS_TANKS_POOL + getAct().getNumber());
    }
}
