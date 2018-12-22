package com.zagorskidev.spaceattack.system;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import com.zagorskidev.spaceattack.consts.Consts;

public class FrameControllerTest
{
	private FrameController frameController;

	@Before
	public void setUp()
	{
		frameController = spy(new FrameController(Consts.FPS));
	}

	@Test
	public void ifIntervalGreaterThanThresholdReturnTrue()
	{
		doReturn(60000l).when(frameController).getCurrentTime();
		frameController.check();
		doReturn(60000l + 1000 / Consts.FPS + 1).when(frameController).getCurrentTime();

		assertTrue(frameController.check());
	}

	@Test
	public void ifIntervalLesserThanThresholdReturnFalse()
	{
		doReturn(60000l).when(frameController).getCurrentTime();
		frameController.check();
		doReturn(60000l + 1000 / Consts.FPS - 1).when(frameController).getCurrentTime();

		assertFalse(frameController.check());
	}
}
