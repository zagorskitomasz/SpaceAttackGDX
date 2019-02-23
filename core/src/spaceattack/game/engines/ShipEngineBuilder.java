package spaceattack.game.engines;

import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.enemy.IEnemyShip;

public enum ShipEngineBuilder
{
	INSTANCE;

	public IEngine createPlayersEngine(IShip ship)
	{
		IEngine engine = new ShipEngine(ship, Factories.getUtilsFactory().create());

		engine.setBaseSpeed(1f * Sizes.RADIUS_FACTOR);
		engine.setAcceleration(1f * Sizes.RADIUS_FACTOR);
		engine.setBraking(1f * Sizes.RADIUS_FACTOR);
		engine.setAgility(2f * Sizes.RADIUS_FACTOR);

		return engine;
	}

	public IEngine createFighterEngine(IEnemyShip fighter)
	{
		IEngine engine = new FighterEngine(fighter, Factories.getUtilsFactory().create());

		engine.setBaseSpeed((0.3f + (float) Math.random() * 1f)  * Sizes.RADIUS_FACTOR);
		engine.setAcceleration(1f * Sizes.RADIUS_FACTOR);
		engine.setBraking(1f * Sizes.RADIUS_FACTOR);
		engine.setAgility(1f * Sizes.RADIUS_FACTOR);

		return engine;
	}
}
