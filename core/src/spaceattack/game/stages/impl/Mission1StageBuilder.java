package spaceattack.game.stages.impl;

import com.zagorskidev.spaceattack.stages.impl.Bar;
import com.zagorskidev.spaceattack.stages.impl.HpEnergyBar;

import spaceattack.game.GameProgress;
import spaceattack.game.buttons.weapon.FireButtonsBuilder;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.engines.IEngine;
import spaceattack.game.engines.ShipEngineBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.MissionInputHandler;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.ships.pools.Pool;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.PlayerWeaponController;
import spaceattack.game.weapons.WeaponsFactory;

public enum Mission1StageBuilder implements IStageBuilder
{
	INSTANCE;

	@Override
	public IGameStage build(GameProgress progress)
	{
		GameplayStage stage = new GameplayStage();

		GameProgress gameProgress = GameLoaderFactory.INSTANCE.create().load();
		gameProgress.registerObserver(stage);

		stage.setStage(Factories.getStageFactory().create());
		stage.setGameSaver(GameSaverFactory.INSTANCE.create());
		stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
		stage.setGameProgress(gameProgress);

		PlayerShip playersShip = new PlayerShip();

		MissionInputHandler processor = new MissionInputHandler();
		PlayerWeaponController weaponController = new PlayerWeaponController();
		MissilesLauncher missilesLauncher = new MissilesLauncher(stage);
		IWeapon redLaser = WeaponsFactory.INSTANCE.createRedLaser(weaponController, missilesLauncher);
		IWeapon greenLaser = null;
		IFireButton primaryFireButton = FireButtonsBuilder.INSTANCE.primary(redLaser);
		IFireButton secondaryFireButton = FireButtonsBuilder.INSTANCE.secondary(weaponController, greenLaser);

		processor.registerFireButton(primaryFireButton);
		processor.registerFireButton(secondaryFireButton);
		processor.registerShip(playersShip);
		stage.setInputProcessor(processor);
		Factories.getUtilsFactory().create().setInputProcessor(processor);

		gameProgress.registerObserver(playersShip);
		playersShip.notify(gameProgress);
		weaponController.setShip(playersShip);

		weaponController.setPrimaryWeapon(redLaser);
		weaponController.setPrimaryFireButton(primaryFireButton);

		weaponController.setSecondaryWeapon(greenLaser);
		weaponController.setSecondaryFireButton(secondaryFireButton);

		IEngine engine = ShipEngineBuilder.INSTANCE.createPlayersEngine(playersShip);

		IPool energyPool = new Pool(50, 10, 10, 2);
		IPool hpPool = new HpPool(50, 10, 5, 1);
		Bar energyBar = new HpEnergyBar(energyPool, hpPool);
		energyBar.initGdx();

		playersShip.setActor(Factories.getActorFactory().create(playersShip));
		playersShip.loadComplexGraphics(Textures.PLAYER_SHIP_F.getTexture(), Textures.PLAYER_SHIP_R.getTexture(),
				Textures.PLAYER_SHIP_L.getTexture());
		playersShip.setShipEngine(engine);
		playersShip.addWeapon(redLaser);
		playersShip.setEnergyPool(energyPool);
		playersShip.setHpPool(hpPool);

		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M1_BACKGROUND.getTexture(), 0, 0);

		stage.addActor(background);

		return stage;
	}
}
