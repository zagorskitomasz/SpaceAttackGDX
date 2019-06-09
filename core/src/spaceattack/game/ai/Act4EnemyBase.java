package spaceattack.game.ai;

import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act4EnemyBase extends EnemyBase {

    public Act4EnemyBase(final IUtils utils) {

        super(utils);
        setAct(Acts.IV);
    }

    @Override
    protected ShooterAI createTankShooter(final IEnemyShip tank) {

        return ShooterType.INSTANT_SHOOTER.create();
    }

    @Override
    protected IPowerUp chooseWeaponPowerUp() {

        return powerUpBuilder.waveHolder(controller, fireButton, stage);
    }
}
