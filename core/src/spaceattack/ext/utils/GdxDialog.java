package spaceattack.ext.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import spaceattack.consts.Consts;

public class GdxDialog extends Dialog
{
	public GdxDialog(String title)
	{
		super(title, GdxUtils.INSTANCE.getUiSkin(), Consts.DIALOG);
	}

	@Override
	public void result(Object object)
	{
		if ((boolean) object)
			throw new DialogAcceptedThrowable();

		throw new DialogCanceledThrowable();
	}
}

class DialogAcceptedThrowable extends RuntimeException
{
	private static final long serialVersionUID = 846286392500206585L;
}

class DialogCanceledThrowable extends RuntimeException
{
	private static final long serialVersionUID = 1521043832006773873L;
}
