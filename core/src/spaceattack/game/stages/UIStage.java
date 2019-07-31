package spaceattack.game.stages;

import spaceattack.game.factories.Factories;
import spaceattack.game.input.IInputProcessor;

public abstract class UIStage extends AbstractStage {

    private Message messageToShow;

    @Override
    public IInputProcessor getInputProcessor() {

        return null;
    }

    public void showMessage(final Message message) {

        messageToShow = message;
    }

    @Override
    public void draw() {

        stage.draw();

        if (messageToShow != null) {
            Factories.getUtilsFactory().create().infoDialog(messageToShow.title, messageToShow.text, stage);
            messageToShow = null;
        }
    }

    public static class Message {

        private final String title;
        private final String text;

        public Message(final String title, final String text) {

            this.title = title;
            this.text = text;
        }
    }
}
