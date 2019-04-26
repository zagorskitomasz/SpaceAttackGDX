package spaceattack.game.ships.player;

import java.util.HashMap;
import java.util.Map;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.PowerUpConsumer;
import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.Ship;
import spaceattack.game.system.graphics.ITexture;

public class PlayerShip extends Ship implements RequiredOnStage, PowerUpConsumer {

    private Map<IShip.Turn, ITexture> textures;

    @Override
    public void setActor(IActor actor) {

        super.setActor(actor);
        getActor().setPosition(Sizes.GAME_WIDTH * 0.5f, Sizes.GAME_HEIGHT * 0.4f);
    }

    public void loadComplexGraphics(ITexture iTexture, ITexture iTexture2, ITexture iTexture3) {

        textures = new HashMap<>();
        textures.put(IShip.Turn.FRONT, iTexture);
        textures.put(IShip.Turn.LEFT, iTexture3);
        textures.put(IShip.Turn.RIGHT, iTexture2);

        currentTexture = textures.get(IShip.Turn.FRONT);
    }

    @Override
    protected void fly() {

        Turn turn = engine.fly();

        if (textures != null)
            currentTexture = textures.get(turn);
    }

    @Override
    public void consume(IPowerUp powerUp) {

        powerUp.consumed();
    }
}
