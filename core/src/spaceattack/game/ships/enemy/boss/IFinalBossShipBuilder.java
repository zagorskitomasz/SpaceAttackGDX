package spaceattack.game.ships.enemy.boss;

import spaceattack.game.ships.IBoss;
import spaceattack.game.stages.impl.GameplayStage;

public interface IFinalBossShipBuilder {

    IBoss createSpaceStationI(GameplayStage stage);

    IBoss createHelperI(GameplayStage stage);

}
