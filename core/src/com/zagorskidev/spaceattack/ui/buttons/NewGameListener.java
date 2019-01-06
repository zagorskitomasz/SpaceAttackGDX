package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameProgress;

import spaceattack.game.system.GameLoader;

public class NewGameListener extends ChangeStageButtonListener
{
	public NewGameListener(UIStage stage,Stages nextStage)
	{
		super(stage, Stages.MISSIONS);
	}

	@Override
	public void clicked(InputEvent event,float x,float y)
	{
		if (!isSaveFileExists())
			finalizeStage();
		else
			confirm();
	}

	IGameStage getStage()
	{
		return stage;
	}

	boolean isSaveFileExists()
	{
		return GameLoader.INSTANCE.fileExists();
	}

	void confirm()
	{
		Dialog dialog = new ConfirmNewGameDialog("New game", stage.getSkin(), "Dialog", this);
		dialog.text("\nOverride progress?\n");
		dialog.button("Yes", true);
		dialog.button("No", false);
		dialog.key(Keys.ENTER, true);
		dialog.show(stage);
	}

	@Override
	public void finalizeStage()
	{
		stage.setResult(createResult(new GameProgress()), true);
	}
}

class ConfirmNewGameDialog extends Dialog
{
	private NewGameListener listener;

	public ConfirmNewGameDialog(String title,Skin skin,String windowStyleName,NewGameListener listener)
	{
		super(title, skin, windowStyleName);
		setListener(listener);
	}

	void setListener(NewGameListener listener)
	{
		this.listener = listener;
	}

	@Override
	public void result(Object object)
	{
		if ((boolean) object)
			listener.finalizeStage();
	}
}