package com.zagorskidev.spaceattack.ui.buttons;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public class PrimaryFireButtonTest
{
	@Mock
	private IWeapon weapon;

	@Mock
	private Stage stage;

	private FireButton button;

	@Before
	public void setUp()
	{
		initMocks(this);

		button = mock(PrimaryFireButton.class);
		button.weapon = weapon;
		doReturn(80f).when(button).getWidth();
		doReturn(stage).when(button).getStage();
		doCallRealMethod().when(button).touchUp(anyInt(), anyInt());
		doCallRealMethod().when(button).setButtonCenter(any(Vector2.class));
		button.setButtonCenter(new Vector2(200, 200));
	}

	@Test
	public void touchingCenterOfButtonIsFiringWeapon()
	{
		doReturn(new Vector2(200, 200)).when(stage).screenToStageCoordinates(any(Vector2.class));
		boolean result = button.touchUp(200, 200);

		verify(weapon).use();
		assertTrue(result);
	}

	@Test
	public void touchingBorderOfButtonIsFiringWeapon()
	{
		doReturn(new Vector2(239, 200)).when(stage).screenToStageCoordinates(any(Vector2.class));
		boolean result = button.touchUp(239, 200);

		verify(weapon).use();
		assertTrue(result);
	}

	@Test
	public void touchingOutOfBorderOfButtonIsNotFiringWeapon()
	{
		doReturn(new Vector2(241, 200)).when(stage).screenToStageCoordinates(any(Vector2.class));
		boolean result = button.touchUp(241, 200);

		verify(weapon, times(0)).use();
		assertFalse(result);
	}

	@Test
	public void cornerIsExcluded()
	{
		doReturn(new Vector2(238, 238)).when(stage).screenToStageCoordinates(any(Vector2.class));
		boolean result = button.touchUp(238, 238);

		verify(weapon, times(0)).use();
		assertFalse(result);
	}

	@Test
	public void releasingOutsideButtonIsUnpressing()
	{
		doReturn(new Vector2(238, 238)).when(stage).screenToStageCoordinates(any(Vector2.class));
		doReturn(true).when(button).isPressed();
		boolean result = button.touchUp(238, 238);

		verify(button).fire(any(InputEvent.class));
		assertFalse(result);
	}
}