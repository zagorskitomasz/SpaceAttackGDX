package spaceattack.ext.button;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import spaceattack.consts.Consts;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.IActor;
import spaceattack.game.buttons.IButton;
import spaceattack.game.buttons.IListener;

public class GdxButton extends TextButton implements IButton
{
	public GdxButton(String text)
	{
		super(text, ExtUtilsFactory.INSTANCE.getGdxUtils().getUiSkin(), Consts.DEFAULT);
	}

	@Override
	public IActor getActor()
	{
		return this;
	}

	@Override
	public void addListener(IListener listener)
	{
		addListener(new ListenerProxy(listener));
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		setTouchable(enabled ? Touchable.enabled : Touchable.disabled);
		setDisabled(!enabled);
	}
}
