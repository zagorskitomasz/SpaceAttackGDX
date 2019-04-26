package spaceattack.game.weapons.miner;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.missiles.ExplosiveMissile;

public class Mine extends ExplosiveMissile {

    private IUtils utils;
    private long created;
    private long delay;

    Mine(long explodeAfterMillis) {

        utils = Factories.getUtilsFactory().create();
        created = utils.millis();
        delay = explodeAfterMillis;
    }

    @Override
    public void act(float delta) {

        super.act(delta);

        if (utils.millis() > created + delay) {
            setToKill();
        }
    }

    @Override
    protected void move() {

        // do nothing
    }

    @Override
    public void overheat() {

        if (utils.millis() + Consts.Weapons.MINE_OVERHEAT_DELAY < created + delay) {
            created = utils.millis();
            delay = Consts.Weapons.MINE_OVERHEAT_DELAY;
        }
    }
}
