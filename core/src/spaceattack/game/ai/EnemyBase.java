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
import spaceattack.game.powerup.IPowerUp;
import spaceattack.game.powerup.PowerUpBuilder;
import spaceattack.game.ships.enemy.IEnemyShip;
import spaceattack.game.ships.enemy.IEnemyShipsFactory;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;

public class EnemyBase extends InvisibleActor
{
	private GameplayStage stage;
	private IEnemyShipsFactory shipsFactory;
	private Radar radar;
	private List<IEnemyShip> enemyShips;
	private RadarVisible playerShip;
	private IPool hpPool;
	private IPool energyPool;

	private FrameController fighterTimer;

	public EnemyBase(IUtils utils)
	{
		fighterTimer = new FrameController(utils, Consts.AI.FIGHTERS_PER_SECOND, 3000);
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

	@Override
	public void act(float delta)
	{
		if (fighterTimer.check())
			addFighter();
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
		ShooterAI shooter = null;

		if (enemyShips.isEmpty())
			shooter = new DirectShooter();

		// for tests
		shooter = new DirectShooter();

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
		}
		return powerUp;
	}
}
