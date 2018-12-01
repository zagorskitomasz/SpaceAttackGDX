package com.zagorskidev.spaceattack.ui.buttons;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.stages.UIStage;
import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;
import com.zagorskidev.spaceattack.ui.buttons.NewGameButton.NewGameListener;

public class NewGameButtonTest
{
	@Test
	public void afterTouchResultIsSetToMissionsStage()
	{
		UIStage stage = Mockito.mock(UIStage.class);
		NewGameButton button = Mockito.mock(NewGameButton.class);
		Mockito.doCallRealMethod().when(button).init();
		Mockito.doCallRealMethod().when(button).createListener();

		button.setStage(stage);
		button.init();

		Mockito.verify(button).addListener(ArgumentMatchers.any(ClickListener.class));
	}

	@Test
	public void listenerIsSettingResultToMissionsStage()
	{
		MainMenuStage stage = Mockito.mock(MainMenuStage.class);
		NewGameButton button = Mockito.mock(NewGameButton.class);

		NewGameListener listener = Mockito.spy(button.new NewGameListener());
		Mockito.doReturn(stage).when(listener).getStage();
		Mockito.doCallRealMethod().when(stage).setResult(ArgumentMatchers.any());
		Mockito.doCallRealMethod().when(stage).getResult();
		listener.clicked(null, 0, 0);

		assertEquals(Stages.MISSIONS, stage.getResult().getNextStage());
	}
}
