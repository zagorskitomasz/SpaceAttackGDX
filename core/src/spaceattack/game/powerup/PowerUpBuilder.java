package spaceattack.game.powerup;

import spaceattack.consts.Consts;
import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.WeaponsFactory;

public enum PowerUpBuilder implements IPowerUpBuilder {
    INSTANCE;

    public IPowerUp hp(final IPool hpPool) {

        PoolIncreaser increaser = new PoolIncreaser();
        increaser.setPool(hpPool);
        increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
        increaser.setTexture(Textures.HP_POWER_UP);
        increaser.setActor(Factories.getActorFactory().create(increaser));

        return increaser;
    }

    public IPowerUp energy(final IPool energyPool) {

        PoolIncreaser increaser = new PoolIncreaser();
        increaser.setPool(energyPool);
        increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
        increaser.setTexture(Textures.ENERGY_POWER_UP);
        increaser.setActor(Factories.getActorFactory().create(increaser));

        return increaser;
    }

    public IPowerUp rocketMissileHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon rocketMissile = WeaponsFactory.INSTANCE.createRocketMissile(controller, stage.getMissilesLauncher());
        rocketMissile.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(stage.getAct() != null ? stage.getAct().getPowerUpAmmo() : 0);
        holder.setTexture(Textures.ROCKET_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(rocketMissile);
        holder.setWeaponIcon(Textures.ROCKET_ICON.getTexture());

        return holder;
    }

    public IPowerUp mineHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon mine = WeaponsFactory.INSTANCE.createMine(controller, stage.getMissilesLauncher());
        mine.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(stage.getAct().getPowerUpAmmo());
        holder.setTexture(Textures.MINE_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(mine);
        holder.setWeaponIcon(Textures.MINE_ICON.getTexture());

        return holder;
    }

    public IPowerUp shieldHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon shield = WeaponsFactory.INSTANCE.createShield(controller, stage.getMissilesLauncher());
        shield.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(stage.getAct().getPowerUpAmmo());
        holder.setTexture(Textures.SHIELD_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(shield);
        holder.setWeaponIcon(Textures.SHIELD_ICON.getTexture());

        return holder;
    }

    public IPowerUp waveHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon wave = WeaponsFactory.INSTANCE.createTimeWave(controller, stage.getMissilesLauncher());
        wave.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(stage.getAct().getPowerUpAmmo());
        holder.setTexture(Textures.WAVE_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(wave);
        holder.setWeaponIcon(Textures.WAVE_ICON.getTexture());

        return holder;
    }

    public IPowerUp flameHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon flamethrower = WeaponsFactory.INSTANCE.createFlamethrower(controller, stage.getMissilesLauncher());
        flamethrower.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(stage.getAct().getPowerUpAmmo());
        holder.setTexture(Textures.FLAME_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(flamethrower);
        holder.setWeaponIcon(Textures.FLAME_ICON.getTexture());

        return holder;
    }
}
