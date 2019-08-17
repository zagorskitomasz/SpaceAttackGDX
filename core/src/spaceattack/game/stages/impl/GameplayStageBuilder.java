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
import spaceattack.game.rpg.Attribute;
import spaceattack.game.rpg.Improvement;
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
import spaceattack.game.system.sound.MusicPlayer;
import spaceattack.game.utils.GameplayLabel;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.PlayerWeaponController;
import spaceattack.game.weapons.WeaponsFactory;

public abstract class GameplayStageBuilder implements IStageBuilder {

    protected GameplayStage stage;

    protected GameProgress gameProgress;
    protected PlayerShip playersShip;
    protected IEngine engine;
    protected MissionInputHandler processor;
    protected PlayerWeaponController weaponController;
    protected MissilesLauncher missilesLauncher;
    protected IWeapon primaryWeapon;
    protected IWeapon greenLaser;
    protected IFireButton primaryFireButton;
    protected ComplexFireButton secondaryFireButton;
    protected Accelerator accelerator;
    protected IPool expPool;
    protected HpPool hpPool;
    protected IPool energyPool;
    protected Bar expBar;
    protected Bar hpEnergyBar;
    protected EnemyBase enemyBase;
    protected GameplayLabel missionLabel;
    protected GameplayLabel levelLabel;

    @Override
    public IGameStage build(final GameProgress progress) {

        synchronized (this) {
            gameProgress = progress;
            stage = new GameplayStage();
            buildStage();
            setMissionNumber();
            setTanks(enemyBase);
            MusicPlayer.INSTANCE.playAct(getAct());
            gameProgress.notifyObservers();
            return stage;
        }
    }

    protected abstract void setTanks(EnemyBase enemyBase);

    protected abstract void setMissionNumber();

    protected void buildStage() {

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

    protected abstract void buildShip();

    protected abstract void addBackground();

    private void addSystemDependencies() {

        stage.setStage(Factories.getStageFactory().create());
        stage.setGameSaver(GameSaverFactory.INSTANCE.create());
        stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
    }

    private void addGameProgress() {

        gameProgress.registerObserver(stage);
        stage.setGameProgress(gameProgress);
        stage.setAct(getAct());
    }

    protected void initComponents() {

        playersShip = new PlayerShip();
        playersShip.setAttributes(gameProgress.getAttributes());
        accelerator = AcceleratorFactory.INSTANCE.create();
        engine = ShipEngineBuilder.INSTANCE.createInputEngine(playersShip, accelerator);
        processor = new MissionInputHandler();
        weaponController = new PlayerWeaponController();
        missilesLauncher = new MissilesLauncher(stage);
        primaryWeapon = createPrimaryWeapon(gameProgress.getAttributes().get(Attribute.ARMORY),
                gameProgress.getImprovements().get(Improvement.RED_LASER_MASTERY));
        greenLaser = createSecondaryWeapon(gameProgress.getAttributes().get(Attribute.ARMORY));
        primaryFireButton = FireButtonsBuilder.INSTANCE.primary(primaryWeapon);
        secondaryFireButton = FireButtonsBuilder.INSTANCE.secondary(weaponController, greenLaser);
        enemyBase = createEnemyBase(Factories.getUtilsFactory().create());
    }

    IWeapon createPrimaryWeapon(final int armory, final int redLaserMastery) {

        if (redLaserMastery >= 8) {
            return WeaponsFactory.INSTANCE.createMassiveRedLaser(weaponController, missilesLauncher, armory,
                    redLaserMastery);
        }

        if (redLaserMastery >= 4) {
            return WeaponsFactory.INSTANCE.createDoubleRedLaser(weaponController, missilesLauncher, armory,
                    redLaserMastery);
        }

        return WeaponsFactory.INSTANCE.createRedLaser(weaponController, missilesLauncher, armory, redLaserMastery);
    }

    protected abstract IWeapon createSecondaryWeapon(final int armory);

    protected abstract EnemyBase createEnemyBase(IUtils utils);

    private void configureInputProcessor() {

        processor.setUtils(Factories.getUtilsFactory().create());
        processor.registerFireButton(primaryFireButton);
        processor.registerFireButton(secondaryFireButton);
        processor.registerShip(playersShip);
        accelerator.setInputProcessor(processor);
        Factories.getUtilsFactory().create().setInputProcessor(processor);
    }

    private void configureWeapons() {

        weaponController.setPrimaryWeapon(primaryWeapon);
        weaponController.setPrimaryFireButton(primaryFireButton);

        weaponController.setSecondaryWeapon(greenLaser);
        weaponController.setSecondaryFireButton(secondaryFireButton);

        weaponController.setShip(playersShip);
    }

    private void initPools() {

        expPool = new ExperiencePool(gameProgress, stage.getProgressBackup());
        energyPool = new Pool(gameProgress.getAttributes().get(Attribute.BATTERY));
        hpPool = new HpPool(gameProgress.getAttributes().get(Attribute.SHIELDS));
        hpPool.setImmunityChecker(stage::isGameOver);
        // hpPool.addTemporalInfinityChecker(() -> true);
        // TODO immortal ship for test purposes;
        // remove before release

        expBar = BarBuilder.INSTANCE.experienceBar(expPool);
        hpEnergyBar = BarBuilder.INSTANCE.hpEnergyBar(hpPool, energyPool);

        stage.setExpPool(expPool);
    }

    private void initAI() {

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

    private void setTimeLabels() {

        ILabel levelUpILabel = Factories.getUtilsFactory().create().createTimeLabel("LEVEL UP!", 0xdaa520ff);
        TimeLabel levelUpLabel = new TimeLabel();
        levelUpLabel.setLabel(levelUpILabel);
        levelUpILabel.setPosition((Sizes.GAME_WIDTH - levelUpILabel.getWidth() * Sizes.X_FACTOR) * 0.5f,
                Sizes.GAME_HEIGHT * 0.4f);

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

    private void createLabels() {

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

    private void addComponents() {

        stage.setMissileLauncher(missilesLauncher);
        stage.addActorBeforeGUI(playersShip);
        stage.addActor(StaticImageFactory.INSTANCE.create(Textures.COCKPIT_PANEL.getTexture(), 0,
                Sizes.GAME_HEIGHT - 360 * Sizes.Y_FACTOR));
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
