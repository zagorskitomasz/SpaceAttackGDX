package spaceattack.game.ai;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import spaceattack.consts.Consts;
import spaceattack.game.actors.InvisibleActor;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.ships.IBoss;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.IEnemyShipsFactory;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.Acts;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeaponController;

public abstract class EnemyBase extends InvisibleActor {

    protected GameplayStage stage;
    protected Radar radar;
    protected List<IEnemyShip> enemyShips;
    protected RadarVisible playerShip;
    protected ComplexFireButton fireButton;
    protected IWeaponController controller;

    private Acts act;
    private IEnemyShipsFactory shipsFactory;
    private IPool hpPool;
    private IPool energyPool;

    private int tanksPool;

    private final FrameController fighterTimer;
    private final FrameController chaserTimer;
    private final FrameController tankTimer;

    protected IBoss boss;
    private boolean isBossOnField;

    public EnemyBase(final IUtils utils) {

        fighterTimer = new FrameController(utils, Consts.AI.FIGHTERS_PER_SECOND, Consts.AI.FIRST_FIGHTER_AFTER_MILLIS);
        chaserTimer = new FrameController(utils, Consts.AI.CHASERS_PER_SECOND, Consts.AI.FIRST_CHASER_AFTER_MILLIS);
        tankTimer = new FrameController(utils, Consts.AI.TANKS_PER_SECOND, Consts.AI.FIRST_TANK_AFTER_MILLIS);
    }

    public void setStage(final GameplayStage stage) {

        this.stage = stage;
    }

    public void setShipsFactory(final IEnemyShipsFactory factory) {

        shipsFactory = factory;
    }

    public void setRadar(final Radar radar) {

        this.radar = radar;
    }

    public void setHpPool(final IPool hpPool) {

        this.hpPool = hpPool;
    }

    public void setEnergyPool(final IPool energyPool) {

        this.energyPool = energyPool;
    }

    public void setComplexFireButton(final ComplexFireButton button) {

        fireButton = button;
    }

    public void setWeaponController(final IWeaponController controller) {

        this.controller = controller;
    }

    public void setTanksPool(final int pool) {

        tanksPool = pool;
    }

    public void setBoss(final IBoss boss) {

        this.boss = boss;
    }

    protected void setAct(final Acts act) {

        this.act = act;
    }

    @Override
    public void act(final float delta) {

        if (fighterTimer.check() && !isBossOnField) {
            addFighter();
        }

        if (chaserTimer.check() && !isBossOnField) {
            addChaser();
        }

        if (tanksPool > 0 && tankTimer.check() && !isBossOnField) {
            addTank();
        }

        if (tanksPool <= 0 && boss != null && tankTimer.check() && !isBossOnField) {
            addBoss();
            isBossOnField = true;
        }
    }

    private void addFighter() {

        updateRadar();

        IEnemyShip fighter = shipsFactory.createFighter(act, stage);

        MoverAI mover = chooseMover(fighter);
        ShooterAI shooter = chooseShooter(fighter);
        IPowerUp powerUp = choosePowerUp(fighter);

        fighter.setPlayerShip(radar.getPlayerShip());
        fighter.setMover(mover);
        fighter.setShooter(shooter);
        fighter.setPowerUp(powerUp);

        stage.addActorBeforeGUI(fighter);
    }

    private void addChaser() {

        updateRadar();

        IEnemyShip chaser = shipsFactory.createChaser(act, stage);

        MoverAI mover = MoverType.FRONT_CHASER.create();
        ShooterAI shooter = createDirectShooter(chaser);
        IPowerUp powerUp = choosePowerUp(chaser);

        mover.setPlayerShip(playerShip);
        mover.setOwner(chaser);

        chaser.setPlayerShip(radar.getPlayerShip());
        chaser.setMover(mover);
        chaser.setShooter(shooter);
        chaser.setPowerUp(powerUp);

        stage.addActorBeforeGUI(chaser);
    }

    protected void addSuperChaser(final Direction direction) {

        updateRadar();

        IEnemyShip chaser = shipsFactory.createSuperChaser(act, stage);

        MoverAI mover = MoverType.SIDE_FRONT_CHASER.create();
        mover.setPlayerShip(playerShip);
        mover.setOwner(chaser);
        mover.setSiderDirection(direction);

        ShooterAI shooter = ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER.create();
        shooter.setFriends(enemyShips);
        shooter.setPlayerShip(playerShip);
        shooter.setOwner(chaser);

        chaser.setPlayerShip(radar.getPlayerShip());
        chaser.setMover(mover);
        chaser.setShooter(shooter);
        chaser.getHpPool().addTemporalInfinityChecker(() -> boss != null && !boss.isToKill());

        stage.addActorBeforeGUI(chaser);
    }

    private void addTank() {

        updateRadar();

        IEnemyShip tank = createTank();

        MoverAI mover = MoverType.SLOW_DOWNER.create();
        ShooterAI shooter = createTankShooter(tank);
        IPowerUp powerUp = choosePowerUp(tank);

        shooter.setFriends(enemyShips);
        shooter.setPlayerShip(playerShip);
        shooter.setOwner(tank);

        mover.setPlayerShip(playerShip);
        mover.setOwner(tank);

        tank.setPlayerShip(radar.getPlayerShip());
        tank.setMover(mover);
        tank.setShooter(shooter);
        tank.setPowerUp(powerUp);

        stage.addActorBeforeGUI(tank);
    }

    protected abstract ShooterAI createTankShooter(IEnemyShip tank);

    protected void addBoss() {

        updateRadar();

        MoverAI mover = boss.getDefaultMoverType().create();
        ShooterAI shooter = createBossShooter(boss);

        mover.setPlayerShip(playerShip);
        mover.setOwner(boss);
        mover.registerObserver(shooter);

        boss.setPlayerShip(radar.getPlayerShip());
        boss.setMover(mover);
        boss.setShooter(shooter);

        stage.addActorBeforeGUI(boss);
    }

    private ShooterAI createBossShooter(final IBoss boss) {

        ShooterAI shooter;
        shooter = boss.getDefaultShooterType().create();

        shooter.setFriends(enemyShips);
        shooter.setPlayerShip(playerShip);
        shooter.setOwner(boss);

        return shooter;
    }

    private IEnemyShip createTank() {

        IEnemyShip tank = null;

        if (tanksPool > 1 || boss != null) {
            tank = shipsFactory.createTank(act, stage);
        }
        else {
            tank = shipsFactory.createSuperTank(act, stage);
        }

        tanksPool--;
        return tank;
    }

    private MoverAI chooseMover(final IEnemyShip fighter) {

        MoverAI mover = null;

        if (enemyShips.isEmpty()) {
            mover = MoverType.DIRECT_CHASER.create();
        }
        else {
            mover = chooseMinOccursMover();
        }

        mover.setPlayerShip(playerShip);
        mover.setOwner(fighter);

        return mover;
    }

    private MoverAI chooseMinOccursMover() {

        Map<MoverType, Long> countedMovers = countMovers();
        AtomicLong minOccurs = getMinOccursNumber(countedMovers);
        List<MoverType> lessFrequentMovers = getLessFrequentMovers(countedMovers, minOccurs);

        return lessFrequentMovers.get((int) (Math.random() * (lessFrequentMovers.size() - 1))).create();
    }

    private Map<MoverType, Long> countMovers() {

        Map<MoverType, Long> countedMovers = enemyShips //
                .stream() //
                .map(ship -> ship.getMoverType()) //
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Arrays.asList(MoverType.values()) //
                .stream() //
                .filter(type -> !countedMovers.containsKey(type)) //
                .forEach(type -> countedMovers.put(type, 0l));
        return countedMovers;
    }

    private AtomicLong getMinOccursNumber(final Map<MoverType, Long> countedMovers) {

        AtomicLong minOccurs = new AtomicLong(Long.MAX_VALUE);

        countedMovers //
                .entrySet() //
                .stream() //
                .filter(entry -> !entry.getKey().isSpecial()) //
                .forEach(entry -> {
                    if (entry.getValue() < minOccurs.get()) {
                        minOccurs.set(entry.getValue());
                    }

                }); //
        return minOccurs;
    }

    private List<MoverType> getLessFrequentMovers(final Map<MoverType, Long> countedMovers,
            final AtomicLong minOccurs) {

        List<MoverType> lessFrequentMovers = countedMovers //
                .entrySet() //
                .stream() //
                .filter(entry -> !entry.getKey().isSpecial()) //
                .filter(entry -> entry.getValue().equals(minOccurs.get()))//
                .map(entry -> entry.getKey()) //
                .collect(Collectors.toList());
        return lessFrequentMovers;
    }

    private ShooterAI chooseShooter(final IEnemyShip fighter) {

        return createDirectShooter(fighter);
    }

    protected ShooterAI createDirectShooter(final IEnemyShip fighter) {

        ShooterAI shooter;
        shooter = ShooterType.DIRECT_SHOOTER.create();

        shooter.setFriends(enemyShips);
        shooter.setPlayerShip(playerShip);
        shooter.setOwner(fighter);

        return shooter;
    }

    protected void updateRadar() {

        if (radar == null) {
            return;
        }

        radar.update();
        playerShip = radar.getPlayerShip();
        enemyShips = radar.getEnemyShips();
    }

    private IPowerUp choosePowerUp(final IEnemyShip fighter) {

        IPowerUp powerUp = null;

        if (Math.random() < Consts.AI.FIGHTER_POWER_UP_CHANCE) {
            double randomNumber = Math.random();

            if (randomNumber < Consts.AI.FIGHTER_HP_UP_CHANCE) {
                powerUp = PowerUpBuilder.INSTANCE.hp(hpPool);
            }
            else
                if (randomNumber < Consts.AI.FIGHTER_HP_UP_CHANCE + Consts.AI.FIGHTER_ENE_UP_CHANCE) {
                    powerUp = PowerUpBuilder.INSTANCE.energy(energyPool);
                }
                else {
                    powerUp = choosePowerUp();
                }
        }
        return powerUp;
    }

    protected abstract IPowerUp choosePowerUp();

    public enum Direction {

        LEFT(-1),
        RIGHT(1);

        Direction(final float factor) {

            this.factor = factor;
        }

        private float factor;

        public float getFactor() {

            return factor;
        }
    }
}
