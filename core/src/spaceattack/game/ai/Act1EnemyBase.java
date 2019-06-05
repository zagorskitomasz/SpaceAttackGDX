package spaceattack.game.ai;

import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act1EnemyBase extends EnemyBase {

    public Act1EnemyBase(IUtils utils) {

        super(utils);
        setAct(Acts.I);
    }

    @Override
    protected ShooterAI createTankShooter(IEnemyShip tank) {

        return ShooterType.DIRECT_SHOOTER.create();
    }

    @Override
    protected IPowerUp chooseWeaponPowerUp() {

        return PowerUpBuilder.INSTANCE.rocketMissileHolder(controller, fireButton, stage);
    }
}
