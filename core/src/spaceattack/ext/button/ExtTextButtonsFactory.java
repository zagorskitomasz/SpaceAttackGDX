package spaceattack.ext.button;

import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.ITextButtonFactory;

public enum ExtTextButtonsFactory implements ITextButtonFactory
{
	INSTANCE;

	@Override
	public IButton create(String text)
	{
		return new GdxButton(text);
	}
}
