package spaceattack.game.stages.impl;

import spaceattack.game.ai.Act4EnemyBase;
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

public abstract class Act4StageBuilder extends GameplayStageBuilder {

    @Override
    protected Acts getAct() {

        return Acts.IV;
    }

    @Override
    protected EnemyBase createEnemyBase(final IUtils utils) {

        return new Act4EnemyBase(utils);
    }

    @Override
    protected void buildShip() {

        Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();
        Burner burner = BurnerBuilder.INSTANCE.build(playersShip);

        playersShip.setActor(Factories.getActorFactory().create(playersShip));
        playersShip.loadComplexGraphics(Textures.PLAYER_SHIP4_F.getTexture(), Textures.PLAYER_SHIP4_R.getTexture(),
                Textures.PLAYER_SHIP4_L.getTexture());
        playersShip.setShipEngine(engine);
        playersShip.addWeapon(primaryWeapon);
        playersShip.addWeapon(greenLaser);
        playersShip.setEnergyPool(energyPool);
        playersShip.setHpPool(hpPool);
        playersShip.setMissilesLauncher(missilesLauncher);
        playersShip.setExplosion(explosion);
        playersShip.setBurner(burner);
    }

    @Override
    protected IWeapon createSecondaryWeapon(final int armory) {

        return WeaponsFactory.INSTANCE.createTripleGreenLaser(weaponController, missilesLauncher, armory);
    }
}
