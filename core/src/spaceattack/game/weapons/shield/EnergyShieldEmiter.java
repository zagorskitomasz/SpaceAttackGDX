package spaceattack.game.weapons.shield;

import java.util.function.Predicate;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.AbstractWeapon;
import spaceattack.game.weapons.missiles.Missile;

public class EnergyShieldEmiter extends AbstractWeapon {

    private EnergyShield lastShield;
    private Predicate<Float> activityChecker;
    private long duration;

    @Override
    public float getWeaponsMovementFactor() {

        return 0.0f;
    }

    @Override
    public float getCollisionRadius() {

        return Consts.Weapons.SHIELD_RADIUS;
    }

    @Override
    public void setLevel(final int level) {

        dmg = Consts.Weapons.SHIELD_BASE_DMG + (level - 1) * Consts.Weapons.SHIELD_DMG_PER_LEVEL;
        energyCost = Consts.Weapons.SHIELD_BASE_COST + (level - 1) * Consts.Weapons.SHIELD_COST_PER_LEVEL;
        speed = 0;
    }

    @Override
    protected float getShotCost() {

        return getEnergyCost() / 3;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.SHIELD_ATTACKS_PER_SECOND);
    }

    @Override
    protected Missile buildMissile() {

        if (lastShield != null) {
            if (lastShield.isToKill()) {
                lastShield = null;
            }
            else {
                return null;
            }
        }
        if (activityChecker == null) {
            activityChecker = controller::isContinuousFireTriggered;
        }

        EnergyShield shield = new EnergyShield();

        shield.setActor(Factories.getActorFactory().create(shield));
        shield.setAnimation(Animations.SHIELD.getAnimation());
        shield.setDmg(dmg);
        shield.setEnergyCost(getEnergyCost());
        shield.setPositionSupplier(() -> controller.getShip().getPosition());
        if (duration > 0) {
            long launched = utils.millis();
            shield.setActivityPredicate(
                    unrealtedNumber -> !controller.getShip().isToKill() && launched + 3000 > utils.millis());
        }
        else {
            shield.setActivityPredicate(activityChecker);
        }
        shield.setRadius(Consts.Weapons.SHIELD_RADIUS);
        shield.setSound(Sounds.SHIELD);
        shield.setPlayersAttack(controller.isPlayer());

        controller.getShip().setTemporalImmortalityChecker(shield::isUp);

        lastShield = shield;

        return shield;
    }

    float getDmg() {

        return dmg;
    }

    @Override
    public boolean isContinuousFire() {

        return true;
    }

    public void setActivityPredicate(final Predicate<Float> predicate) {

        activityChecker = predicate;
    }

    public void setShieldDuration(final long duration) {

        this.duration = duration;
    }
}
