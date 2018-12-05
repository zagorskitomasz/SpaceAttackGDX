package com.zagorskidev.spaceattack.graphics;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class StaticImageFactoryTest
{
	private StaticImageFactory factory;
	private StaticImage image;

	@Before
	public void setUp()
	{
		factory = mock(StaticImageFactory.class);
		image = mock(StaticImage.class);
		doReturn(image).when(factory).instantiateImage();
		doCallRealMethod().when(factory).create(anyString(), anyFloat(), anyFloat());
	}

	@Test
	public void factoryIsInitializingImageObject()
	{
		factory.create("path", 100, 500);

		verify(image).init("path", 100, 500);
	}

	@Test
	public void factoryIsReturningCreatedObject()
	{
		StaticImage imageCreated = factory.create("path", 100, 500);

		assertEquals(image, imageCreated);
	}
}
