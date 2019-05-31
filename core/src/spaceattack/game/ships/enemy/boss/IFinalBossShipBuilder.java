package spaceattack.game.ships.enemy.boss;

import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.stages.impl.GameplayStage;

public interface IFinalBossShipBuilder {

    IBoss createSpaceStationI(GameplayStage stage);

    IEnemyShip createWeaponHolder(GameplayStage stage);

}
