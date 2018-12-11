package com.zagorskidev.spaceattack.stages;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.ships.IShip;

public class GameplayStageTest
{
	private GameplayStage stage;

	@Before
	public void setUp()
	{
		stage = mock(GameplayStage.class);
		Mockito.doCallRealMethod().when(stage).registerShip(ArgumentMatchers.any(IShip.class));
	}

	@Test
	public void registeringObjectIsInitializingInputProcessor()
	{
		IInput inputProcessor = mock(IInput.class);
		doReturn(inputProcessor).when(stage).initInputProcessor();
		IShip ship = mock(IShip.class);

		stage.registerShip(ship);

		verify(inputProcessor).registerShip(ship);
	}
}
