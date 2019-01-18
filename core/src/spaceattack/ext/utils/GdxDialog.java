package spaceattack.ext.utils;

import java.util.function.Consumer;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import spaceattack.consts.Consts;

public class GdxDialog extends Dialog
{
	private Consumer<Boolean> resultProcessor;

	public GdxDialog(String title)
	{
		super(title, GdxUtils.INSTANCE.getUiSkin(), Consts.DIALOG);
	}

	public void setResultProcessor(Consumer<Boolean> resultProcessor)
	{
		this.resultProcessor = resultProcessor;
	}

	@Override
	public void result(Object object)
	{
		if (resultProcessor != null)
			resultProcessor.accept((boolean) object);
	}
}