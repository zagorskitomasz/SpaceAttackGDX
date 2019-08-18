package spaceattack.game.ships.player;

import java.util.HashMap;
import java.util.Map;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.PowerUpConsumer;
import spaceattack.game.actors.interfaces.RequiredOnStage;
import spaceattack.game.factories.Factories;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.rpg.Improvements;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.Ship;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.ITexture;

public class PlayerShip extends Ship implements RequiredOnStage, PowerUpConsumer {

    private Map<IShip.Turn, ITexture> textures;
    private Attributes attributes;
    private Improvements improvements;

    private int berserkerLevel;
    private FrameController berserkerChecker;

    @Override
    public void setActor(final IActor actor) {

        super.setActor(actor);
        getActor().setPosition(Sizes.GAME_WIDTH * 0.5f, Sizes.GAME_HEIGHT * 0.4f);
    }

    public void loadComplexGraphics(final ITexture iTexture, final ITexture iTexture2, final ITexture iTexture3) {

        textures = new HashMap<>();
        textures.put(IShip.Turn.FRONT, iTexture);
        textures.put(IShip.Turn.LEFT, iTexture3);
        textures.put(IShip.Turn.RIGHT, iTexture2);

        currentTexture = textures.get(IShip.Turn.FRONT);
    }

    @Override
    protected void fly() {

        Turn turn = engine.fly();

        if (textures != null) {
            currentTexture = textures.get(turn);
        }
    }

    @Override
    public void consume(final IPowerUp powerUp) {

        powerUp.consumed();
    }

    @Override
    public Attributes getAttributes() {

        return attributes;
    }

    public void setAttributes(final Attributes attributes) {

        this.attributes = attributes;
    }

    @Override
    public Improvements getImprovements() {

        return improvements;
    }

    public void setImprovements(final Improvements improvements) {

        this.improvements = improvements;
    }

    @Override
    public float getAdditionalSpeedFactor() {

        float factor = 1;

        if (hpBelowHalf()) {
            factor += improvements.get(Improvement.ADRENALINE) * Consts.Pools.SPEED_FACTOR;
        }
        if (berserkerLevel > 0) {
            factor += berserkerLevel * 2 * Consts.Pools.SPEED_FACTOR;
        }
        else
            if (improvements.get(Improvement.BERSERKER) > 0) {
                factor /= 2;
            }

        return factor;
    }

    public void gainBerserk() {

        if (improvements.get(Improvement.BERSERKER) <= 0) {
            return;
        }

        if (berserkerChecker == null) {
            berserkerChecker = new FrameController(Factories.getUtilsFactory().create(), 1);
        }
        berserkerChecker.reset(berserkerChecksPerSecond());
        berserkerLevel = Math.min(3, berserkerLevel + 1);
    }

    private float berserkerChecksPerSecond() {

        return 0.5f - improvements.get(Improvement.BERSERKER) * 0.025f;
    }

    @Override
    public int getBerserkerLevel() {

        return berserkerLevel;
    }

    @Override
    public void act(final float delta) {

        super.act(delta);
        if (berserkerChecker != null && berserkerChecker.check()) {
            berserkerLevel = 0;
        }
    }
}
