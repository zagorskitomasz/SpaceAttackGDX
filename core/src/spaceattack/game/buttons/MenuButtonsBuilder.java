package spaceattack.game.buttons;

import spaceattack.consts.Sizes;
import spaceattack.consts.UIStrings;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.Stages;
import spaceattack.game.stages.UIStage;
import spaceattack.game.stages.impl.MainMenuStage;
import spaceattack.game.stages.impl.MissionsStage;

public enum MenuButtonsBuilder {
    INSTANCE;

    private ITextButtonFactory factory;

    private MenuButtonsBuilder() {

        factory = Factories.getTextButtonFactory();
    }

    public IButton exitGameButton(final MainMenuStage stage) {

        IButton button = factory.createAlertButton(UIStrings.EXIT);

        button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.1f);
        button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
        button.addListener(new ExitGameListener(stage));

        return button;
    }

    public IButton missionsMenuButton(final UIStage stage) {

        IButton button = factory.createAlertButton(UIStrings.MISSIONS_BTN);

        button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.54f);
        button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT * 1.2f);
        button.addListener(new ChangeStageButtonListener(stage, Stages.MISSIONS));

        return button;
    }

    public IButton statsMenuButton(final UIStage stage, final int amount) {

        IButton button = factory.create(UIStrings.STATS_BTN + (amount > 0 ? " [+" + amount + "]" : ""));

        button.setPosition(Sizes.GAME_WIDTH * 0.2f - Sizes.BUTTON_WIDTH * 0.15f, Sizes.GAME_HEIGHT * 0.42f);
        button.setSize(Sizes.BUTTON_WIDTH * 1.3f, Sizes.BUTTON_HEIGHT);
        button.addListener(new ChangeStageButtonListener(stage, Stages.STATS));

        return button;
    }

    public IButton skillsMenuButton(final UIStage stage, final int amount) {

        IButton button = factory.create(UIStrings.SKILLS_BTN + (amount > 0 ? " [+" + amount + "]" : ""));

        button.setPosition(Sizes.GAME_WIDTH * 0.2f - Sizes.BUTTON_WIDTH * 0.15f, Sizes.GAME_HEIGHT * 0.30f);
        button.setSize(Sizes.BUTTON_WIDTH * 1.3f, Sizes.BUTTON_HEIGHT);
        button.addListener(new ChangeStageButtonListener(stage, Stages.SKILLS));

        return button;
    }

    public IButton weaponsMenuButton(final UIStage stage) {

        IButton button = factory.create(UIStrings.WEAPONS_BTN);

        button.setPosition(Sizes.GAME_WIDTH * 0.2f - Sizes.BUTTON_WIDTH * 0.15f, Sizes.GAME_HEIGHT * 0.18f);
        button.setSize(Sizes.BUTTON_WIDTH * 1.3f, Sizes.BUTTON_HEIGHT);
        button.addListener(new ChangeStageButtonListener(stage, Stages.WEAPONS));

        return button;
    }

    public IButton backToPlayersMenuButton(final UIStage stage) {

        IButton button = factory.createAlertButton(UIStrings.BACK);

        button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.04f);
        button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
        button.addListener(new ChangeStageButtonListener(stage, Stages.PLAYERS_MENU));

        return button;
    }

    public IButton backToMainMenuButton(final UIStage stage) {

        IButton button = factory.createAlertButton(UIStrings.BACK);

        button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * 0.04f);
        button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
        button.addListener(new ChangeStageButtonListener(stage, Stages.MAIN_MENU));

        return button;
    }

    public IButton previousActButton(final MissionsStage stage) {

        IButton button = factory.create(UIStrings.PREV_ACT);

        button.setPosition(Sizes.GAME_WIDTH * 0.1f, Sizes.GAME_HEIGHT * 0.5f);
        button.setSize(Sizes.BUTTON_WIDTH * 0.5f, Sizes.BUTTON_HEIGHT);
        button.addListener(new ActChangeButtonListener(stage, ActChangeButtonListener.Variants.PREV));

        return button;
    }

    public IButton nextActButton(final MissionsStage stage) {

        IButton button = factory.create(UIStrings.NEXT_ACT);

        button.setPosition(Sizes.GAME_WIDTH * 0.6f, Sizes.GAME_HEIGHT * 0.5f);
        button.setSize(Sizes.BUTTON_WIDTH * 0.5f, Sizes.BUTTON_HEIGHT);
        button.addListener(new ActChangeButtonListener(stage, ActChangeButtonListener.Variants.NEXT));

        return button;
    }

    public IButton missionButton(final MissionsStage stage, final int gridPosition) {

        IButton button = factory.create(UIStrings.MISSION);

        button.setColumnPosition(gridPosition);
        button.setPosition(Sizes.GAME_WIDTH * 0.2f, Sizes.GAME_HEIGHT * (0.48f - (gridPosition + 1) * 0.1f));
        button.setSize(Sizes.BUTTON_WIDTH, Sizes.BUTTON_HEIGHT);
        button.addListener(new LaunchMissionButtonListener(stage, gridPosition));

        return button;
    }
}
