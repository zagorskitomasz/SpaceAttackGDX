package spaceattack.game.ai;

import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act2EnemyBase extends EnemyBase {

    public Act2EnemyBase(final IUtils utils) {

        super(utils);
        setAct(Acts.II);
    }

    @Override
    protected ShooterAI createTankShooter(final IEnemyShip tank) {

        return ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER.create();
    }

    @Override
    protected IPowerUp chooseWeaponPowerUp() {

        return powerUpBuilder.mineHolder(controller, fireButton, stage);
    }
}
