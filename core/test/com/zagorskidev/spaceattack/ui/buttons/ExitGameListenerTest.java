package com.zagorskidev.spaceattack.ui.buttons;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import spaceattack.game.stages.UIStage;

public class ExitGameListenerTest
{
	private ConfirmExitDialog dialog;
	private ExitGameListener listener;

	@Before
	public void setUp()
	{
		UIStage stage = mock(UIStage.class);
		listener = spy(new ExitGameListener(stage));
		doNothing().when(listener).closeApp();
		doNothing().when(listener).confirmExit();
		dialog = mock(ConfirmExitDialog.class);
		doCallRealMethod().when(dialog).setListener(any());
		doCallRealMethod().when(dialog).result(any());
	}

	@Test
	public void afterTouchCloseAppIsInvoked()
	{
		dialog.setListener(listener);
		dialog.result(true);

		verify(listener).closeApp();
	}

	@Test
	public void ifNotConfirmedNotExited()
	{
		dialog.setListener(listener);
		dialog.result(false);

		verify(listener, times(0)).closeApp();
	}
}
