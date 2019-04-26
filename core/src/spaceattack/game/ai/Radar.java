package spaceattack.game.ai;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.player.PlayerShip;

public class Radar {

    private RadarVisible playersShip;
    private List<IEnemyShip> enemyShips;

    private List<IGameActor> actors;

    public Radar(List<IGameActor> actors) {

        this.actors = actors;
    }

    public RadarVisible getPlayerShip() {

        return playersShip;
    }

    public List<IEnemyShip> getEnemyShips() {

        return enemyShips;
    }

    public void update() {

        findPlayerShip();
        findEnemyShips();
    }

    private void findPlayerShip() {

        // @formatter:off
        Optional<RadarVisible> ship = actors
                .parallelStream()
                .filter(actor -> actor instanceof PlayerShip)
                .map(actor -> (RadarVisible) actor)
                .findFirst();
        // @formatter:on

        playersShip = ship.isPresent() ? ship.get() : null;
    }

    private void findEnemyShips() {

        // @formatter:off
        enemyShips = actors
                .parallelStream()
                .filter(actor -> actor instanceof IEnemyShip)
                .map(actor -> (IEnemyShip) actor)
                .collect(Collectors.toList());
        // @formatter:on
    }
}
