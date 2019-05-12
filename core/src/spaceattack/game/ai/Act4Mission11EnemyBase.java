package spaceattack.game.ai;

import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act4Mission11EnemyBase extends Act4EnemyBase {

    public Act4Mission11EnemyBase(final IUtils utils) {

        super(utils);
        setAct(Acts.IV);
    }

    @Override
    protected void addBoss() {

        super.addBoss();
        addSuperChaser(Direction.LEFT);
        addSuperChaser(Direction.RIGHT);
    }
}
