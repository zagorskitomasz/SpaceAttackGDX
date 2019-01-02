package com.zagorskidev.spaceattack.stages.impl;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.zagorskidev.spaceattack.consts.Consts;

public class TimeLabelTest
{
	private TimeLabel timeLabel;

	@Mock
	private Label label;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		timeLabel = spy(new TimeLabel("TEST"));
		doReturn(label).when(timeLabel).createLabel();
		timeLabel.initGdx();
	}

	@Test
	public void afterShowIsDrawed()
	{
		timeLabel.show();
		timeLabel.draw(null, 0);

		verify(label).draw(null, 0);
	}

	@Test
	public void dissapearsAfter300Millis() throws InterruptedException
	{
		timeLabel.show();
		Thread.sleep(Consts.Gameplay.LABEL_SHOW_TIME + 1);
		timeLabel.draw(null, 0);

		verify(label, times(0)).draw(null, 0);
	}
}
