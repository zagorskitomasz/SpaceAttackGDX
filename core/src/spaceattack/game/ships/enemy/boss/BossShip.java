package spaceattack.game.ships.enemy.boss;

import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.BaseEnemyShip;

public class BossShip extends BaseEnemyShip implements IBoss {

    public BossShip(final Attributes attributes) {

        super(attributes, 0);
    }

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
    public void setDefaultMoverType(final MoverType type) {

        moverType = type;
    }

    @Override
    public void setDefaultShooterType(final ShooterType type) {

        shooterType = type;
    }
}
