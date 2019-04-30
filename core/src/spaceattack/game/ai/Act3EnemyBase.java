package spaceattack.game.ai;

import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act3EnemyBase extends EnemyBase {

    public Act3EnemyBase(final IUtils utils) {

        super(utils);
        setAct(Acts.III);
    }

    @Override
    protected ShooterAI createTankShooter(final IEnemyShip tank) {

        return ShooterType.INSTANT_SHOOTER.create();
    }

    @Override
    protected IPowerUp choosePowerUp() {

        return PowerUpBuilder.INSTANCE.mineHolder(controller, fireButton, stage);
    }
}
