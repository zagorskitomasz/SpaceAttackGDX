package spaceattack.game.engines;

import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;

public enum ShipEngineBuilder
{
	INSTANCE;

	public IEngine createPlayersEngine(IShip ship)
	{
		ShipEngine engine = new ShipEngine(ship, Factories.getUtilsFactory().create());

		engine.setBaseSpeed(1f);
		engine.setAcceleration(1f);
		engine.setBraking(1f);
		engine.setAgility(2f);

		return engine;
	}
}
