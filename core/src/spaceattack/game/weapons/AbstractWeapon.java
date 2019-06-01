package spaceattack.game.weapons;

import spaceattack.consts.Consts;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.weapons.missiles.Missile;

public abstract class AbstractWeapon implements IWeapon {

    protected IUtils utils;
    protected FrameController frameController;
    protected IWeaponController controller;
    protected MissilesLauncher launcher;

    protected float dmg;
    protected float speed;
    protected float energyCost;

    private boolean noEnergyCost;

    protected abstract Missile buildMissile();

    public void setUtils(final IUtils utils) {

        this.utils = utils;
        frameController = new FrameController(utils, Consts.Weapons.LASER_ATTACKS_PER_SECOND);
    }

    public void setController(final IWeaponController weaponController) {

        this.controller = weaponController;
    }

    public void setMissilesLauncher(final MissilesLauncher missilesLauncher) {

        launcher = missilesLauncher;
    }

    @Override
    public boolean use() {

        if (!frameController.check()) {
            return false;
        }
        if (!controller.takeEnergy(getShotCost())) {
            return false;
        }
        Missile missile = buildMissile();
        if (missile != null) {
            launcher.launch(missile);
        }
        return true;
    }

    protected float getShotCost() {

        if (noEnergyCost) {
            return 0;
        }

        return energyCost;
    }

    @Override
    public float getEnergyCost() {

        if (noEnergyCost) {
            return 0;
        }

        return energyCost;
    }

    protected IVector calculateTargetedShotPosition(final float distanceFromShip, final float gunMovX,
            final float gunMovY) {

        IVector shipCoords = controller.getPrimaryWeaponUsePlacement();
        IVector movement = controller.getTargetedWeaponMovement();
        float xDirection = movement.getX();
        float yDirection = movement.getY();

        float x;
        float y;

        if (xDirection < 0) {
            x = shipCoords.getX() - controller.getShipsWidth() * distanceFromShip;
        }
        else
            if (xDirection > 0) {
                x = shipCoords.getX() + controller.getShipsWidth() * distanceFromShip;
            }
            else {
                x = shipCoords.getX();
            }

        if (yDirection < 0) {
            y = shipCoords.getY() - controller.getShipsHeight() * distanceFromShip;
        }
        else
            if (yDirection > 0) {
                y = shipCoords.getY() + controller.getShipsHeight() * distanceFromShip;
            }
            else {
                y = shipCoords.getY();
            }

        if (gunMovX != 0 || gunMovY != 0) {
            return Factories.getVectorFactory().create(x + gunMovX * controller.getShip().getRadius(),
                    y + gunMovY * controller.getShip().getRadius());
        }
        return Factories.getVectorFactory().create(x, y);
    }

    @Override
    public void setNoEnergyCost() {

        noEnergyCost = true;
    }

    @Override
    public void setInterval(final float checksPerSecond) {

        frameController.reset(checksPerSecond);
    }
}
