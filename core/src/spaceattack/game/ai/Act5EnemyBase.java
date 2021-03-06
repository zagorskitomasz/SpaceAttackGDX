package spaceattack.game.ai;

import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;

public class Act5EnemyBase extends EnemyBase {

    public Act5EnemyBase(final IUtils utils) {

        super(utils);
        setAct(Acts.V);
    }

    @Override
    protected ShooterAI createTankShooter(final IEnemyShip tank) {

        return ShooterType.INSTANT_SHOOTER.create();
    }

    @Override
    protected ShooterAI createChaserShooter() {

        return ShooterType.INSTANT_SHOOTER.create();
    }

    @Override
    protected ShooterAI createFighterShooter() {

        return ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER.create();
    }
}
