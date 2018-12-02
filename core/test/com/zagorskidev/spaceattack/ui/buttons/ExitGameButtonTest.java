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

import com.zagorskidev.spaceattack.ui.buttons.ExitGameButton.ConfirmExitDialog;
import com.zagorskidev.spaceattack.ui.buttons.ExitGameButton.ExitGameListener;

public class ExitGameButtonTest
{
	private ConfirmExitDialog dialog;
	private ExitGameListener listener;

	@Before
	public void setUp()
	{
		ExitGameButton button = mock(ExitGameButton.class);
		listener = spy(button.new ExitGameListener());
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
