package spaceattack.ext.button;

import spaceattack.consts.Consts;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.ITextButtonFactory;

public enum ExtTextButtonsFactory implements ITextButtonFactory {
    INSTANCE;

    @Override
    public IButton create(String text) {

        return new GdxButton(text);
    }

    @Override
    public IButton createAlertButton(String text) {

        return new GdxButton(text, Consts.RED_BTN);
    }
}
