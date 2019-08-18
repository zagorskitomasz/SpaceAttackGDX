package spaceattack.game.ships.enemy;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.batch.IBatch;
import spaceattack.game.factories.Factories;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.rpg.Attributes;
import spaceattack.game.ships.Ship;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.missiles.Freezer;

public class BaseEnemyShip extends Ship implements IEnemyShip {

    private RadarVisible playerShip;

    private IPowerUp powerUp;
    private MoverAI mover;
    private ShooterAI shooter;
    private IWeaponController controller;

    private EnemyBar bar;
    private final Attributes attributes;
    private float fearFactor;
    private boolean feared;
    private final ITexture fearEffect = Textures.FEAR.getTexture();

    public BaseEnemyShip(final Attributes attributes, final float fear) {

        this.attributes = attributes;
        initFear(fear);
    }

    protected void initFear(final float fear) {

        if (fear > 0) {
            fearFactor = Consts.Weapons.FEAR_BASE + Consts.Weapons.FEAR_PER_IMP * (fear - 1);
        }
    }

    @Override
    public void act(final float delta) {

        super.act(delta);
        mover.updateDirection();
        performAttack();
        disappearIfNeeded();
    }

    void performAttack() {

        PossibleAttacks possibleAttack = shooter.checkShot();
        if (!PossibleAttacks.NONE.equals(possibleAttack)) {
            controller.performAttack(possibleAttack, playerShip);
        }
    }

    @Override
    public void setPlayerShip(final RadarVisible playerShip) {

        this.playerShip = playerShip;
    }

    @Override
    public void setMover(final MoverAI mover) {

        this.mover = mover;
    }

    @Override
    public void setShooter(final ShooterAI shooter) {

        this.shooter = shooter;
    }

    @Override
    public IWeaponController getWeaponController() {

        return controller;
    }

    @Override
    public boolean isMoving() {

        return !engine.isDestinationReached();
    }

    @Override
    public void setWeaponController(final IWeaponController controller) {

        this.controller = controller;
    }

    @Override
    public void setActor(final IActor actor) {

        super.setActor(actor);
        getActor().setPosition((float) (Math.random() * Sizes.GAME_WIDTH), Sizes.GAME_HEIGHT);
    }

    @Override
    public void setBar(final EnemyBar bar) {

        this.bar = bar;
    }

    @Override
    public MoverType getMoverType() {

        return mover.getType();
    }

    @Override
    public void draw(final IBatch batch, final float alpha) {

        super.draw(batch, alpha);

        if (feared) {
            batch.draw(fearEffect, getX() - fearEffect.getWidth() / 2,
                    getY() - fearEffect.getHeight() / 2);
        }

        if (bar != null) {
            bar.draw(batch);
        }
    }

    @Override
    public void setPowerUp(final IPowerUp powerUp) {

        this.powerUp = powerUp;
    }

    @Override
    public void setToKill() {

        super.setToKill();

        if (powerUp != null) {
            powerUp.setX(getX());
            powerUp.setY(getY());

            launcher.launch(powerUp);
        }
    }

    @Override
    public boolean isToKill() {

        return super.isToKill();
    }

    @Override
    public void freeze(final Freezer freezer) {

        super.freeze(freezer);
        controller.freeze();
    }

    @Override
    public void unfreeze() {

        controller.unfreeze();
        super.unfreeze();
    }

    @Override
    public Attributes getAttributes() {

        return attributes;
    }

    @Override
    public void takeDmg(final float dmg) {

        super.takeDmg(dmg);
        fear();
    }

    private void fear() {

        if (feared()) {
            feared = true;
            escape();
        }
    }

    private boolean feared() {

        return fearFactor > Math.random();
    }

    private void escape() {

        forceDestination(Factories.getVectorFactory().create(getX(), Sizes.GAME_HEIGHT * 2));
    }
}
