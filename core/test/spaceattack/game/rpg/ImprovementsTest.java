package spaceattack.game.rpg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ImprovementsTest {

    private Improvements improvements;

    @Before
    public void setUp() {

        improvements = new Improvements();
        improvements.setFreePoints(5);
    }

    @Test
    public void initialValuesAreMinValues() {

        assertEquals(Improvement.MIN_VALUE, improvements.get(Improvement.ABSORBER));
        assertEquals(Improvement.MIN_VALUE, improvements.get(Improvement.AMMO_COLLECTOR));
    }

    @Test
    public void improvementCanBeIncreased() {

        assertTrue(improvements.increase(Improvement.ABSORBER));
        assertEquals(Improvement.MIN_VALUE + 1, improvements.get(Improvement.ABSORBER));
    }

    @Test
    public void improvementCanBeDecreased() {

        improvements.increase(Improvement.ABSORBER);
        improvements.increase(Improvement.ABSORBER);

        assertTrue(improvements.decrease(Improvement.ABSORBER));
        assertEquals(Improvement.MIN_VALUE + 1, improvements.get(Improvement.ABSORBER));
    }

    @Test
    public void improvementCantBeBelowMinValue() {

        assertFalse(improvements.decrease(Improvement.ABSORBER));
        assertEquals(Improvement.MIN_VALUE, improvements.get(Improvement.ABSORBER));
    }

    @Test
    public void improvementCantBeIncreasedWhenNoFreePoints() {

        improvements.setFreePoints(0);

        assertFalse(improvements.increase(Improvement.ABSORBER));
        assertEquals(Improvement.MIN_VALUE, improvements.get(Improvement.ABSORBER));
    }

    @Test
    public void increasingIsConsumingFreePoint() {

        improvements.setFreePoints(2);
        improvements.increase(Improvement.ABSORBER);

        assertEquals(1, improvements.getFreePoints());
    }

    @Test
    public void decreasingIsGivingBackFreePoint() {

        improvements.setFreePoints(2);
        improvements.increase(Improvement.ABSORBER);
        improvements.decrease(Improvement.ABSORBER);

        assertEquals(2, improvements.getFreePoints());
    }

    @Test
    public void improvementCantBeAboveMaxValue() {

        improvements.setFreePoints(Improvement.MAX_VALUE * 2);

        for (int i = 0; i < Improvement.MAX_VALUE * 2; i++) {
            improvements.increase(Improvement.ABSORBER);
        }
        assertEquals(Improvement.MAX_VALUE, improvements.get(Improvement.ABSORBER));
    }
}
