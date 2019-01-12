package spaceattack.game.buttons;

public interface ITextButtonFactory
{
	public IButton create(String text);

	public IButton createAlertButton(String text);
}
