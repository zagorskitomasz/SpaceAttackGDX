package spaceattack.game.stages.impl;

import com.zagorskidev.spaceattack.ships.IShip;

import spaceattack.game.GameProgress;
import spaceattack.game.buttons.weapon.FireButtonsBuilder;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.MissionInputHandler;
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

		IShip playersShip = null;

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

		StaticImage background = StaticImageFactory.INSTANCE.create(Textures.M1_BACKGROUND.getTexture(), 0, 0);

		stage.addActor(background);

		return stage;
	}
}
