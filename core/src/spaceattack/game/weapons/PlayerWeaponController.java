package spaceattack.game.weapons;

import spaceattack.consts.Consts;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.utils.vector.IVector;

public class PlayerWeaponController extends AbstractWeaponController {

    @SuppressWarnings("unused")
    private IFireButton primaryFireButton;
    private IFireButton secondaryFireButton;

    public void setPrimaryFireButton(final IFireButton button) {

        primaryFireButton = button;
    }

    public void setSecondaryFireButton(final IFireButton button) {

        secondaryFireButton = button;
    }

    @Override
    public IVector getWeaponMovement() {

        return Factories.getVectorFactory().create(0, 1);
    }

    @Override
    public void updateSecondaryWeapon(final IWeapon weapon) {

        super.updateSecondaryWeapon(weapon);
        secondaryFireButton.setWeapon(weapon);
    }

    @Override
    public IVector getPrimaryWeaponUsePlacement() {

        return Factories.getVectorFactory().create(ship.getX(),
                ship.getY() + ship.getHeight() * primaryWeapon.getWeaponsMovementFactor());
    }

    @Override
    public IVector getSecondaryWeaponUsePlacement() {

        return Factories.getVectorFactory().create(ship.getX(),
                ship.getY() + ship.getHeight() * secondaryWeapon.getWeaponsMovementFactor());
    }

    @Override
    public void performAttack(final PossibleAttacks possibleAttack, final RadarVisible target) {

        passiveWeapons.forEach(weapon -> weapon.use());
    }

    @Override
    public boolean isPlayer() {

        return true;
    }

    @Override
    public IVector getTargetedWeaponMovement() {

        return getWeaponMovement();
    }

    @Override
    public boolean isContinuousFireTriggered(final float energyCost) {

        return secondaryFireButton.isPressed() && super.isContinuousFireTriggered(energyCost);
    }

    @Override
    public float getDamageFactor() {

        float factor = 1;

        if (ship.hpBelowHalf()) {
            factor += ship.getImprovements().get(Improvement.ADRENALINE) * Consts.Weapons.DAMAGE_MASTERY_FACTOR;
        }
        if (ship.getBerserkerLevel() > 0) {
            factor += ship.getBerserkerLevel() * ship.getImprovements().get(Improvement.BERSERKER) * 0.5f
                    * Consts.Weapons.DAMAGE_MASTERY_FACTOR;
        }
        return factor;
    }

    @Override
    public float getMissilesSpeedFactor() {

        float factor = 1;

        if (ship.getBerserkerLevel() > 0) {
            factor += 1 + (ship.getBerserkerLevel() - 1) * ship.getImprovements().get(Improvement.BERSERKER) * 0.1f;
        }
        return factor;
    }
}
