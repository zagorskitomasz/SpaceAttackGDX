package spaceattack.game.weapons;

import spaceattack.game.ships.IShip;

public abstract class AbstractWeaponController implements IWeaponController {

    protected IShip ship;

    protected IWeapon primaryWeapon;
    protected IWeapon secondaryWeapon;

    @Override
    public void setShip(IShip ship) {

        this.ship = ship;
    }

    @Override
    public void setPrimaryWeapon(IWeapon weapon) {

        primaryWeapon = weapon;
    }

    @Override
    public void setSecondaryWeapon(IWeapon weapon) {

        secondaryWeapon = weapon;
    }

    @Override
    public boolean takeEnergy(float energyCost) {

        return ship.takeEnergy(energyCost);
    }

    @Override
    public void updateSecondaryWeapon(IWeapon weapon) {

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
}
