package spaceattack.game.actors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.consts.Consts;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.factories.Factories;

public class TimeLabelTest
{
	private TimeLabel timeLabel;

	@Mock
	private ILabel label;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

		timeLabel = new TimeLabel();
		timeLabel.setLabel(label);
	}

	@Test
	public void afterShowDrawingIsEnabling()
	{
		timeLabel.show();
		timeLabel.draw(null, 0);

		verify(label).enableDawing();
	}

	@Test
	public void drawingDisabledAfter300Millis() throws InterruptedException
	{
		timeLabel.show();
		timeLabel.draw(null, 0);
		verify(label,times(0)).disableDrawing();
		
		Thread.sleep(Consts.Gameplay.LABEL_SHOW_TIME + 1);

		timeLabel.draw(null, 0);
		verify(label).disableDrawing();
	}
}
