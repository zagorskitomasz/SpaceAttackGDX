package spaceattack.game.weapons.flame;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.AbstractWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.missiles.Missile;

public class Flamethrower extends AbstractWeapon {

    private Flame lastFlame;

    public Flamethrower(final int armory) {

        dmg = Consts.Weapons.FLAME_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.FLAME_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.FLAME_COST_PER_ATTR * armory;
    }

    @Override
    public float getWeaponsMovementFactor() {

        return 2f;
    }

    @Override
    public float getCollisionRadius() {

        return Consts.Weapons.SHIELD_RADIUS;
    }

    @Override
    protected float getShotCost() {

        return energyCost / 3;
    }

    @Override
    public void setUtils(final IUtils utils) {

        super.setUtils(utils);
        frameController.reset(Consts.Weapons.SHIELD_ATTACKS_PER_SECOND);
    }

    @Override
    public void setController(final IWeaponController weaponController) {

        this.controller = weaponController;
    }

    @Override
    protected Missile buildMissile() {

        if (lastFlame != null) {
            if (lastFlame.isToKill()) {
                lastFlame = null;
            }
            else {
                return null;
            }
        }

        final float yMov = (controller.getShip().getHeight() / 2
                + Animations.FLAME_P.getAnimation().getFrame().getHeight() / 2) * (controller.isPlayer() ? 1 : -1);

        Flame flame = new Flame();

        flame.setActor(Factories.getActorFactory().create(flame));
        flame.setAnimation(
                controller.isPlayer() ? Animations.FLAME_P.getAnimation() : Animations.FLAME_E.getAnimation());
        flame.setDmg(dmg * controller.getDamageFactor());
        flame.setEnergyCost(getEnergyCost());
        flame.setPosition(controller.getShip().getPosition());
        flame.setPositionSupplier(() -> {
            IVector basePosition = controller.getShip().getPosition();
            return Factories.getVectorFactory().create(basePosition.getX(), basePosition.getY() + yMov);
        });
        flame.setActivityPredicate(controller::isContinuousFireTriggered);
        flame.setRadius(Consts.Weapons.FLAME_RADIUS);
        flame.setSound(Sounds.FLAME);
        flame.setPlayersAttack(controller.isPlayer());

        lastFlame = flame;

        return flame;
    }

    float getDmg() {

        return dmg * controller.getDamageFactor();
    }

    @Override
    public boolean isContinuousFire() {

        return true;
    }
}
