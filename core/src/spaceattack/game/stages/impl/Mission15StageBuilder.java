package spaceattack.game.stages.impl;

import spaceattack.game.ai.Act5Mission15EnemyBase;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ships.enemy.boss.FinalBossBuilder;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.IUtils;

public class Mission15StageBuilder extends Act5StageBuilder {

    @Override
    public void setMissionNumber() {

        stage.setCurrentMission(15);
    }

    @Override
    protected EnemyBase createEnemyBase(final IUtils utils) {

        return new Act5Mission15EnemyBase(utils, FinalBossBuilder.INSTANCE);
    }

    @Override
    public void addBackground() {

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M15_BACKGROUND.getTexture(), 0, 0);
        stage.addBackground(background);
    }

    @Override
    protected void setTanks(final EnemyBase enemyBase) {

        // do nothing - this mission has dedicated enemy base implementation
    }
}
