package spaceattack.game.buttons;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import spaceattack.game.stages.impl.MissionsStage;

public class ActChangeButtonListenerTest
{
	@Mock
	private MissionsStage stage;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void clickOnNextVariantIsIncrementingStageAct()
	{
		ActChangeButtonListener listener = new ActChangeButtonListener(stage, ActChangeButtonListener.Variants.NEXT);
		listener.clicked();
		Mockito.verify(stage).nextAct();
	}

	@Test
	public void clickOnPrevVariantIsIncrementingStageAct()
	{
		ActChangeButtonListener listener = new ActChangeButtonListener(stage, ActChangeButtonListener.Variants.PREV);
		listener.clicked();
		Mockito.verify(stage).previousAct();
	}
}
