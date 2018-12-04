package com.zagorskidev.spaceattack.stages;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;

public class StageFactoryTest
{
	@Test
	public void factoryIsReturningProperStageInstance() throws InstantiationException,IllegalAccessException
	{
		StageFactory factory = spy(StageFactory.INSTANCE);
		MainMenuStage stageMock = mock(MainMenuStage.class);
		doReturn(stageMock).when(factory).createInstance(eq(Stages.MAIN_MENU.getStageClass()));

		IStage stageReal = factory.getStage(new StageResult());

		assertEquals(stageMock, stageReal);
	}

	@Test
	public void factoryIsSingleton()
	{
		StageFactory firstInstance = StageFactory.INSTANCE;
		StageFactory secondInstance = StageFactory.INSTANCE;

		assertEquals(firstInstance, secondInstance);
	}

	@Test
	public void factoryIsSettingStageType() throws InstantiationException,IllegalAccessException
	{
		StageFactory factory = spy(StageFactory.INSTANCE);
		MainMenuStage stageMock = mock(MainMenuStage.class);
		doReturn(stageMock).when(factory).createInstance(eq(Stages.MAIN_MENU.getStageClass()));

		factory.getStage(new StageResult());

		verify(stageMock).setType(Stages.MAIN_MENU);
	}
}
