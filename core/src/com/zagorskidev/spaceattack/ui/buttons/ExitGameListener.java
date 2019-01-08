package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import spaceattack.game.stages.UIStage;

public class ExitGameListener extends ClickListener
{
	private UIStage stage;

	public ExitGameListener(UIStage stage)
	{
		this.stage = stage;
	}

	@Override
	public void clicked(InputEvent event,float x,float y)
	{
		confirmExit();
	}

	void confirmExit()
	{
		Dialog dialog = new ConfirmExitDialog("Exit", stage.getSkin(), "Dialog", this);
		dialog.text("\nExit game?\n");
		dialog.button("Yes", true);
		dialog.button("No", false);
		dialog.key(Keys.ENTER, true);
		dialog.show(stage);
	}

	void closeApp()
	{
		Gdx.app.exit();
	}
}

class ConfirmExitDialog extends Dialog
{
	private ExitGameListener listener;

	public ConfirmExitDialog(String title,Skin skin,String windowStyleName,ExitGameListener listener)
	{
		super(title, skin, windowStyleName);
		this.listener = listener;
	}

	void setListener(ExitGameListener listener)
	{
		this.listener = listener;
	}

	@Override
	public void result(Object object)
	{
		if ((boolean) object)
			listener.closeApp();
	}
}