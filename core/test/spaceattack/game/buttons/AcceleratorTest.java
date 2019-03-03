package spaceattack.game.buttons;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.consts.Consts;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.IGameplayInput;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class AcceleratorTest
{
	@Mock
	private IGameplayInput processor;
	
	@Mock
	private IProgressButton button;
	
	@Mock
	private IVector vector;
	
	@InjectMocks
	private Accelerator accelerator;
	
	private IVectorFactory factory;
	
	@Before
	public void setUp()
	{
		accelerator = new Accelerator();
		MockitoAnnotations.initMocks(this);
		
		factory = ExtVectorFactory.INSTANCE;
		Factories.setVectorFactory(factory);

		doReturn(0f).when(button).getX();
		doReturn(0f).when(button).getY();
		doReturn(100f).when(button).getWidth();
		doReturn(100f).when(button).getHeight();
	}
	
	@Test
	public void whenNotTouchedVerticalAccIsZero() 
	{
		doReturn(null).when(processor).getTouch();
		
		accelerator.act(0);
		assertEquals(0, accelerator.getVerticalAcceleration(),0);
	}
	
	@Test
	public void whenTouchOutOfButtonVerticalAccIsZero()
	{
		doReturn(vector).when(processor).getTouch();
		doReturn(factory.create(200, 200)).when(button).screenToStageCoordinates(nullable(IVector.class));

		accelerator.act(0);
		assertEquals(0, accelerator.getVerticalAcceleration(),0);
	}
	
	@Test
	public void whenTouchedVerticalAccIsPercentOfUpperButtonPart()
	{
		doReturn(vector).when(processor).getTouch();
		doReturn(factory.create(50, 70)).when(button).screenToStageCoordinates(nullable(IVector.class));
		
		accelerator.act(0);
		assertEquals(40, accelerator.getVerticalAcceleration(),0);
	}
	
	@Test
	public void whenTouchedVerticalBrakeIsPercentOfLowerButtonPart()
	{
		doReturn(vector).when(processor).getTouch();
		doReturn(factory.create(50, 35)).when(button).screenToStageCoordinates(nullable(IVector.class));
		
		accelerator.act(0);
		assertEquals(-30, accelerator.getVerticalAcceleration(),0);
	}
	
	@Test
	public void onButtonTouchItIsRemembered()
	{
		doReturn(vector).when(processor).getTouch();
		doReturn(factory.create(50, 70)).when(button).screenToStageCoordinates(nullable(IVector.class));
		
		accelerator.act(0);
		verify(button).keep();
	}
	
	@Test
	public void ifNoTouchButtonIsReleased()
	{
		doReturn(null).when(processor).getTouch();
		
		accelerator.act(0);
		verify(button).release();
	}

	@Test
	public void inputBelowThresholdIsZero()
	{
		doReturn(Consts.Metagame.ACCELEROMETR_THRESHOLD * 0.5f).when(processor).getAccelerometrX();

		accelerator.act(0);
		assertEquals(0f, accelerator.getHorizontalAcceleration(), 0);
	}

	@Test
	public void inputOverLimitIsLimited()
	{
		doReturn(-1 * (Consts.Metagame.ACCELEROMETR_MAX + 1)).when(processor).getAccelerometrX();

		accelerator.act(0);
		assertEquals(100f, accelerator.getHorizontalAcceleration(), 0);
	}

	@Test
	public void inputIsTransformed()
	{
		doReturn(Consts.Metagame.ACCELEROMETR_MAX * 0.3f).when(processor).getAccelerometrX();

		accelerator.act(0);
		assertEquals(-30f, accelerator.getHorizontalAcceleration(), 0);
	}
}