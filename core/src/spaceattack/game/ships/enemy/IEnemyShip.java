package spaceattack.game.ships.enemy;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.ships.IShip;
import spaceattack.game.weapons.IWeaponController;

public interface IEnemyShip extends IShip {

    void setPlayerShip(RadarVisible playerShip);

    void setMover(MoverAI mover);

    void setShooter(ShooterAI shooter);

    IWeaponController getWeaponController();

    boolean isMoving();

    void setWeaponController(IWeaponController controller);

    MoverType getMoverType();

    void setBar(EnemyBar bar);

    void setPowerUp(IPowerUp powerUp);
}
