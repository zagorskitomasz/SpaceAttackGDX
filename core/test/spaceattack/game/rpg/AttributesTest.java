package spaceattack.game.rpg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AttributesTest {

    private Attributes attributes;

    @Before
    public void setUp() {

        attributes = new Attributes();
        attributes.setFreePoints(5);
    }

    @Test
    public void initialValuesAreMinValues() {

        assertEquals(Attribute.MIN_VALUE + 5, attributes.get(Attribute.ARMORY));
        assertEquals(Attribute.MIN_VALUE + 5, attributes.get(Attribute.BATTERY));
        assertEquals(Attribute.MIN_VALUE, attributes.get(Attribute.ENGINE));
        assertEquals(Attribute.MIN_VALUE, attributes.get(Attribute.SHIELDS));
    }

    @Test
    public void attributeCanBeIncreased() {

        assertTrue(attributes.increase(Attribute.SHIELDS));
        assertEquals(Attribute.MIN_VALUE + 1, attributes.get(Attribute.SHIELDS));
    }

    @Test
    public void attributeCanBeDecreased() {

        attributes.increase(Attribute.SHIELDS);
        attributes.increase(Attribute.SHIELDS);

        assertTrue(attributes.decrease(Attribute.SHIELDS));
        assertEquals(Attribute.MIN_VALUE + 1, attributes.get(Attribute.SHIELDS));
    }

    @Test
    public void attributesCantBeBelowMinValue() {

        assertFalse(attributes.decrease(Attribute.SHIELDS));
        assertEquals(Attribute.MIN_VALUE, attributes.get(Attribute.SHIELDS));
    }

    @Test
    public void attributeCantBeIncreasedWhenNoFreePoints() {

        attributes.setFreePoints(0);

        assertFalse(attributes.increase(Attribute.SHIELDS));
        assertEquals(Attribute.MIN_VALUE, attributes.get(Attribute.SHIELDS));
    }

    @Test
    public void increasingIsConsumingFreePoint() {

        attributes.setFreePoints(2);
        attributes.increase(Attribute.SHIELDS);

        assertEquals(1, attributes.getFreePoints());
    }

    @Test
    public void decreasingIsGivingBackFreePoint() {

        attributes.setFreePoints(2);
        attributes.increase(Attribute.SHIELDS);
        attributes.decrease(Attribute.SHIELDS);

        assertEquals(2, attributes.getFreePoints());
    }
}
