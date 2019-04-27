package spaceattack.game.ships.enemy.boss;

import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.BaseEnemyShip;

public class BossShip extends BaseEnemyShip implements RequiredOnStage, IBoss {

    private MoverType moverType;
    private ShooterType shooterType;

    @Override
    public MoverType getDefaultMoverType() {

        return moverType;
    }

    @Override
    public ShooterType getDefaultShooterType() {

        return shooterType;
    }

    @Override
    public void setDefaultMoverType(MoverType type) {

        moverType = type;
    }

    @Override
    public void setDefaultShooterType(ShooterType type) {

        shooterType = type;
    }
}
