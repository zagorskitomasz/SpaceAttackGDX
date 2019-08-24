package spaceattack.game.stages.impl;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.ILabel;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
import spaceattack.game.controlBar.ControlBar;
import spaceattack.game.controlBar.ControlBarBuilder;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.GameLoaderFactory;
import spaceattack.game.system.GameSaverFactory;
import spaceattack.game.system.graphics.StaticImage;
import spaceattack.game.system.graphics.StaticImageFactory;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.MusicPlayer;
import spaceattack.game.utils.GameplayLabel;
import spaceattack.game.utils.IUtils;

public class AttributesStageBuilder implements IStageBuilder {

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
        infoLabel.update("Tap attribute name\nto show details");
        infoLabel.setY(Sizes.GAME_HEIGHT * 0.7f);

        ILabel freePointsLabel = utils.createBarLabel();
        freePointsLabel.setText("Free points: " + progress.getAttributes().getFreePoints());
        freePointsLabel.pack();
        freePointsLabel.setY(Sizes.GAME_HEIGHT * 0.18f);
        freePointsLabel.setX((Sizes.GAME_WIDTH - freePointsLabel.getWidth()) * 0.5f);

        ILabel levelLabel = Factories.getUtilsFactory().create().createBarLabel();
        levelLabel.setText("Ship level: " + progress.getLevel());
        levelLabel.pack();
        levelLabel.setY(Sizes.GAME_HEIGHT * 0.14f);
        levelLabel.setX((Sizes.GAME_WIDTH - levelLabel.getWidth()) * 0.5f);

        int lineIndex = 0;
        for (Attribute attribute : Attribute.values()) {

            ControlBar bar = ControlBarBuilder.INSTANCE.attributeBar(lineIndex, attribute, stage, infoLabel,
                    freePointsLabel);

            stage.addActorsContainer(bar);

            lineIndex++;
        }

        IButton backToMenuButton = MenuButtonsBuilder.INSTANCE.backToMainMenuButton(stage);

        stage.addBackground(background);
        stage.addActorBeforeGUI(logo);
        stage.addActor(new GameplayLabel(infoLabel));
        stage.addActor(new GameplayLabel(freePointsLabel));
        stage.addActor(new GameplayLabel(levelLabel));

        stage.addActor(backToMenuButton);

        stage.updateControls();

        MusicPlayer.INSTANCE.playMenu();

        return stage;
    }
}
