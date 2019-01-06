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

import com.zagorskidev.spaceattack.ships.IShip;
import com.zagorskidev.spaceattack.ui.buttons.FireButton;

import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.utils.vector.IVector;

public class MissionInputHandlerTest
{
	@Mock
	private IShip ship;

	@Mock
	private FireButton button;

	private MissionInputHandler handler;

	@Before
	public void setUp()
	{
		initMocks(this);

		handler = new MissionInputHandler();
	}

	@Test
	public void inputIsSettingShipsDestination()
	{
		doReturn(false).when(button).touchUp(anyInt(), anyInt());

		handler.registerShip(ship);
		handler.registerFireButton(button);
		handler.touchUp(100, 200, 1, 1);

		verify(ship).setDestination(eq(ExtVectorFactory.INSTANCE.create(100, 550)));
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
