package com.zagorskidev.spaceattack.ui.buttons;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.stages.impl.MissionsStage;

public class ActChangeListenerTest
{
	private MissionsStage stage;

	@Before
	public void setUp()
	{
		stage = Mockito.mock(MissionsStage.class);
	}

	@Test
	public void clickOnNextVariantIsIncrementingStageAct()
	{
		ActChangeListener listener = new ActChangeListener(stage, ActChangeListener.Variants.NEXT);
		listener.clicked(null, 0, 0);
		Mockito.verify(stage).nextAct();
	}

	@Test
	public void clickOnPrevVariantIsIncrementingStageAct()
	{
		ActChangeListener listener = new ActChangeListener(stage, ActChangeListener.Variants.PREV);
		listener.clicked(null, 0, 0);
		Mockito.verify(stage).previousAct();
	}
}
