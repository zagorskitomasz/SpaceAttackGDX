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

public enum PowerUpBuilder {
    INSTANCE;

    public IPowerUp hp(IPool hpPool) {

        PoolIncreaser increaser = new PoolIncreaser();
        increaser.setPool(hpPool);
        increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
        increaser.setTexture(Textures.HP_POWER_UP);
        increaser.setActor(Factories.getActorFactory().create(increaser));

        return increaser;
    }

    public IPowerUp energy(IPool energyPool) {

        PoolIncreaser increaser = new PoolIncreaser();
        increaser.setPool(energyPool);
        increaser.setIncreasePercent(Consts.AI.POOL_INCREASE_PERCENT);
        increaser.setTexture(Textures.ENERGY_POWER_UP);
        increaser.setActor(Factories.getActorFactory().create(increaser));

        return increaser;
    }

    public IPowerUp rocketMissileHolder(IWeaponController controller, ComplexFireButton button, GameplayStage stage) {

        IWeapon rocketMissile = WeaponsFactory.INSTANCE.createRocketMissile(controller, stage.getMissilesLauncher());
        rocketMissile.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(5);
        holder.setTexture(Textures.ROCKET_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(rocketMissile);
        holder.setWeaponIcon(Textures.ROCKET_ICON.getTexture());

        return holder;
    }

    public IPowerUp mineHolder(IWeaponController controller, ComplexFireButton button, GameplayStage stage) {

        IWeapon mine = WeaponsFactory.INSTANCE.createMine(controller, stage.getMissilesLauncher());
        mine.setLevel(stage.getGameProgress().getLevel());

        WeaponHolder holder = new WeaponHolder();
        holder.setFireButton(button);
        holder.setAmmo(5);
        holder.setTexture(Textures.MINE_POWER_UP);
        holder.setActor(Factories.getActorFactory().create(holder));
        holder.setWeapon(mine);
        holder.setWeaponIcon(Textures.MINE_ICON.getTexture());

        return holder;
    }
}
