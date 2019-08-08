package spaceattack.game.stages.impl;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.ILabel;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;
import spaceattack.game.utils.GameplayLabel;

public class MainMenuStageBuilder implements IStageBuilder {

    @Override
    public IGameStage build(final GameProgress progress) {

        MainMenuStage stage = new MainMenuStage();

        stage.setStage(Factories.getStageFactory().create());
        stage.setGameSaver(GameSaverFactory.INSTANCE.create());
        stage.setGameLoader(GameLoaderFactory.INSTANCE.create());
        stage.setGameProgress(progress);

        Factories.getUtilsFactory().create().setInputProcessor(stage.getStage());

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.MENU_BACKGROUND.getTexture(), 0, 0);
        StaticImage logo = StaticImageFactory.INSTANCE.create(Textures.LOGO.getTexture(), 0, Sizes.GAME_HEIGHT * 0.03f);

        ILabel playerLabel = Factories.getUtilsFactory().create().createMenuLabel(progress.getPlayerName(),
                Sizes.GAME_HEIGHT * 0.68f, 0x00ff00ff);

        IButton missionsButton = MenuButtonsBuilder.INSTANCE.missionsMenuButton(stage);
        IButton statsButton = MenuButtonsBuilder.INSTANCE.statsMenuButton(stage,
                progress.getAttributes().getFreePoints());
        IButton skillsButton = MenuButtonsBuilder.INSTANCE.skillsMenuButton(stage, 1);
        IButton weaponsButton = MenuButtonsBuilder.INSTANCE.weaponsMenuButton(stage);
        IButton backToMenuButton = MenuButtonsBuilder.INSTANCE.backToPlayersMenuButton(stage);

        stage.addBackground(background);
        stage.addActorBeforeGUI(logo);
        stage.addActor(new GameplayLabel(playerLabel));
        stage.addActor(missionsButton);
        stage.addActor(statsButton);
        stage.addActor(skillsButton);
        stage.addActor(weaponsButton);
        stage.addActor(backToMenuButton);

        stage.updateControls();

        MusicPlayer.INSTANCE.playMenu();

        return stage;
    }
}
