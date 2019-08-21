package spaceattack.game.ships.intro;

import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.enemy.boss.BossShip;

public class Destroyer extends BossShip {

    private Earth earth;

    public Destroyer(final Attributes attributes) {

        super(attributes);
    }

    public void setEarth(final Earth earth) {

        this.earth = earth;
    }

    @Override
    public void act(final float delta) {

        super.act(delta);

        if (earth != null && earth.getY() > this.getY()) {
            earth.burn();
        }
    }
}
