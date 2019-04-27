package spaceattack.game.ai.shooters;

import java.util.List;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.utils.vector.IVectorFactory;
import spaceattack.game.weapons.IWeaponController;

public abstract class AbstractShooter implements ShooterAI {

    protected RadarVisible playerShip;
    protected IEnemyShip owner;
    protected IWeaponController controller;
    protected IVectorFactory vectors;

    protected boolean canPrimary;
    protected boolean canSecondary;

    protected List<? extends RadarVisible> friends;

    public AbstractShooter() {

        vectors = Factories.getVectorFactory();
    }

    @Override
    public void setPlayerShip(RadarVisible playerShip) {

        this.playerShip = playerShip;
    }

    @Override
    public void setOwner(IEnemyShip owner) {

        this.owner = owner;
        controller = owner.getWeaponController();
    }

    @Override
    public void setFriends(List<? extends RadarVisible> friends) {

        this.friends = friends;
    }

    protected PossibleAttacks processResult() {

        if (canPrimary && canSecondary)
            return PossibleAttacks.BOTH;

        if (canPrimary)
            return PossibleAttacks.PRIMARY;

        if (canSecondary)
            return PossibleAttacks.SECONDARY;

        return PossibleAttacks.NONE;
    }

    @Override
    public void notify(MoverAI state) {

        // do nothing
    }
}
