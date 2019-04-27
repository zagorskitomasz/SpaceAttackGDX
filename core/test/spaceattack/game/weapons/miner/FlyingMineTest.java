package spaceattack.game.weapons.miner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;
import spaceattack.game.utils.vector.IVectorFactory;

public class FlyingMineTest {

    private IVectorFactory vectors;

    @Mock
    private Supplier<IVector> coordsSupplier;

    @InjectMocks
    private FlyingMine mine;

    @Before
    public void setUp() {

        vectors = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(vectors);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        mine = new FlyingMine(10000);
        mine.setActor(new FakeActor());
        mine.setSpeed(5);
        mine.setX(100);
        mine.setY(200);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dontMoveWhenNoCoordsDelivered() {

        doReturn(null).when(coordsSupplier).get();

        mine.move();

        assertEquals(vectors.create(100, 200), mine.getPosition());
    }

    @Test
    public void moveInSingleDimension() {

        doReturn(vectors.create(500, 200)).when(coordsSupplier).get();

        mine.move();

        assertEquals(vectors.create(105, 200), mine.getPosition());
    }

    @Test
    public void moveInTwoDimensions() {

        doReturn(vectors.create(50, 150)).when(coordsSupplier).get();

        mine.move();

        assertEquals(96.5, mine.getPosition().getX(), 0.1);
        assertEquals(196.5, mine.getPosition().getY(), 0.1);
    }
}
