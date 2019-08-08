package spaceattack.game.stages.impl;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.ILabel;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.MenuButtonsBuilder;
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
        infoLabel.setText("Tap attribute name\nto show details");
        infoLabel.pack();
        infoLabel.setY(Sizes.GAME_HEIGHT * 0.7f);
        infoLabel.setX((Sizes.GAME_WIDTH - infoLabel.getWidth()) * 0.5f);

        ILabel freePointsLabel = utils.createBarLabel();
        freePointsLabel.setText("Free points: " + progress.getAttributes().getFreePoints());
        freePointsLabel.pack();
        freePointsLabel.setY(Sizes.GAME_HEIGHT * 0.17f);
        freePointsLabel.setX((Sizes.GAME_WIDTH - freePointsLabel.getWidth()) * 0.5f);

        int buttonIndex = 0;
        for (Attribute attribute : Attribute.values()) {

            float attributeLineYPos = attributeLineYPos(buttonIndex);

            ILabel attributeLabel = utils.createMenuLabel(attribute.getName(),
                    attributeLineYPos, 0xdaa520ff);
            ILabel attributeValueLabel = utils.createMenuLabel(
                    String.format("%03d", progress.getAttributes().get(attribute)),
                    attributeLineYPos, 0xff0000ff);
            IButton attributeDecreaser = MenuButtonsBuilder.INSTANCE.attributeDecreaserButton(stage, progress,
                    attribute, attributeValueLabel::setText, freePointsLabel::setText, infoLabel, attributeLineYPos);
            IButton attributeIncreaser = MenuButtonsBuilder.INSTANCE.attributeIncreaserButton(stage, progress,
                    attribute, attributeValueLabel::setText, freePointsLabel::setText, infoLabel, attributeLineYPos);

            ILabel infoToucher = utils.createDetailerToucher(attributeLineYPos, attribute.getDetails(),
                    infoLabel);

            attributeDecreaser.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.04f - attributeDecreaser.getWidth());
            attributeValueLabel.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.08f - attributeDecreaser.getWidth()
                    - attributeValueLabel.getWidth());
            attributeIncreaser.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.12f - attributeDecreaser.getWidth()
                    - attributeValueLabel.getWidth() - attributeIncreaser.getWidth());
            attributeLabel.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.16f - attributeDecreaser.getWidth()
                    - attributeValueLabel.getWidth() - attributeIncreaser.getWidth() - attributeLabel.getWidth());

            stage.addActor(new GameplayLabel(attributeLabel));
            stage.addActor(new GameplayLabel(attributeValueLabel));
            stage.addActor(new GameplayLabel(freePointsLabel));
            stage.addActor(new GameplayLabel(infoToucher));
            stage.addActor(attributeDecreaser);
            stage.addActor(attributeIncreaser);

            stage.addButtonsEnabledPredicate(attributeDecreaser,
                    button -> progress.getAttributes().get(attribute) > Attribute.MIN_VALUE);
            stage.addButtonsEnabledPredicate(attributeIncreaser,
                    button -> progress.getAttributes().getFreePoints() > 0);

            buttonIndex++;
        }

        IButton backToMenuButton = MenuButtonsBuilder.INSTANCE.backToMainMenuButton(stage);

        stage.addBackground(background);
        stage.addActorBeforeGUI(logo);
        stage.addActor(new GameplayLabel(infoLabel));

        stage.addActor(backToMenuButton);

        stage.updateControls();

        MusicPlayer.INSTANCE.playMenu();

        return stage;
    }

    protected float attributeLineYPos(final int buttonIndex) {

        return Sizes.GAME_HEIGHT * 0.6f - Sizes.GAME_HEIGHT * buttonIndex * 0.12f;
    }
}
