package spaceattack.game.stages.impl;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.ILabel;
import spaceattack.game.actors.TimeLabel;
import spaceattack.game.ai.EnemyBase;
import spaceattack.game.ai.Radar;
import spaceattack.game.bars.Bar;
import spaceattack.game.bars.BarBuilder;
import spaceattack.game.buttons.Accelerator;
import spaceattack.game.buttons.AcceleratorFactory;
import spaceattack.game.buttons.weapon.ComplexFireButton;
import spaceattack.game.buttons.weapon.FireButtonsBuilder;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.MissionInputHandler;
import spaceattack.game.ships.enemy.EnemyShipsFactory;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.ships.pools.ExperiencePool;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.PlayerWeaponController;
import spaceattack.game.weapons.WeaponsFactory;
import spaceattack.game.weapons.missiles.Burner;
import spaceattack.game.weapons.missiles.BurnerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;

public abstract class GameplayStageBuilder implements IStageBuilder
{
	protected GameplayStage stage;

	private GameProgress gameProgress;
	private PlayerShip playersShip;
	private IEngine engine;
	private MissionInputHandler processor;
	private PlayerWeaponController weaponController;
	private MissilesLauncher missilesLauncher;
	private IWeapon redLaser;
	private IWeapon greenLaser;
	private IFireButton primaryFireButton;
	private ComplexFireButton secondaryFireButton;
	private Accelerator accelerator;
	private IPool expPool;
	private HpPool hpPool;
	private IPool energyPool;
	private Bar expBar;
	private Bar hpEnergyBar;
	private EnemyBase enemyBase;

	@Override
	public IGameStage build(GameProgress progress)
	{
		synchronized (this)
		{
			gameProgress = progress;
			stage = new GameplayStage();
			setMissionNumber();
			buildStage();
			setTanks(enemyBase);
			return stage;
		}
	}
	protected abstract void setTanks(EnemyBase enemyBase);

	protected void buildStage()
	{
		addSystemDependencies();
		addGameProgress();
		initComponents();
		initPools();
		initAI();
		buildShip();
		configureInputProcessor();
		configureWeapons();
		setTimeLabels();
		addBackground();
		addComponents();
	}

	protected abstract void addBackground();

	private void addSystemDependencies()
	{
		stage.setStage(Factories.getStageFactory().create());
		stage.setGameSaver(GameSaverFactory.INSTANCE.create());
		stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
	}

	private void addGameProgress()
	{
		gameProgress.registerObserver(stage);
		stage.setGameProgress(gameProgress);
	}

	protected void initComponents()
	{
		playersShip = new PlayerShip();
		accelerator = AcceleratorFactory.INSTANCE.create();
		engine = ShipEngineBuilder.INSTANCE.createInputEngine(playersShip, accelerator);
		processor = new MissionInputHandler();
		weaponController = new PlayerWeaponController();
		missilesLauncher = new MissilesLauncher(stage);
		redLaser = WeaponsFactory.INSTANCE.createRedLaser(weaponController, missilesLauncher);
		greenLaser = WeaponsFactory.INSTANCE.createGreenLaser(weaponController, missilesLauncher);
		primaryFireButton = FireButtonsBuilder.INSTANCE.primary(redLaser);
		secondaryFireButton = FireButtonsBuilder.INSTANCE.secondary(weaponController, greenLaser);
		enemyBase = new EnemyBase(Factories.getUtilsFactory().create());
	}

	private void configureInputProcessor()
	{
		processor.setUtils(Factories.getUtilsFactory().create());
		processor.registerFireButton(primaryFireButton);
		processor.registerFireButton(secondaryFireButton);
		processor.registerShip(playersShip);
		accelerator.setInputProcessor(processor);
		Factories.getUtilsFactory().create().setInputProcessor(processor);
	}

	private void configureWeapons()
	{
		weaponController.setPrimaryWeapon(redLaser);
		weaponController.setPrimaryFireButton(primaryFireButton);

		weaponController.setSecondaryWeapon(greenLaser);
		weaponController.setSecondaryFireButton(secondaryFireButton);

		weaponController.setShip(playersShip);
	}

	private void buildShip()
	{
		
		Explosion explosion = ExplosionsBuilder.INSTANCE.createBossExplosion();
		Burner burner = BurnerBuilder.INSTANCE.build(playersShip);

		gameProgress.registerObserver(playersShip);
		playersShip.notify(gameProgress);
		playersShip.setActor(Factories.getActorFactory().create(playersShip));
		playersShip.loadComplexGraphics(Textures.PLAYER_SHIP_F.getTexture(), Textures.PLAYER_SHIP_R.getTexture(),
				Textures.PLAYER_SHIP_L.getTexture());
		playersShip.setShipEngine(engine);
		playersShip.addWeapon(redLaser);
		playersShip.addWeapon(greenLaser);
		playersShip.setEnergyPool(energyPool);
		playersShip.setHpPool(hpPool);
		playersShip.setLevel(gameProgress.getLevel());
		playersShip.setMissilesLauncher(missilesLauncher);
		playersShip.setExplosion(explosion);
		playersShip.setBurner(burner);
	}

	private void initPools()
	{
		expPool = new ExperiencePool(gameProgress, stage.getProgressBackup());
		energyPool = new Pool(80, 20, 20, 4);
		hpPool = new HpPool(80, 12, 10, 1f);
		hpPool.setImmunityChecker(stage::isGameOver);

		expBar = BarBuilder.INSTANCE.experienceBar(expPool);
		hpEnergyBar = BarBuilder.INSTANCE.hpEnergyBar(hpPool, energyPool);

		stage.setExpPool(expPool);
	}

	private void initAI()
	{
		enemyBase.setStage(stage);
		enemyBase.setShipsFactory(EnemyShipsFactory.INSTANCE);
		enemyBase.setActor(Factories.getActorFactory().create(enemyBase));
		enemyBase.setRadar(new Radar(stage.getActors()));
		enemyBase.setHpPool(hpPool);
		enemyBase.setEnergyPool(energyPool);
		enemyBase.setComplexFireButton(secondaryFireButton);
		enemyBase.setWeaponController(weaponController);
	}

	private void setTimeLabels()
	{
		ILabel levelUpILabel = Factories.getUtilsFactory().create().createTimeLabel("LEVEL UP!", 0xdaa520ff);
		TimeLabel levelUpLabel = new TimeLabel();
		levelUpLabel.setLabel(levelUpILabel);
		levelUpILabel.setPosition((Sizes.GAME_WIDTH - levelUpILabel.getWidth() * Sizes.X_FACTOR) * 0.5f, Sizes.GAME_HEIGHT * 0.4f);

		ILabel failedILabel = Factories.getUtilsFactory().create().createTimeLabel("MISSION\nFAILED!", 0xff0000ff);
		TimeLabel failedLabel = new TimeLabel();
		failedLabel.setLabel(failedILabel);

		ILabel completedILabel = Factories.getUtilsFactory().create().createTimeLabel(" MISSION\nCOMPLETED!",
				0x00ff00ff);
		TimeLabel completedLabel = new TimeLabel();
		completedLabel.setLabel(completedILabel);

		stage.setLevelUpLabel(levelUpLabel);
		stage.setFailedLabel(failedLabel);
		stage.setCompletedLabel(completedLabel);
	}

	private void addComponents()
	{
		stage.setMissileLauncher(missilesLauncher);
		stage.addActorBeforeGUI(playersShip);
		stage.addActor(StaticImageFactory.INSTANCE.create(Textures.COCKPIT_PANEL.getTexture(),0, Sizes.GAME_HEIGHT - 360 * Sizes.Y_FACTOR));
		stage.addActor(primaryFireButton);
		stage.addActor(secondaryFireButton);
		stage.addActor(accelerator);
		stage.addActor(expBar);
		stage.addActor(hpEnergyBar);
		stage.addActor(enemyBase);
	}

	public void setMissionNumber() {
		// TODO Auto-generated method stub
		
	}
}
