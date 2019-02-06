package spaceattack.game.ships.enemy;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.Rect;

public class EnemyBar
{
	private IShip ship;
	private Rect background;
	private Rect bar;

	public EnemyBar(IShip ship)
	{
		this.ship = ship;

		background = new Rect();
		background.setRed(0.1f);
		background.setGreen(0.1f);
		background.setBlue(0.1f);

		bar = new Rect();
		bar.setRed(0.6f);
		bar.setGreen(0.1f);
		bar.setBlue(0.1f);
	}

	public void draw(IBatch batch)
	{
		background.setX(ship.getX() - ship.getWidth() * 0.4f);
		background.setY(ship.getY() + ship.getHeight() * 0.2f);
		background.setWidth(ship.getWidth() * 0.8f);
		background.setHeight(Sizes.ENEMY_BAR_WIDTH + 4);

		bar.setX(ship.getX() - ship.getWidth() * 0.4f + 2);
		bar.setY(ship.getY() + ship.getHeight() * 0.2f + 2);
		bar.setWidth((ship.getWidth() * 0.8f - 4) * ship.getHpPool().getAmount() / ship.getHpPool().getMaxAmount());
		bar.setHeight(Sizes.ENEMY_BAR_WIDTH);

		batch.rect(background, bar);
	}
}
