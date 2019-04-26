package spaceattack.game.stages.impl;

import spaceattack.game.ai.Act1EnemyBase;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.Acts;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.WeaponsFactory;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;

public abstract class Act1StageBuilder extends GameplayStageBuilder {

    @Override
    protected Acts getAct() {

        return Acts.I;
    }

    @Override
    protected EnemyBase createEnemyBase(IUtils utils) {

        return new Act1EnemyBase(utils);
    }

    @Override
    protected void buildShip() {

        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();
        Burner burner = BurnerBuilder.INSTANCE.build(playersShip);

        gameProgress.registerObserver(playersShip);
        playersShip.notify(gameProgress);
        playersShip.setActor(Factories.getActorFactory().create(playersShip));
        playersShip.loadComplexGraphics(Textures.PLAYER_SHIP1_F.getTexture(), Textures.PLAYER_SHIP1_R.getTexture(),
                Textures.PLAYER_SHIP1_L.getTexture());
        playersShip.setShipEngine(engine);
        playersShip.addWeapon(primaryWeapon);
        playersShip.addWeapon(greenLaser);
        playersShip.setEnergyPool(energyPool);
        playersShip.setHpPool(hpPool);
        playersShip.setLevel(gameProgress.getLevel());
        playersShip.setMissilesLauncher(missilesLauncher);
        playersShip.setExplosion(explosion);
        playersShip.setBurner(burner);
    }

    @Override
    public IWeapon createPrimaryWeapon() {

        return WeaponsFactory.INSTANCE.createRedLaser(weaponController, missilesLauncher);
    }
}
