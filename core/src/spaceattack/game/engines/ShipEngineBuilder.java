package spaceattack.game.engines;

import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.enemy.IEnemyShip;

public enum ShipEngineBuilder
{
	INSTANCE;

	public IEngine createPlayersEngine(IShip ship)
	{
		IEngine engine = new ShipEngine(ship, Factories.getUtilsFactory().create());

		engine.setBaseSpeed(1f);
		engine.setAcceleration(1f);
		engine.setBraking(1f);
		engine.setAgility(2f);

		return engine;
	}

	public IEngine createFighterEngine(IEnemyShip fighter)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
