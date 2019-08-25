package spaceattack.game.powerup;

import spaceattack.consts.Consts;
import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.WeaponsFactory;

public enum PowerUpBuilder implements IPowerUpBuilder {
    INSTANCE;

    @Override
    public IPowerUp hp(final IPool hpPool) {

        PoolIncreaser increaser = new PoolIncreaser();
        increaser.setPool(hpPool);
        increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
        increaser.setTexture(Textures.HP_POWER_UP);
        increaser.setActor(Factories.getActorFactory().create(increaser));

        return increaser;
    }

    @Override
    public IPowerUp energy(final IPool energyPool) {

        PoolIncreaser increaser = new PoolIncreaser();
        increaser.setPool(energyPool);
        increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
        increaser.setTexture(Textures.ENERGY_POWER_UP);
        increaser.setActor(Factories.getActorFactory().create(increaser));

        return increaser;
    }

    @Override
    public IPowerUp rocketMissileHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon rocketMissile = WeaponsFactory.INSTANCE.createRocketMissile(controller, stage.getMissilesLauncher(),
                stage.getGameProgress().getAttributes().get(Attribute.ARMORY));

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(getAmmo(stage));
        holder.setTexture(Textures.ROCKET_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(rocketMissile);
        holder.setWeaponIcon(Textures.ROCKET_ICON.getTexture());

        return holder;
    }

    private int getAmmo(final GameplayStage stage) {

        if (stage == null) {
            return 0;
        }
        return Consts.Weapons.BASE_AMMO + stage.getGameProgress().getImprovements().get(Improvement.AMMO_COLLECTOR);
    }

    @Override
    public IPowerUp mineHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon mine = WeaponsFactory.INSTANCE.createMine(controller, stage.getMissilesLauncher(),
                stage.getGameProgress().getAttributes().get(Attribute.ARMORY));

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(getAmmo(stage));
        holder.setTexture(Textures.MINE_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(mine);
        holder.setWeaponIcon(Textures.MINE_ICON.getTexture());

        return holder;
    }

    @Override
    public IPowerUp shieldHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon shield = WeaponsFactory.INSTANCE.createShield(controller, stage.getMissilesLauncher(),
                stage.getGameProgress().getAttributes().get(Attribute.ARMORY));

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(getAmmo(stage));
        holder.setTexture(Textures.SHIELD_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(shield);
        holder.setWeaponIcon(Textures.SHIELD_ICON.getTexture());

        return holder;
    }

    @Override
    public IPowerUp waveHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon wave = WeaponsFactory.INSTANCE.createTimeWave(controller, stage.getMissilesLauncher(),
                stage.getGameProgress().getAttributes().get(Attribute.ARMORY) * 2);

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(getAmmo(stage));
        holder.setTexture(Textures.WAVE_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(wave);
        holder.setWeaponIcon(Textures.WAVE_ICON.getTexture());

        return holder;
    }

    @Override
    public IPowerUp flameHolder(final IWeaponController controller, final ComplexFireButton button,
            final GameplayStage stage) {

        IWeapon flamethrower = WeaponsFactory.INSTANCE.createFlamethrower(controller, stage.getMissilesLauncher(),
                stage.getGameProgress().getAttributes().get(Attribute.ARMORY));

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(getAmmo(stage));
        holder.setTexture(Textures.FLAME_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(flamethrower);
        holder.setWeaponIcon(Textures.FLAME_ICON.getTexture());

        return holder;
    }
}
