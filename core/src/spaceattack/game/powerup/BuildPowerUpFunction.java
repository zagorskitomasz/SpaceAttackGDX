package spaceattack.game.powerup;

import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.weapons.IWeaponController;

@FunctionalInterface
public interface BuildPowerUpFunction {

    IPowerUp build(IWeaponController controller, ComplexFireButton fireButton, GameplayStage stage);
}
