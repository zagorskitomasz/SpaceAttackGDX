package spaceattack.game.ships.intro;

import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.Ship;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Textures;

public class EscapingPlayer extends Ship {

    private FrameController waiter;
    private boolean escaping;

    private static final float SCALE_STEP = 0.005f;
    private static final float ANGLE_STEP = 36;

    private float scale;
    private float angle;

    public EscapingPlayer() {

        setTexture(Textures.PLAYER_SHIP1_F.getTexture());
    }

    @Override
    public void act(final float delta) {

        super.act(delta);

        if (waiter != null && waiter.check()) {
            escaping = true;
        }
        if (escaping) {
            scale += SCALE_STEP;
            angle += ANGLE_STEP;
        }
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        if (escaping) {
            batch.draw(getTexture(), getDrawingX(), getDrawingY(),
                    getTexture().getWidth() * 0.5f,
                    getTexture().getHeight() * 0.5f, getTexture().getWidth(),
                    getTexture().getHeight(), scale, scale, angle);
        }
    }

    @Override
    public Attributes getAttributes() {

        return null;
    }

    public void escape() {

        this.waiter = new FrameController(Factories.getUtilsFactory().create(), 1);
    }
}
