package spaceattack.game.buttons;

public interface ITextButtonFactory {

    IButton create(String text);

    IButton createAlertButton(String text);
}
