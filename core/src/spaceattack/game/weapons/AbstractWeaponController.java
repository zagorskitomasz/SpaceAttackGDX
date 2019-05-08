package spaceattack.game.weapons;

import spaceattack.consts.Consts;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVector;

public abstract class AbstractWeaponController implements IWeaponController {

    protected IShip ship;

    protected IWeapon primaryWeapon;
    protected IWeapon secondaryWeapon;

    protected boolean frozen;

    @Override
    public void setShip(final IShip ship) {

        this.ship = ship;
    }

    @Override
    public void setPrimaryWeapon(final IWeapon weapon) {

        primaryWeapon = weapon;
    }

    @Override
    public void setSecondaryWeapon(final IWeapon weapon) {

        secondaryWeapon = weapon;
    }

    @Override
    public boolean takeEnergy(final float energyCost) {

        return ship.takeEnergy(energyCost);
    }

    @Override
    public void updateSecondaryWeapon(final IWeapon weapon) {

        secondaryWeapon = weapon;
    }

    @Override
    public float getPrimaryWeaponRadius() {

        return primaryWeapon.getCollisionRadius();
    }

    @Override
    public float getSecondaryWeaponRadius() {

        return secondaryWeapon.getCollisionRadius();
    }

    @Override
    public float getShipsWidth() {

        return ship.getWidth();
    }

    @Override
    public float getShipsHeight() {

        return ship.getHeight();
    }

    @Override
    public IVector getTargetCoords() {

        return null;
    }

    @Override
    public IShip getShip() {

        return ship;
    }

    @Override
    public boolean isContinuousFireTriggered(final float energyCost) {

        return ship.takeEnergy(energyCost / Consts.Metagame.FPS);
    }

    @Override
    public void freeze() {

        frozen = true;
    }

    @Override
    public void unfreeze() {

        frozen = false;
    }
}
