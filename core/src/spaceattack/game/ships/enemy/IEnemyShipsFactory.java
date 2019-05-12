package spaceattack.game.ships.enemy;

import spaceattack.game.ships.IBoss;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;

public interface IEnemyShipsFactory {

    IEnemyShip createFighter(Acts act, GameplayStage stage);

    IEnemyShip createChaser(Acts act, GameplayStage stage);

    IEnemyShip createTank(Acts act, GameplayStage stage);

    IEnemyShip createSuperTank(Acts act, GameplayStage stage);

    IBoss createMinorBoss(Acts act, GameplayStage stage);

    IEnemyShip createSuperChaser(Acts act, GameplayStage stage);
}
