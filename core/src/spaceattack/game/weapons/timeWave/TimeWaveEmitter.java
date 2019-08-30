package spaceattack.game.weapons.timeWave;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.AbstractWeapon;
import spaceattack.game.weapons.missiles.Missile;

public class TimeWaveEmitter extends AbstractWeapon {

    public TimeWaveEmitter(final int armory) {

        dmg = Consts.Weapons.WAVE_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.WAVE_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.WAVE_COST_PER_ATTR * armory;
    }

    @Override
    public float getWeaponsMovementFactor() {

        return 0;
    }

    @Override
    public float getCollisionRadius() {

        return 0;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.TIME_WAVE_ATTACKS_PER_SECOND);
    }

    @Override
    protected Missile buildMissile() {

        TimeWave wave = new TimeWave();

        wave.setActor(Factories.getActorFactory().create(wave));
        wave.setAnimation(Animations.TIME_WAVE.getAnimation());
        wave.setPositionSupplier(() -> controller.getShip().getPosition());
        wave.setSound(Sounds.TIME_WAVE);
        wave.setDmg(dmg * controller.getDamageFactor() * (controller.isPlayer() ? 0.6f : 1));
        wave.setPlayersAttack(controller.isPlayer());

        return wave;
    }
}
