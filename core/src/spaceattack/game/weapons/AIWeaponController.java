package spaceattack.game.weapons;

import spaceattack.consts.Consts;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class AIWeaponController extends AbstractWeaponController {

    private RadarVisible target;
    private final IVectorFactory vectors;

    private final FrameController attacksInterval;
    private boolean shot;

    public AIWeaponController() {

        vectors = Factories.getVectorFactory();
        attacksInterval = new FrameController(Factories.getUtilsFactory().create(), 1);
    }

    @Override
    public IVector getWeaponMovement() {

        return Factories.getVectorFactory().create(0, -1);
    }

    @Override
    public IVector getPrimaryWeaponUsePlacement() {

        return Factories.getVectorFactory().create(ship.getX(),
                ship.getY() - ship.getHeight() * primaryWeapon.getWeaponsMovementFactor());
    }

    @Override
    public IVector getSecondaryWeaponUsePlacement() {

        return Factories.getVectorFactory().create(ship.getX(),
                ship.getY() - ship.getHeight() * secondaryWeapon.getWeaponsMovementFactor());
    }

    @Override
    public void performAttack(final PossibleAttacks possibleAttack, final RadarVisible target) {

        if (shot && !attacksInterval.check()) {
            return;
        }

        switch (possibleAttack) {
        case BOTH:
            if (!tryUse(secondaryWeapon)) {
                tryUse(primaryWeapon);
            }
            break;
        case PRIMARY:
            tryUse(primaryWeapon);
            break;
        case SECONDARY:
            tryUse(secondaryWeapon);
            break;
        default:
            break;
        }
        this.target = target;
    }

    private boolean tryUse(final IWeapon weapon) {

        shot = weapon.use();

        if (shot) {
            attacksInterval.reset(Consts.Weapons.LASER_ATTACKS_PER_SECOND);
        }

        return shot;
    }

    @Override
    public boolean isPlayer() {

        return false;
    }

    @Override
    public IVector getTargetedWeaponMovement() {

        if (target == null) {
            return vectors.create(0, -1);
        }

        float xDiff = target.getX() - ship.getX();
        float yDiff = target.getY() - ship.getY();

        if (xDiff <= 0 && yDiff <= 0) {
            if (xDiff == 0 || yDiff / xDiff > 2) {
                return vectors.create(0, -1);
            }
            else
                if (yDiff == 0 || xDiff / yDiff > 2) {
                    return vectors.create(-1, 0);
                }
                else {
                    return vectors.create(-0.7f, -0.7f);
                }
        }
        else
            if (xDiff <= 0 && yDiff >= 0) {
                if (xDiff == 0 || yDiff / xDiff < -2) {
                    return vectors.create(0, 1);
                }
                else
                    if (yDiff == 0 || xDiff / yDiff < -2) {
                        return vectors.create(-1, 0);
                    }
                    else {
                        return vectors.create(-0.7f, 0.7f);
                    }
            }
            else
                if (xDiff >= 0 && yDiff >= 0) {
                    if (xDiff == 0 || yDiff / xDiff > 2) {
                        return vectors.create(0, 1);
                    }
                    else
                        if (yDiff == 0 || xDiff / yDiff > 2) {
                            return vectors.create(1, 0);
                        }
                        else {
                            return vectors.create(0.7f, 0.7f);
                        }
                }
                else
                    if (xDiff >= 0 && yDiff <= 0) {
                        if (xDiff == 0 || yDiff / xDiff < -2) {
                            return vectors.create(0, -1);
                        }
                        else
                            if (yDiff == 0 || xDiff / yDiff < -2) {
                                return vectors.create(1, 0);
                            }
                            else {
                                return vectors.create(0.7f, -0.7f);
                            }
                    }
        return vectors.create(0, -1);
    }

    @Override
    public IVector getTargetCoords() {

        if (target != null) {
            return vectors.create(target.getX(), target.getY());
        }
        return null;
    }
}
