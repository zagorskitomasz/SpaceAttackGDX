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

    private float yMov;

    @Override
    public float getWeaponsMovementFactor() {

        return 2f;
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

        yMov = controller.getShip().getHeight() / 2 + Animations.FLAME_P.getAnimation().getFrame().getHeight() / 2;

        if (!controller.isPlayer()) {
            yMov *= -1;
        }
    }

    @Override
    protected Missile buildMissile() {

        Flame flame = new Flame();

        flame.setActor(Factories.getActorFactory().create(flame));
        flame.setAnimation(
                controller.isPlayer() ? Animations.FLAME_P.getAnimation() : Animations.FLAME_E.getAnimation());
        flame.setDmg(dmg);
        flame.setEnergyCost(energyCost);
        flame.setPositionSupplier(() -> {
            IVector basePosition = controller.getShip().getPosition();
            return Factories.getVectorFactory().create(basePosition.getX(), basePosition.getY() + yMov);
        });
        flame.setActivityPredicate(controller::isContinuousFireTriggered);
        flame.setRadius(Consts.Weapons.FLAME_RADIUS);
        flame.setSound(Sounds.FLAME);
        flame.setPlayersAttack(controller.isPlayer());

        return flame;
    }

    float getDmg() {

        return dmg;
    }

    @Override
    public boolean isContinuousFire() {

        return true;
    }
}
