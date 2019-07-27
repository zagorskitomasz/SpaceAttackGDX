package spaceattack.game.stages.impl;

import java.util.Map;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
import spaceattack.game.buttons.PlayerButtonsBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaver;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;

public class PlayerSelectionStageBuilder implements IStageBuilder {

    @Override
    public IGameStage build(final GameProgress progress) {

        MainMenuStage stage = new MainMenuStage();

        GameLoader loader = GameLoaderFactory.INSTANCE.create();
        GameSaver saver = GameSaverFactory.INSTANCE.create();

        stage.setStage(Factories.getStageFactory().create());
        stage.setGameSaver(saver);
        stage.setGameLoader(loader);

        StaticImage background = StaticImageFactory.INSTANCE.create(Textures.MENU_BACKGROUND.getTexture(), 0, 0);
        StaticImage logo = StaticImageFactory.INSTANCE.create(Textures.LOGO.getTexture(), 0, Sizes.GAME_HEIGHT * 0.03f);

        stage.addBackground(background);
        stage.addActorBeforeGUI(logo);

        Factories.getUtilsFactory().create().setInputProcessor(stage.getStage());

        Map<String, String> players = loader.loadAll();

        for (int buttonIndex = 0; buttonIndex < Consts.Metagame.PLAYER_SLOTS; buttonIndex++) {
            String index = String.valueOf(buttonIndex);

            if (players.containsKey(index)) {
                stage.addActor(PlayerButtonsBuilder.INSTANCE.createExistingPlayerButton(buttonIndex, players.get(index),
                        loader, stage));
                stage.addActor(PlayerButtonsBuilder.INSTANCE.createDeletePlayerButton(buttonIndex, saver, stage));
            }
            else {
                stage.addActor(PlayerButtonsBuilder.INSTANCE.createEmptySlotButton(buttonIndex, saver, stage));
            }
        }
        IButton exitButton = MenuButtonsBuilder.INSTANCE.exitGameButton(stage);
        exitButton.setPosition(exitButton.getX(), exitButton.getY() - Sizes.GAME_HEIGHT * 0.04f);
        stage.addActor(exitButton);

        MusicPlayer.INSTANCE.playMenu();

        return stage;
    }
}
