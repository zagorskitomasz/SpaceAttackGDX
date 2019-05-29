package spaceattack.game.ai;

import java.util.HashMap;
import java.util.Map;

import spaceattack.consts.Consts;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.Acts;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.shield.EnergyShieldEmiter;

public class Act5Mission14EnemyBase extends Act5EnemyBase {

    private final Map<Direction, IShip> helpers;

    public Act5Mission14EnemyBase(final IUtils utils) {

        super(utils);
        setAct(Acts.V);
        helpers = new HashMap<>();
    }

    @Override
    protected void addBoss() {

        super.addBoss();

        addSuperChaser(Direction.LEFT);
        addSuperChaser(Direction.RIGHT);

        IWeapon primaryWeapon = boss.getWeaponController().getPrimaryWeapon();
        if (primaryWeapon instanceof EnergyShieldEmiter) {
            ((EnergyShieldEmiter) primaryWeapon).setActivityPredicate(unrelatedValue -> isAnyOfHelpersAlive());
        }
    }

    private boolean isAnyOfHelpersAlive() {

        for (IShip ship : helpers.values()) {
            if (!ship.isToKill()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected IEnemyShip buildSuperChaser(final Direction direction) {

        updateRadar();

        IEnemyShip chaser = super.buildSuperChaser(direction);
        IPool hpPool = new HpPool(
                Consts.Pools.MAJOR_BOSS_HP_BASE_AMOUNT,
                Consts.Pools.MAJOR_BOSS_HP_INCREASE_PER_LEVEL,
                Consts.Pools.MAJOR_BOSS_HP_BASE_REGEN,
                Consts.Pools.MAJOR_BOSS_HP_REGEN_PER_LEVEL);

        chaser.setHpPool(hpPool);
        chaser.setLevel(stage.getCurrentMission() * 2);

        helpers.put(direction, chaser);

        return chaser;
    }
}
