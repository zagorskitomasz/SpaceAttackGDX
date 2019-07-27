package spaceattack.game.buttons;

import spaceattack.consts.Sizes;
import spaceattack.consts.UIStrings;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.impl.MainMenuStage;
import spaceattack.game.system.GameLoader;
import spaceattack.game.system.GameSaver;

public enum PlayerButtonsBuilder {

    INSTANCE;

    private ITextButtonFactory factory;

    private PlayerButtonsBuilder() {

        factory = Factories.getTextButtonFactory();
    }

    public IButton createEmptySlotButton(final int buttonIndex, final GameSaver saver, final IGameStage stage) {

        IButton button = factory.create(UIStrings.EMPTY_SLOT);

        button.setPosition(Sizes.GAME_WIDTH * 0.25f,
                Sizes.GAME_HEIGHT * 0.65f - Sizes.BUTTON_HEIGHT * 1.1f * buttonIndex);
        button.setSize(Sizes.BUTTON_WIDTH - Sizes.GAME_WIDTH * 0.1f, Sizes.BUTTON_HEIGHT * 0.9f);
        button.addListener(new CreatePlayerListener(buttonIndex, saver, stage));
        button.setDisabledStyle(true);

        return button;
    }

    public IButton createExistingPlayerButton(final int buttonIndex, final String name, final GameLoader loader,
            final IGameStage stage) {

        IButton button = factory.create(name);

        button.setPosition(Sizes.GAME_WIDTH * 0.2f,
                Sizes.GAME_HEIGHT * 0.65f - Sizes.BUTTON_HEIGHT * 1.1f * buttonIndex);
        button.setSize(Sizes.BUTTON_WIDTH * 0.95f, Sizes.BUTTON_HEIGHT * 0.9f);
        button.addListener(new LoadPlayerListener(buttonIndex, loader, stage));

        return button;
    }

    public IGameActor createDeletePlayerButton(final int buttonIndex, final GameSaver saver,
            final MainMenuStage stage) {

        IButton button = factory.createAlertButton(UIStrings.X);

        button.setPosition(Sizes.GAME_WIDTH * 0.3f + Sizes.BUTTON_WIDTH * 0.8f,
                Sizes.GAME_HEIGHT * 0.65f - Sizes.BUTTON_HEIGHT * 1.1f * buttonIndex);
        button.setSize(Sizes.BUTTON_WIDTH * 0.20f, Sizes.BUTTON_HEIGHT * 0.9f);
        button.addListener(new DeletePlayerListener(buttonIndex, saver, stage));

        return button;
    }
}
