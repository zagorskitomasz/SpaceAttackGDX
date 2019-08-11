package spaceattack.game.stages.impl;

import java.util.LinkedList;
import java.util.List;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.ILabel;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
import spaceattack.game.controlBar.ControlBarBuilder;
import spaceattack.game.controlBar.WeaponBar;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;
import spaceattack.game.utils.GameplayLabel;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.SpecialWeapons;

public class WeaponsStageBuilder implements IStageBuilder {

    @Override
    public IGameStage build(final GameProgress progress) {

        IUtils utils = Factories.getUtilsFactory().create();

        MainMenuStage stage = new MainMenuStage();

        stage.setStage(Factories.getStageFactory().create());
        stage.setGameSaver(GameSaverFactory.INSTANCE.create());
        stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
        stage.setGameProgress(progress);

        Factories.getUtilsFactory().create().setInputProcessor(stage.getStage());

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.MENU_BACKGROUND.getTexture(), 0, 0);
        StaticImage logo = StaticImageFactory.INSTANCE.create(Textures.LOGO.getTexture(), 0, Sizes.GAME_HEIGHT * 0.03f);

        ILabel infoLabel = utils.createBarLabel();
        infoLabel.update("Tap weapon\nto show details");
        infoLabel.setY(Sizes.GAME_HEIGHT * 0.7f);

        int lineIndex = 0;
        List<Runnable> updateActions = new LinkedList<>();
        for (SpecialWeapons weapon : SpecialWeapons.values()) {

            WeaponBar bar = ControlBarBuilder.INSTANCE.weaponBar(lineIndex, weapon, stage, infoLabel, updateActions);

            stage.addActorsContainer(bar);

            lineIndex++;
        }
        updateActions.forEach(Runnable::run);

        IButton backToMenuButton = MenuButtonsBuilder.INSTANCE.backToMainMenuButton(stage);

        stage.addBackground(background);
        stage.addActorBeforeGUI(logo);
        stage.addActor(new GameplayLabel(infoLabel));

        stage.addActor(backToMenuButton);

        progress.setNewWeaponAvailable(false);
        MusicPlayer.INSTANCE.playMenu();

        return stage;
    }
}
