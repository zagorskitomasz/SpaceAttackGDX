package spaceattack.game.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import spaceattack.game.system.Acts;

public class ActsTest
{
	@Test
	public void selectingByExistingNumber()
	{
		assertEquals(Acts.II, Acts.get(2));
	}

	@Test
	public void selectingByNonExistingNumber()
	{
		assertNull(Acts.get(-5));
	}
}
