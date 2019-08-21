package spaceattack.game.ships.intro;

import java.util.List;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.Ship;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.missiles.Explosion;

public class Earth extends Ship {

    private final ITexture burnedTexture;
    private boolean burned;
    private final FrameController explodingChecker;
    private List<Explosion> explosions;

    public Earth(final FrameController explodingChecker) {

        setTexture(Textures.EARTH_BF.getTexture());
        burnedTexture = Textures.EARTH_AF.getTexture();
        this.explodingChecker = explodingChecker;
    }

    @Override
    public Attributes getAttributes() {

        return null;
    }

    public void setExplosions(final List<Explosion> explosions) {

        this.explosions = explosions;
    }

    public void burn() {

        setTexture(burnedTexture);
        burned = true;
        explodingChecker.reset(3f);
    }

    @Override
    public void act(final float delta) {

        if (burned && explodingChecker.check()) {
            explode();
            setX(Sizes.GAME_WIDTH * 2);
        }
    }

    @Override
    public void explode() {

        if (exploded || launcher == null || explosions.size() < 5) {
            return;
        }

        exploded = true;
        IActor actor = getActor();

        explosions.get(0).getActor().setPosition(actor.getX(), actor.getY());
        launcher.launch(explosions.get(0));

        explosions.get(1).getActor().setPosition(actor.getX() - burnedTexture.getWidth() * 0.3f, actor.getY());
        launcher.launch(explosions.get(1));

        explosions.get(2).getActor().setPosition(actor.getX() + burnedTexture.getWidth() * 0.3f, actor.getY());
        launcher.launch(explosions.get(2));

        explosions.get(3).getActor().setPosition(actor.getX(), actor.getY() - burnedTexture.getHeight() * 0.3f);
        launcher.launch(explosions.get(3));

        explosions.get(4).getActor().setPosition(actor.getX(), actor.getY() + burnedTexture.getHeight() * 0.3f);
        launcher.launch(explosions.get(4));
    }
}
