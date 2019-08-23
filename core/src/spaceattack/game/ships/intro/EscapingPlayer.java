package spaceattack.game.ships.intro;

import java.util.LinkedList;
import java.util.Queue;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.Ship;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class EscapingPlayer extends Ship {

    private FrameController waiter;
    private boolean escaping;

    private static final float SCALE_STEP = 0.005f;
    private float angleStep = 50;

    private float scale;
    private float angle;

    private Queue<IVector> destinations;
    private final Attributes attributes;
    private final Runnable finalizeAction;

    public EscapingPlayer(final Runnable finalizeAction) {

        attributes = Consts.AttributesStarters.FIGHTER.get(1);
        this.finalizeAction = finalizeAction;
        createDestinationQueue();
        setTexture(Textures.PLAYER_SHIP1_F.getTexture());
    }

    private void createDestinationQueue() {

        IVectorFactory vectors = Factories.getVectorFactory();
        destinations = new LinkedList<>();
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.3f, Sizes.GAME_HEIGHT * 0.7f));
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.6f, Sizes.GAME_HEIGHT * 0.6f));
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.5f));
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.9f, Sizes.GAME_HEIGHT * 0.2f));
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.5f, Sizes.GAME_HEIGHT * 0.4f));
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.5f, Sizes.GAME_HEIGHT * 0.3f));
        destinations.add(vectors.create(Sizes.GAME_WIDTH * 0.5f, Sizes.GAME_HEIGHT * 1.5f));
    }

    @Override
    public void act(final float delta) {

        super.act(delta);

        if (waiter != null && waiter.check()) {
            escaping = true;
        }
        if (escaping) {
            if (engine.isDestinationReached()) {
                if (destinations.size() == 0) {
                    finalizeAction.run();
                }
                engine.setDestination(destinations.poll());
            }
            scale = Math.min(scale + SCALE_STEP, 1);

            if (scale > 0.8) {
                angleStep = 10;
            }
            else
                if (scale > 0.6) {
                    angleStep = 20;
                }
                else
                    if (scale > 0.4) {
                        angleStep = 30;
                    }
                    else
                        if (scale > 0.2) {
                            angleStep = 40;
                        }

            if (scale > 0.95f && angle % 360 == 0) {
                angle = 0;
            }
            else {
                angle += angleStep;
            }
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

        return attributes;
    }

    public void escape() {

        this.waiter = new FrameController(Factories.getUtilsFactory().create(), 1);
    }
}
