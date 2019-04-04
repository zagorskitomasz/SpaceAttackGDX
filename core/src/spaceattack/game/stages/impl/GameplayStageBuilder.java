package spaceattack.game.stages.impl;

import spaceattack.consts.Consts;
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
import spaceattack.game.system.Acts;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.GameplayLabel;
import spaceattack.game.utils.IUtils;
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
	private GameplayLabel missionLabel;
	private GameplayLabel levelLabel;

	@Override
	public IGameStage build(GameProgress progress)
	{
		synchronized (this)
		{
			gameProgress = progress;
			stage = new GameplayStage();
			buildStage();
			setMissionNumber();
			setTanks(enemyBase);
			gameProgress.notifyObservers();
			return stage;
		}
	}
	protected abstract void setTanks(EnemyBase enemyBase);

	protected abstract void setMissionNumber();

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
		createLabels();
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
		enemyBase = createEnemyBase(Factories.getUtilsFactory().create());
	}

	protected abstract EnemyBase createEnemyBase(IUtils utils);
	
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
		playersShip.loadComplexGraphics(Textures.PLAYER_SHIP1_F.getTexture(), Textures.PLAYER_SHIP1_R.getTexture(),
				Textures.PLAYER_SHIP1_L.getTexture());
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
		energyPool = new Pool(
				Consts.POOLS.PLAYER_ENERGY_BASE_AMOUNT, 
				Consts.POOLS.PLAYER_ENERGY_INCREASE_PER_LEVEL, 
				Consts.POOLS.PLAYER_ENERGY_BASE_REGEN, 
				Consts.POOLS.PLAYER_ENERGY_REGEN_PER_LEVEL);
		hpPool = new HpPool(
				Consts.POOLS.PLAYER_HP_BASE_AMOUNT, 
				Consts.POOLS.PLAYER_HP_INCREASE_PER_LEVEL, 
				Consts.POOLS.PLAYER_HP_BASE_REGEN, 
				Consts.POOLS.PLAYER_HP_REGEN_PER_LEVEL);
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

	protected abstract Acts getAct();
	
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
	
	private void createLabels()
	{
		ILabel levelILabel = Factories.getUtilsFactory().create().createBarLabel();
		levelILabel.setAlignment(Consts.Align.topRight);
		levelILabel.setPosition(Sizes.GAME_WIDTH - 60 * Sizes.X_FACTOR, 350 * Sizes.Y_FACTOR);
		
		levelLabel = new GameplayLabel(levelILabel);
		gameProgress.registerObserver(state -> levelLabel.setText("Ship level: " + state.getLevel()));
		
		ILabel missionILabel = Factories.getUtilsFactory().create().createBarLabel();
		missionILabel.setAlignment(Consts.Align.topRight);
		missionILabel.setPosition(Sizes.GAME_WIDTH - 60 * Sizes.X_FACTOR, 310 * Sizes.Y_FACTOR);
		
		missionLabel = new GameplayLabel(missionILabel);
		stage.registerObserver(state -> missionLabel.setText("Mission: " + state.getCurrentMission()));
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
		stage.addActor(levelLabel);
		stage.addActor(missionLabel);
	}
}
