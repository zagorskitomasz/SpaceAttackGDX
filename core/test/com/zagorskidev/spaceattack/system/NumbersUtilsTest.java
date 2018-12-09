package com.zagorskidev.spaceattack.system;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
