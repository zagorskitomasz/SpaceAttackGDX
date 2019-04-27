package spaceattack.game.ai.movers;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public abstract class SideChaser extends AbstractMover {

    protected float margin;

    @Override
    public void setPlayerShip(RadarVisible ship) {

        super.setPlayerShip(ship);
        if (playerShip != null)
            margin = playerShip.getRadius() * 2;
    }

    protected boolean isInRadius(IVector destination) {

        return NumbersUtils.distance(owner.getPosition(), destination) < playerShip.getRadius();
    }
}
