package com.zagorskidev.spaceattack.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

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
