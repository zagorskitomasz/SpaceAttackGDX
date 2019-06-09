package spaceattack.game.powerup;

import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.weapons.IWeaponController;

public interface IPowerUpBuilder {

    IPowerUp hp(IPool hpPool);

    IPowerUp energy(IPool energyPool);

    IPowerUp rocketMissileHolder(IWeaponController controller, ComplexFireButton fireButton, GameplayStage stage);

    IPowerUp mineHolder(IWeaponController controller, ComplexFireButton fireButton, GameplayStage stage);

    IPowerUp shieldHolder(IWeaponController controller, ComplexFireButton fireButton, GameplayStage stage);

    IPowerUp waveHolder(IWeaponController controller, ComplexFireButton fireButton, GameplayStage stage);

    IPowerUp flameHolder(IWeaponController controller, ComplexFireButton fireButton, GameplayStage stage);

}
