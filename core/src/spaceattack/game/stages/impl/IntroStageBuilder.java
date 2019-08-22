package spaceattack.game.stages.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.ShooterType;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.intro.Destroyer;
import spaceattack.game.ships.intro.Earth;
import spaceattack.game.ships.intro.EscapingPlayer;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.IntroStage;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaver;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.AIWeaponController;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.flame.FlamethrowerBuilder;
import spaceattack.game.weapons.missiles.Explosion;
import spaceattack.game.weapons.missiles.ExplosionsBuilder;

public class IntroStageBuilder implements IStageBuilder {

    @Override
    public IGameStage build(final GameProgress progress) {

        IntroStage stage = new IntroStage();

        GameLoader loader = GameLoaderFactory.INSTANCE.create();
        GameSaver saver = GameSaverFactory.INSTANCE.create();
        IUtils utils = Factories.getUtilsFactory().create();

        stage.setStage(Factories.getStageFactory().create());
        stage.setGameSaver(saver);
        stage.setGameLoader(loader);

        utils.setInputProcessor(stage.getStage());
        stage.setUtils(utils);

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.INTRO_BG.getTexture(), 0, 0);

        MissilesLauncher launcher = new MissilesLauncher(stage);

        EscapingPlayer player = buildPlayer();
        Earth earth = buildEarth(launcher, utils, player);
        IGameActor destroyer = buildDestroyer(launcher, earth);

        stage.addBackground(background);
        stage.addActor(earth);
        stage.addActor(destroyer);
        stage.addActor(player);

        MusicPlayer.INSTANCE.playMenu();

        return stage;
    }

    protected EscapingPlayer buildPlayer() {

        EscapingPlayer player = new EscapingPlayer();

        player.setActor(Factories.getActorFactory().create(player));
        player.setX(Sizes.GAME_WIDTH * 0.5f);
        player.setY(Sizes.GAME_HEIGHT * 0.4f);

        return player;
    }

    protected Destroyer buildDestroyer(final MissilesLauncher launcher, final Earth earth) {

        Destroyer destroyer = new Destroyer(Consts.AttributesStarters.INTRO_STATION.get(1));
        destroyer.setEarth(earth);

        destroyer.setActor(Factories.getActorFactory().create(destroyer));
        destroyer.setX(Sizes.GAME_WIDTH * 0.5f);
        destroyer.setY(Sizes.GAME_HEIGHT + Textures.SPACE_STATION.getTexture().getHeight());

        MoverAI mover = MoverType.SLOW_DOWNER.create();
        mover.setPlayerShip(earth);
        mover.setOwner(destroyer);
        destroyer.setMover(mover);
        ShooterAI shooter = ShooterType.INSTANT_SHOOTER.create();
        shooter.setFriends(Collections.emptyList());
        shooter.setPlayerShip(earth);
        shooter.setOwner(destroyer);
        destroyer.setShooter(shooter);
        IWeaponController controller = new AIWeaponController();
        controller.setShip(destroyer);
        destroyer.setWeaponController(controller);
        destroyer.setMissilesLauncher(launcher);
        IEngine engine = ShipEngineBuilder.INSTANCE.createDestinationEngine(destroyer);

        IWeapon thrower = FlamethrowerBuilder.INSTANCE.build(controller, launcher, 1);
        thrower.setNoEnergyCost();
        controller.addPassiveWeapon(thrower);
        destroyer.addWeapon(thrower);

        destroyer.setShipEngine(engine);
        engine.setDestination(
                Factories.getVectorFactory().create(Sizes.GAME_WIDTH * 0.5f,
                        0 - Textures.SPACE_STATION.getTexture().getHeight()));
        destroyer.setTexture(Textures.SPACE_STATION.getTexture());

        return destroyer;
    }

    protected Earth buildEarth(final MissilesLauncher launcher, final IUtils utils, final EscapingPlayer player) {

        Earth earth = new Earth(new FrameController(utils, 1));

        List<Explosion> explosions = Arrays.asList(
                ExplosionsBuilder.INSTANCE.createBossExplosion(),
                ExplosionsBuilder.INSTANCE.createBossExplosion(),
                ExplosionsBuilder.INSTANCE.createBossExplosion(),
                ExplosionsBuilder.INSTANCE.createBossExplosion(),
                ExplosionsBuilder.INSTANCE.createBossExplosion());

        earth.setActor(Factories.getActorFactory().create(earth));
        earth.setExplosions(explosions);
        earth.setMissilesLauncher(launcher);
        earth.setPlayer(player);

        earth.setX(Sizes.GAME_WIDTH * 0.5f);
        earth.setY(Sizes.GAME_HEIGHT * 0.4f);

        return earth;
    }
}
