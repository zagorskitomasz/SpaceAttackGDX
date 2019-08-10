package spaceattack.game.engines;

import spaceattack.game.buttons.IAccelerator;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.enemy.IEnemyShip;

public enum ShipEngineBuilder {
    INSTANCE;

    public IEngine createDestinationEngine(final IShip ship) {

        IEngine engine = new DestinationShipEngine(ship, Factories.getUtilsFactory().create(),
                ship.getAttributes().get(Attribute.ENGINE));

        return engine;
    }

    public IEngine createInputEngine(final IShip ship, final IAccelerator accelerator) {

        InputShipEngine engine = new InputShipEngine(ship, ship.getAttributes().get(Attribute.ENGINE));
        engine.setAccelerator(accelerator);

        return engine;
    }

    public IEngine createFighterEngine(final IEnemyShip fighter) {

        IEngine engine = new FighterEngine(fighter, Factories.getUtilsFactory().create(),
                fighter.getAttributes().get(Attribute.ENGINE));

        return engine;
    }

    public IEngine createFastDestinationEngine(final IShip ship) {

        IEngine engine = new FastDestinationShipEngine(ship, Factories.getUtilsFactory().create(),
                ship.getAttributes().get(Attribute.ENGINE));

        return engine;
    }
}
