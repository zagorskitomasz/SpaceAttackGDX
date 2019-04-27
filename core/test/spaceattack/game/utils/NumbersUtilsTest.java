package spaceattack.game.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.vector.IVector;

public class NumbersUtilsTest {

    @Mock
    private IVector first;

    @Mock
    private IVector second;

    @Test
    public void convertingFirstTenNumers() {

        assertEquals("I", NumbersUtils.toRoman(1));
        assertEquals("II", NumbersUtils.toRoman(2));
        assertEquals("III", NumbersUtils.toRoman(3));
        assertEquals("IV", NumbersUtils.toRoman(4));
        assertEquals("V", NumbersUtils.toRoman(5));
        assertEquals("VI", NumbersUtils.toRoman(6));
        assertEquals("VII", NumbersUtils.toRoman(7));
        assertEquals("VIII", NumbersUtils.toRoman(8));
        assertEquals("IX", NumbersUtils.toRoman(9));
        assertEquals("X", NumbersUtils.toRoman(10));
    }

    @Test
    public void distance() {

        initMocks(this);

        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        doReturn(10f).when(first).getX();
        doReturn(10f).when(first).getY();
        doReturn(13f).when(second).getX();
        doReturn(14f).when(second).getY();

        assertEquals(5f, NumbersUtils.distance(first, second), 0);
    }
}
