package spaceattack.game.ships.enemy;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.IShip;

public class BigEnemyBar extends EnemyBar {

    public BigEnemyBar(IShip ship) {

        super(ship);
    }

    @Override
    public void draw(IBatch batch) {

        background.setX(ship.getX() - ship.getWidth() * 0.6f);
        background.setY(ship.getY() + ship.getHeight() * 0.2f);
        background.setWidth(ship.getWidth() * 1.2f);
        background.setHeight(Sizes.ENEMY_BAR_WIDTH * 2f + 4);

        bar.setX(ship.getX() - ship.getWidth() * 0.6f + 2);
        bar.setY(ship.getY() + ship.getHeight() * 0.2f + 2);
        bar.setWidth((ship.getWidth() * 1.2f - 4) * ship.getHpPool().getAmount() / ship.getHpPool().getMaxAmount());
        bar.setHeight(Sizes.ENEMY_BAR_WIDTH * 2f);

        batch.rect(background, bar);
    }
}
