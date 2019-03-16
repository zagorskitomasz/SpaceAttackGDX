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
import spaceattack.game.ai.shooters.DirectShooter;
import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.IEnemyShipsFactory;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeaponController;

public class EnemyBase extends InvisibleActor
{
	private GameplayStage stage;
	private IEnemyShipsFactory shipsFactory;
	private Radar radar;
	private List<IEnemyShip> enemyShips;
	private RadarVisible playerShip;
	private IPool hpPool;
	private IPool energyPool;
	private ComplexFireButton fireButton;
	private IWeaponController controller;
	
	private int tanksPool;

	private FrameController fighterTimer;
	private FrameController chaserTimer;
	private FrameController tankTimer;
	
	private IEnemyShip boss;
	private boolean isBossOnField;

	public EnemyBase(IUtils utils)
	{
		fighterTimer = new FrameController(utils, Consts.AI.FIGHTERS_PER_SECOND, 3000);
		chaserTimer = new FrameController(utils, Consts.AI.CHASERS_PER_SECOND, 10000);
		tankTimer = new FrameController(utils, Consts.AI.TANKS_PER_SECOND, 23000);
	}

	public void setStage(GameplayStage stage)
	{
		this.stage = stage;
	}

	public void setShipsFactory(IEnemyShipsFactory factory)
	{
		shipsFactory = factory;
	}

	public void setRadar(Radar radar)
	{
		this.radar = radar;
	}

	public void setHpPool(IPool hpPool)
	{
		this.hpPool = hpPool;
	}

	public void setEnergyPool(IPool energyPool)
	{
		this.energyPool = energyPool;
	}

	public void setComplexFireButton(ComplexFireButton button)
	{
		fireButton = button;
	}

	public void setWeaponController(IWeaponController controller)
	{
		this.controller = controller;
	}
	
	public void setTanksPool(int pool)
	{
		tanksPool = pool;
	}
	
	public void setBoss(IEnemyShip boss)
	{
		this.boss = boss;
	}

	@Override
	public void act(float delta)
	{
		if (fighterTimer.check())
			addFighter();

		if (chaserTimer.check())
			addChaser();

		if (tankTimer.check())
			addTank();
		
		if(tanksPool == 0 && boss != null && !isBossOnField)
		{
			addBoss();
			isBossOnField = true;
		}
	}

	private void addFighter()
	{
		updateRadar();

		IEnemyShip fighter = shipsFactory.createFighter(stage);

		MoverAI mover = chooseMover(fighter);
		ShooterAI shooter = chooseShooter(fighter);
		IPowerUp powerUp = choosePowerUp(fighter);

		fighter.setPlayerShip(radar.getPlayerShip());
		fighter.setMover(mover);
		fighter.setShooter(shooter);
		fighter.setPowerUp(powerUp);

		stage.addActorBeforeGUI(fighter);
	}

	private void addChaser()
	{
		updateRadar();

		IEnemyShip chaser = shipsFactory.createChaser(stage);

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

	private void addTank()
	{
		updateRadar();

		IEnemyShip tank = createTank();
		if(tank == null)
			return;

		MoverAI mover = MoverType.SLOW_DOWNER.create();
		ShooterAI shooter = createDirectShooter(tank);
		IPowerUp powerUp = choosePowerUp(tank);

		mover.setPlayerShip(playerShip);
		mover.setOwner(tank);
		
		tank.setPlayerShip(radar.getPlayerShip());
		tank.setMover(mover);
		tank.setShooter(shooter);
		tank.setPowerUp(powerUp);

		stage.addActorBeforeGUI(tank);
	}

	private void addBoss()
	{
		updateRadar();

		MoverAI mover = MoverType.FRONT_CHASER.create();
		ShooterAI shooter = createDirectShooter(boss);

		mover.setPlayerShip(playerShip);
		mover.setOwner(boss);
		
		boss.setPlayerShip(radar.getPlayerShip());
		boss.setMover(mover);
		boss.setShooter(shooter);

		stage.addActorBeforeGUI(boss);
	}

	private IEnemyShip createTank() 
	{
		IEnemyShip tank = null;
		
		if(tanksPool > 0)
			tank = shipsFactory.createTank(stage);
		else if(boss == null)
			tank = shipsFactory.createSuperTank(stage);
		
		tanksPool--;
		return tank;
	}

	private MoverAI chooseMover(IEnemyShip fighter)
	{
		MoverAI mover = null;

		if (enemyShips.isEmpty())
			mover = MoverType.DIRECT_CHASER.create();
		else
			mover = chooseMinOccursMover();

		mover.setPlayerShip(playerShip);
		mover.setOwner(fighter);

		return mover;
	}

	private MoverAI chooseMinOccursMover()
	{
		Map<MoverType, Long> countedMovers = countMovers();
		AtomicLong minOccurs = getMinOccursNumber(countedMovers);
		List<MoverType> lessFrequentMovers = getLessFrequentMovers(countedMovers, minOccurs);

		return lessFrequentMovers.get((int) (Math.random() * (lessFrequentMovers.size() - 1))).create();
	}

	private Map<MoverType, Long> countMovers()
	{
		Map<MoverType, Long> countedMovers = enemyShips //
				.stream() //
				.map(ship->ship.getMoverType()) //
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Arrays.asList(MoverType.values()) //
				.stream() //
				.filter(type->!countedMovers.containsKey(type)) //
				.forEach(type->countedMovers.put(type, 0l));
		return countedMovers;
	}

	private AtomicLong getMinOccursNumber(Map<MoverType, Long> countedMovers)
	{
		AtomicLong minOccurs = new AtomicLong(Long.MAX_VALUE);

		countedMovers //
				.entrySet() //
				.stream() //
				.forEach(entry->
				{
					if (entry.getValue() < minOccurs.get())
						minOccurs.set(entry.getValue());

				}); //
		return minOccurs;
	}

	private List<MoverType> getLessFrequentMovers(Map<MoverType, Long> countedMovers,AtomicLong minOccurs)
	{
		List<MoverType> lessFrequentMovers = countedMovers //
				.entrySet() //
				.stream() //
				.filter(entry->entry.getValue().equals(minOccurs.get()))//
				.map(entry->entry.getKey()) //
				.collect(Collectors.toList());
		return lessFrequentMovers;
	}

	private ShooterAI chooseShooter(IEnemyShip fighter)
	{
		return createDirectShooter(fighter);
	}

	private ShooterAI createDirectShooter(IEnemyShip fighter) {
		ShooterAI shooter;
		shooter = new DirectShooter();

		shooter.setFriends(enemyShips);
		shooter.setPlayerShip(playerShip);
		shooter.setOwner(fighter);

		return shooter;
	}

	private void updateRadar()
	{
		if (radar == null)
			return;

		radar.update();
		playerShip = radar.getPlayerShip();
		enemyShips = radar.getEnemyShips();
	}

	private IPowerUp choosePowerUp(IEnemyShip fighter)
	{
		IPowerUp powerUp = null;

		if (Math.random() < Consts.AI.FIGHTER_POWER_UP_CHANCE)
		{
			double randomNumber = Math.random();

			if (randomNumber < Consts.AI.FIGHTER_HP_UP_CHANCE)
				powerUp = PowerUpBuilder.INSTANCE.hp(hpPool);
			else if (randomNumber < Consts.AI.FIGHTER_HP_UP_CHANCE + Consts.AI.FIGHTER_ENE_UP_CHANCE)
				powerUp = PowerUpBuilder.INSTANCE.energy(energyPool);
			else
				powerUp = PowerUpBuilder.INSTANCE.rocketMissileHolder(controller, fireButton, stage);
		}
		return powerUp;
	}
}
