package com.zagorskidev.spaceattack.stages;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.math.Vector2;
import com.zagorskidev.spaceattack.input.IInput;
import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.weapons.IWeapon;
import com.zagorskidev.spaceattack.weapons.WeaponFactory;

public class GameplayStageTest
{
	private GameplayStage stage;

	@Mock
	private IShip ship;

	@Mock
	private IInput inputProcessor;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		stage = mock(GameplayStage.class);
		doCallRealMethod().when(stage).registerShip(any(IShip.class));
		doCallRealMethod().when(stage).setPrimaryWeapon(any(IWeapon.class));
		doCallRealMethod().when(stage).getPrimaryWeaponUsePlacement();
	}

	@Test
	public void registeringObjectIsInitializingInputProcessor()
	{
		doReturn(inputProcessor).when(stage).initInputProcessor();
		stage.registerShip(ship);

		verify(inputProcessor).registerShip(ship);
	}

	@Test
	public void placementOfLaserShot()
	{
		//@formatter:off
		IWeapon redLaser = WeaponFactory
				.INSTANCE
				.redLaser()
				.setController(stage)
				.setMissileLauncher(null)
				.setLevel(1)
				.build();
		//@formatter:off
		
		doReturn(100f).when(ship).getX();
		doReturn(200f).when(ship).getY();
		doReturn(60f).when(ship).getHeight();
		
		doReturn(inputProcessor).when(stage).initInputProcessor();
		stage.registerShip(ship);
		stage.setPrimaryWeapon(redLaser);
		
		assertEquals(new Vector2(100,242), stage.getPrimaryWeaponUsePlacement());
	}
}