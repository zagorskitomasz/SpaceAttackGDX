package spaceattack.game.weapons;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVector;

public interface IWeaponController {

    void setShip(IShip ship);

    void setPrimaryWeapon(IWeapon weapon);

    void setSecondaryWeapon(IWeapon weapon);

    IVector getPrimaryWeaponUsePlacement();

    IVector getSecondaryWeaponUsePlacement();

    IVector getWeaponMovement();

    boolean takeEnergy(float energyCost);

    void updateSecondaryWeapon(IWeapon weapon);

    float getPrimaryWeaponRadius();

    float getSecondaryWeaponRadius();

    void performAttack(PossibleAttacks possibleAttack, RadarVisible target);

    boolean isPlayer();

    IVector getTargetedWeaponMovement();

    float getShipsWidth();

    float getShipsHeight();
}
