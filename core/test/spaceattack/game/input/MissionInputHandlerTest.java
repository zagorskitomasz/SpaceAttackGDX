package spaceattack.game.input;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.consts.Sizes;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.IShip;
import spaceattack.game.utils.vector.IVector;

public class MissionInputHandlerTest
{
	@Mock
	private IShip ship;

	@Mock
	private IFireButton button;

	private MissionInputHandler handler;

	@Before
	public void setUp()
	{
		initMocks(this);
		Factories.setVectorFactory(ExtVectorFactory.INSTANCE);

		handler = new MissionInputHandler();
	}

	@Test
	public void inputIsSettingShipsDestination()
	{
		doReturn(false).when(button).touchUp(anyInt(), anyInt());

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship).setDestination(eq(ExtVectorFactory.INSTANCE.create(100, Sizes.GAME_HEIGHT - 200)));
	}

	@Test
	public void ifWeaponFiredShipIsNotMoved()
	{
		doReturn(true).when(button).touchUp(anyInt(), anyInt());

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship, times(0)).setDestination(any(IVector.class));
	}
}
