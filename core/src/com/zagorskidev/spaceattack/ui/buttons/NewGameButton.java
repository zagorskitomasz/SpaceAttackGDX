package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.UIStrings;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.system.GameProgress;

public class NewGameButton extends TextButton
{
	private UIStage stage;

	public NewGameButton(UIStage pmStage)
	{
		super(UIStrings.NEW_GAME, pmStage.getSkin(), Consts.DEFAULT);
		stage = pmStage;

		init();
	}

	void init()
	{
		setPosition(Consts.GAME_WIDTH * 0.2f, 12 * Consts.BUTTON_HEIGHT);
		setSize(Consts.BUTTON_WIDTH, Consts.BUTTON_HEIGHT);
		addListener(createListener());
	}

	NewGameListener createListener()
	{
		return new NewGameListener();
	}

	void setStage(UIStage stage)
	{
		this.stage = stage;
	}

	class NewGameListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event,float x,float y)
		{
			if (!isSaveFileExists())
				finalizeStage();
			else
				confirm();
		}

		IStage getStage()
		{
			return stage;
		}

		boolean isSaveFileExists()
		{
			return GameLoader.INSTANCE.fileExists();
		}

		private StageResult createResult(GameProgress progress)
		{
			StageResult result = new StageResult();
			result.setNextStage(Stages.MISSIONS);
			result.setGameProgress(progress);

			return result;
		}

		void confirm()
		{
			Dialog dialog = new ConfirmNewGameDialog("Override progress?", stage.getSkin(), "Dialog", this);
			dialog.text("New game will override your progress.\nContinue?");
			dialog.button("Yes", true);
			dialog.button("No", false);
			dialog.key(Keys.ENTER, true);
			dialog.show(stage);
		}

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
}