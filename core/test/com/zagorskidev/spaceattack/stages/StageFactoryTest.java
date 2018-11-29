package com.zagorskidev.spaceattack.stages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.stages.impl.MainMenuStage;

public class StageFactoryTest
{
	@Test
	public void factoryIsReturningProperStageInstance() throws InstantiationException,IllegalAccessException
	{
		StageFactory factory = Mockito.spy(StageFactory.INSTANCE);
		MainMenuStage stageMock = Mockito.mock(MainMenuStage.class);
		Mockito.doReturn(stageMock).when(factory).createInstance(ArgumentMatchers.eq(Stages.MAIN_MENU.getStageClass()));

		IStage stageReal = factory.getStage(Stages.MAIN_MENU);

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
		StageFactory factory = Mockito.spy(StageFactory.INSTANCE);
		MainMenuStage stageMock = Mockito.mock(MainMenuStage.class);
		Mockito.doReturn(stageMock).when(factory).createInstance(ArgumentMatchers.eq(Stages.MAIN_MENU.getStageClass()));

		factory.getStage(Stages.MAIN_MENU);

		Mockito.verify(stageMock).setType(Stages.MAIN_MENU);
	}
}
