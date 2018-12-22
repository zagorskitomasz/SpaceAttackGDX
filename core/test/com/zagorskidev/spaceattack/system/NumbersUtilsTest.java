package com.zagorskidev.spaceattack.system;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class NumbersUtilsTest
{
	@Test
	public void convertingFirstTenNumers()
	{
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
	public void distance()
	{
		Vector2 v1 = new Vector2(10, 10);
		Vector2 v2 = new Vector2(13, 14);

		assertEquals(5f, NumbersUtils.distance(v1, v2), 0);
	}
}
